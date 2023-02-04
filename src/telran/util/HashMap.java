package telran.util;

import telran.util.absClasses.AbstractMap;

public class HashMap<K, V> extends AbstractMap<K, V> {
	public HashMap() {
		set = new HashSet<>();
	}
}