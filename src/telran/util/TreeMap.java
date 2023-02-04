package telran.util;

import telran.util.absClasses.AbstractMap;

public class TreeMap<K, V> extends AbstractMap<K, V> {
	public TreeMap() {
		set = new TreeSet<>();
	}
}