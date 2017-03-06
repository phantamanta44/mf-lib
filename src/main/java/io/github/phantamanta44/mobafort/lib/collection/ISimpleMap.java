package io.github.phantamanta44.mobafort.lib.collection;

import java.util.Map;
import java.util.Set;

public interface ISimpleMap<K, V> extends ISimpleCollection<K> {

    void put(K key, V val);

    V get(K key);

    boolean remove(K key);

    boolean contains(K key);

    void clear();

    Set<Map.Entry<K, V>> entrySet();

}
