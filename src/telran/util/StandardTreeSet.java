package telran.util;

import java.util.Comparator;
import java.util.Iterator;

public class StandardTreeSet<T> implements Sorted<T> {
	private java.util.TreeSet<T> tree;

	public StandardTreeSet(Comparator<T> comp) {
		tree = new java.util.TreeSet<>(comp);
	}

	@SuppressWarnings("unchecked")
	public StandardTreeSet() {
		this((Comparator<T>) Comparator.naturalOrder());
	}

	@Override
	public boolean add(T element) {
		return tree.add(element);
	}

	@Override
	public boolean remove(T pattern) {
		return tree.remove(pattern);
	}

	@Override
	public boolean isEmpty() {
		return tree.isEmpty();
	}

	@Override
	public int size() {
		return tree.size();
	}

	@Override
	public boolean contains(T pattern) {
		return tree.contains(pattern);
	}

	@Override
	public Iterator<T> iterator() {
		return tree.iterator();
	}

	@Override
	public T floor(T element) {
		return tree.floor(element);
	}

	@Override
	public T ceiling(T element) {
		return tree.ceiling(element);
	}

	@Override
	public T first() {
		return tree.first();
	}

	@Override
	public T last() {
		return tree.last();
	}

}