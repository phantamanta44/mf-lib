package io.github.phantamanta44.mobafort.lib.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public class CollectionUtils {

    public static <K, V> OneToManyMap<K, V, List<V>> groupByProperty(Collection<V> coll, Function<V, K> getter) {
        return groupByProperty(coll, getter, ArrayList::new);
    }

    public static <K, V> OneToManyMap<K, V, List<V>> groupByProperty(Collection<V> coll, Function<V, K> getter, Supplier<List<V>> factory) {
        OneToManyMap<K, V, List<V>> map = new OneToManyMap<>(factory);
        coll.forEach(e -> map.put(getter.apply(e), e));
        return map;
    }

    public static <E, C extends Collection<E>> E random(C coll) {
        Object[] elems = coll.toArray();
        return (E)elems[(int)Math.floor(Math.random() * elems.length)];
    }

}
