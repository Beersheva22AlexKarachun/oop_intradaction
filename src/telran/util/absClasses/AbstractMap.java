package telran.util.absClasses;

import java.util.Objects;

import telran.util.LinkedList;
import telran.util.interfaces.Collection;
import telran.util.interfaces.Map;
import telran.util.interfaces.Set;

public abstract class AbstractMap<K, V> implements Map<K, V> {
	protected Set<Entry<K, V>> set;

	public AbstractMap() {
	};

	public AbstractMap(Map<K, V> map) {
		putAll(map);
	}

	public int size() {
		return set.size();
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public void putAll(Map<K, V> map) {
		for (Map.Entry<K, V> entry : map.entrySet())
			put(entry.getKey(), entry.getValue());
	}

	@Override
	public V put(K key, V value) {
		V res = null;
		Entry<K, V> entry = set.get(new Entry<>(key, null));
		if (entry != null) {
			res = entry.getValue();
			entry.setValue(value);
		} else {
			set.add(new Entry<>(key, value));
		}
		return res;
	}

	@Override
	public V get(K key) {
		Entry<K, V> entry = set.get(new Entry<>(key, null));
		return entry != null ? entry.getValue() : null;
	}

	@Override
	public boolean containsValue(V value) {
		return set.stream().anyMatch(x -> x.getValue().equals(value));
	}

	@Override
	public Collection<V> values() {
		Collection<V> list = new LinkedList<>();
		set.forEach(x -> list.add(x.getValue()));
		return list;
	}

	@Override
	public Set<K> keySet() {
		try {
			@SuppressWarnings("unchecked")
			Set<K> res = set.getClass().getConstructor().newInstance();
			set.forEach(x -> res.add(x.getKey()));
			return res;
		} catch (Exception e) {
			throw new IllegalStateException();
		}
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		try {
			@SuppressWarnings("unchecked")
			Set<Entry<K, V>> res = set.getClass().getConstructor().newInstance();
			set.forEach(res::add);
			return res;
		} catch (Exception e) {
			throw new IllegalStateException();
		}
	}

	@Override
	public V remove(K key) {
		V res = null;
		Entry<K, V> entry = set.get(new Entry<>(key, null));
		if (entry != null) {
			set.remove(entry);
			res = entry.getValue();
		}
		return res;
	}

	@Override
	public int hashCode() {
		return Objects.hash(set);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractMap other = (AbstractMap) obj;
		return set.equals(other.set);
	}
}