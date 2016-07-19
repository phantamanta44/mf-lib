package io.github.phantamanta44.mobafort.lib.collection;

import java.util.function.Supplier;

public class DisposingNestedMap<K, k, v, C extends ISelfMutatingCollection<k> & ISimpleMap<k, v>> extends DisposingMap<K, k, C> {

	private Supplier<C> factory;

	public DisposingNestedMap(Supplier<C> factory) {
		super();
		this.factory = factory;
	}

	public v get(K key, k key2) {
		C vals = backing.get(key);
		return vals != null ? vals.get(key2) : null;
	}

	public void put(K key, k key2, v val) {
		C vals = backing.get(key);
		if (vals == null) {
			vals = factory.get();
			backing.put(key, vals);
		}
		vals.put(key2, val);
	}

}
