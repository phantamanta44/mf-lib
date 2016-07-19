package io.github.phantamanta44.mobafort.lib.collection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class OneToManyMap<K, V, C extends Collection<V>> implements ISimpleCollection<K> {

	private final Map<K, C> backing;
	private final Supplier<C> factory;

	public OneToManyMap(Supplier<C> factory) {
		backing = new HashMap<>();
		this.factory = factory;
	}

	public void put(K key, V val) {
		C vals = backing.get(key);
		if (vals == null) {
			vals = factory.get();
			backing.put(key, vals);
		}
		vals.add(val);
	}

	public C get(K key) {
		return backing.get(key);
	}

	public boolean remove(K key, V val) {
		C vals = backing.get(key);
		if (vals != null) {
			boolean success = vals.remove(val);
			if (vals.isEmpty())
				backing.remove(key);
			return success;
		}
		return false;
	}

	public boolean clear(K key) {
		return backing.remove(key) != null;
	}

	public void clear() {
		backing.clear();
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

	public boolean contains(K key, V val) {
		C vals = backing.get(key);
		return vals != null && vals.contains(val);
	}

}