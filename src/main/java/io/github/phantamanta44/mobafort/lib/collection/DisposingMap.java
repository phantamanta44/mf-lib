package io.github.phantamanta44.mobafort.lib.collection;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DisposingMap<K, V, C extends ISelfMutatingCollection<V>> implements ISimpleMap<K, C>, ISelfMutatingCollection<K> {

    protected final Map<K, C> backing;
    private final Collection<Runnable> selfMutationListeners;

    public DisposingMap() {
        this.backing = new ConcurrentHashMap<>();
        this.selfMutationListeners = new LinkedList<>();
    }

    public C get(K key) {
        return backing.get(key);
    }

    public void put(K key, C val) {
        backing.put(key, val);
        val.onSelfMutation(() -> {
            if (val.isEmpty()) {
                remove(key);
                selfMutationListeners.forEach(Runnable::run);
            }
        });
    }

    public boolean remove(K key) {
        return backing.remove(key) != null;
    }

    @Override
    public int size() {
        return backing.size();
    }

    @Override
    public boolean isEmpty() {
        return backing.isEmpty();
    }

    public boolean contains(K key) {
        return backing.containsKey(key);
    }

    public void clear() {
        backing.clear();
    }

    @Override
    public Set<Map.Entry<K, C>> entrySet() {
        return backing.entrySet();
    }

    @Override
    public void onSelfMutation(Runnable cb) {
        selfMutationListeners.add(cb);
    }

}
