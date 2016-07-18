package io.github.phantamanta44.mobafort.lib.collection;

import org.apache.commons.lang.mutable.MutableInt;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class TimedStackMap {

	private final Map<UUID, MutableInt> backing;
	private final Map<UUID, StackDecayTask> decayTasks;
	private final Plugin plugin;
	private long decayTime;

	public TimedStackMap(Plugin plugin, long decayTime) {
		if (decayTime < 1L)
			throw new IllegalArgumentException();
		this.plugin = plugin;
		this.backing = new ConcurrentHashMap<>();
		this.decayTasks = new ConcurrentHashMap<>();
		this.decayTime = decayTime;
	}

	public long getDecayTime() {
		return decayTime;
	}

	public void setDecayTime(long ticks) {
		this.decayTime = ticks;
	}

	public boolean contains(UUID key) {
		return backing.containsKey(key);
	}

	public int getStacks(UUID key) {
		MutableInt stacks = backing.get(key);
		return stacks != null ? stacks.intValue() : 0;
	}

	public void addStacks(UUID key, int amt) {
		if (amt < 0)
			throw new IllegalArgumentException();
		MutableInt stacks = backing.get(key);
		if (stacks == null)
			put(key, new MutableInt(amt));
		else
			stacks.setValue(stacks.intValue() + amt);
	}

	public void removeStacks(UUID key, int amt) {
		if (amt < 0)
			throw new IllegalArgumentException();
		MutableInt stacks = backing.get(key);
		if (stacks != null) {
			if (amt >= stacks.intValue())
				dropEntry(key);
			else
				stacks.setValue(stacks.intValue() - amt);
		}
	}

	public void setStacks(UUID key, int amt) {
		if (amt > 0) {
			MutableInt stacks = backing.get(key);
			if (stacks != null)
				stacks.setValue(amt);
			else
				put(key, new MutableInt(amt));
		}
		else
			dropEntry(key);
	}

	public boolean clearStacks(UUID key) {
		if (contains(key)) {
			dropEntry(key);
			return true;
		}
		return false;
	}

	public void clear() {
		backing.clear();
		decayTasks.entrySet().removeIf(e -> {
			e.getValue().cancel();
			return true;
		});
	}

	private boolean dropEntry(UUID key) {
		if (backing.remove(key) != null) {
			decayTasks.remove(key).cancel();
			return true;
		}
		return false;
	}

	private void put(UUID key, MutableInt stacks) {
		backing.put(key, stacks);
		decayTasks.put(key, new StackDecayTask(key));
	}

	private class StackDecayTask extends BukkitRunnable {

		private UUID key;

		private StackDecayTask(UUID key) {
			this.key = key;
			this.runTaskTimer(plugin, decayTime, decayTime);
		}

		@Override
		public void run() {
			removeStacks(key, 1);
		}

	}

}
