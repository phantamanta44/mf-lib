package io.github.phantamanta44.mobafort.lib.collection;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class TimedExpiryMap<K, V> implements ISelfMutatingCollection<K>, ISimpleMap<K, V> {

    private final Map<K, V> backing;
    private final Map<K, EntryRemovalTask> removalTasks;
    private final Collection<Runnable> selfMutationListeners;
    private final Plugin plugin;
    private long defaultExpiry;

    public TimedExpiryMap(Plugin plugin) {
        this(plugin, -1L);
    }

    public TimedExpiryMap(Plugin plugin, long defaultExpiry) {
        this.plugin = plugin;
        this.backing = new ConcurrentHashMap<>();
        this.removalTasks = new ConcurrentHashMap<>();
        this.selfMutationListeners = new ArrayList<>();
        this.defaultExpiry = defaultExpiry;
    }

    public long getDefaultExpiry() {
        return defaultExpiry;
    }

    public void setDefaultExpiry(long ticks) {
        this.defaultExpiry = ticks;
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
    public V get(K key) {
        return backing.get(key);
    }

    @Override
    public void put(K key, V val) {
        put(key, val, defaultExpiry);
    }

    public void put(K key, V val, long duration) {
        backing.put(key, val);
        if (duration > 0L)
            removalTasks.put(key, new EntryRemovalTask(key, duration));
    }

    @Override
    public boolean remove(K key) {
        if (backing.remove(key) != null) {
            EntryRemovalTask task = removalTasks.remove(key);
            if (task != null)
                task.cancel();
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        backing.clear();
        removalTasks.entrySet().removeIf(e -> {
            e.getValue().cancel();
            return true;
        });
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return backing.entrySet();
    }

    @Override
    public void onSelfMutation(Runnable cb) {
        selfMutationListeners.add(cb);
    }

    private class EntryRemovalTask extends BukkitRunnable {

        private K key;

        private EntryRemovalTask(K key, long delay) {
            this.key = key;
            this.runTaskLater(plugin, delay);
        }

        @Override
        public void run() {
            backing.remove(key);
            removalTasks.remove(key);
            selfMutationListeners.forEach(Runnable::run);
        }

    }

}
