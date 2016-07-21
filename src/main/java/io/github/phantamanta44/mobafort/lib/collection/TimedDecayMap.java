package io.github.phantamanta44.mobafort.lib.collection;

import org.apache.commons.lang.mutable.MutableInt;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class TimedDecayMap<K> implements ISelfMutatingCollection<K>, ISimpleMap<K, Integer> {

	private final Map<K, MutableInt> backing;
	private final Map<K, StackDecayTask> decayTasks;
	private final Collection<Runnable> selfMutationListeners;
	private final Plugin plugin;
	private long defaultDecayTime;

	public TimedDecayMap(Plugin plugin, long defaultDecayTime) {
		if (defaultDecayTime < 1L)
			throw new IllegalArgumentException();
		this.plugin = plugin;
		this.backing = new ConcurrentHashMap<>();
		this.decayTasks = new ConcurrentHashMap<>();
		this.selfMutationListeners = new LinkedList<>();
		this.defaultDecayTime = defaultDecayTime;
	}

	public long getDefaultDecayTime() {
		return defaultDecayTime;
	}

	public void setDefaultDecayTime(long ticks) {
		this.defaultDecayTime = ticks;
	}

	@Override
	public int size() {
		return backing.size();
	}

	@Override
	public boolean isEmpty() {
		return backing.isEmpty();
	}

	@Override
	public boolean contains(K key) {
		return backing.containsKey(key);
	}

	@Override
	public void put(K key, Integer val) {
		set(key, val);
	}

	@Override
	public Integer get(K key) {
		MutableInt val = backing.get(key);
		return val != null ? val.intValue() : 0;
	}

	@Override
	public boolean remove(K key) {
		return clear(key);
	}

	public void add(K key, int amt) {
		add(key, amt, defaultDecayTime);
	}

	public void add(K key, int amt, long decayTime) {
		if (amt < 0)
			throw new IllegalArgumentException();
		MutableInt val = backing.get(key);
		if (val == null)
			put(key, new MutableInt(amt), decayTime);
		else
			val.setValue(val.intValue() + amt);
	}

	public void remove(K key, int amt) {
		if (amt < 0)
			throw new IllegalArgumentException();
		MutableInt val = backing.get(key);
		if (val != null) {
			if (amt >= val.intValue())
				dropEntry(key);
			else
				val.setValue(val.intValue() - amt);
		}
	}

	public void set(K key, int amt) {
		set(key, amt, defaultDecayTime);
	}

	public void set(K key, int amt, long decayTime) {
		if (amt > 0) {
			MutableInt val = backing.get(key);
			if (val != null)
				val.setValue(amt);
			else
				put(key, new MutableInt(amt), decayTime);
		}
		else
			dropEntry(key);
	}

	public boolean clear(K key) {
		if (contains(key)) {
			dropEntry(key);
			return true;
		}
		return false;
	}

	@Override
	public void clear() {
		backing.clear();
		decayTasks.entrySet().removeIf(e -> {
			e.getValue().cancel();
			return true;
		});
	}

	@Override
	public Set<Map.Entry<K, Integer>> entrySet() {
		return backing.entrySet().stream()
				.map(e -> new AbstractMap.SimpleImmutableEntry<>(e.getKey(), e.getValue().intValue()))
				.collect(Collectors.toSet());
	}

	private boolean dropEntry(K key) {
		if (backing.remove(key) != null) {
			decayTasks.remove(key).cancel();
			return true;
		}
		return false;
	}

	private void put(K key, MutableInt val, long decayTime) {
		backing.put(key, val);
		decayTasks.put(key, new StackDecayTask(key, decayTime));
	}

	@Override
	public void onSelfMutation(Runnable cb) {
		this.selfMutationListeners.add(cb);
	}

	public void resetDecayTimer(K key) {
		StackDecayTask task = decayTasks.get(key);
		if (task != null) {
			task.cancel();
			decayTasks.put(key, new StackDecayTask(key, task.decayTime));
		}
	}

	private class StackDecayTask extends BukkitRunnable {

		private K key;
		private long decayTime;

		private StackDecayTask(K key, long decayTime) {
			this.key = key;
			this.startTimer();
			this.decayTime = decayTime;
		}

		private void startTimer() {
			this.runTaskTimer(plugin, decayTime, decayTime);
		}

		@Override
		public void run() {
			remove(key, 1);
			selfMutationListeners.forEach(Runnable::run);
		}

	}

}
