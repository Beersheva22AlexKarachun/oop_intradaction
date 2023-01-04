package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class LinkedList<T> implements List<T> {
	private static class Node<T> {
		T obj;
		Node<T> prev;
		Node<T> next;

		Node(T obj) {
			this.obj = obj;
		}
	}

	private Node<T> head;
	private Node<T> tail;
	private int size;

	private class LinkedListIterator implements Iterator<T> {
		Node<T> current = head;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T obj = current.obj;
			current = current.next;
			return obj;
		}
	}

	public LinkedList() {
	};

	public LinkedList(Collection<T> collection) {
		for (T item : collection.toArray()) {
			add(item);
		}
	}

	@Override
	public boolean add(T element) {
		Node<T> node = getNewNode(element);
		if (head == null) {
			head = tail = node;
		} else {
			tail.next = node;
			node.prev = tail;
			tail = node;
		}
		return true;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		int oldSize = size;
		Node<T> current = head;
		Node<T> next = null;
		for (int i = 0; i < oldSize; i++) {
			next = current.next;
			if (predicate.test(current.obj)) {
				unlink(current);
			}
			current = next;
		}
		return oldSize > size;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public T[] toArray(T[] ar) {
		if (ar.length < size) {
			ar = Arrays.copyOf(ar, size);
		}
		Iterator<T> it = iterator();
		for (int i = 0; i < size; i++) {
			ar[i] = it.next();
		}
		Arrays.fill(ar, size, ar.length, null);
		return ar;
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}

	@Override
	public void add(int index, T element) {
		checkIndex(index, 0, size);
		if (index == size) {
			add(element);
		} else if (index == 0) {
			addHead(element);
		} else {
			addMiddle(index, element);
		}
	}

	private void addMiddle(int index, T element) {
		Node<T> node = getNewNode(element);
		Node<T> next = getNode(index);
		Node<T> prev = next.prev;
		prev.next = node;
		node.prev = prev;
		next.prev = node;
		node.next = next;
	}

	private Node<T> getNode(int index) {
		return index < size >> 1 ? getNodeFromLeft(index) : getNodeFromRight(index);
	}

	private Node<T> getNodeFromRight(int index) {
		Node<T> current = tail;
		for (int i = size - 1; i > index; i--) {
			current = current.prev;
		}
		return current;
	}

	private Node<T> getNodeFromLeft(int index) {
		Node<T> current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current;
	}

	private void addHead(T element) {
		Node<T> node = getNewNode(element);
		node.next = head;
		head.prev = node;
		head = node;
	}

	public Node<T> getNewNode(T element) {
		Node<T> node = new Node<>(element);
		size++;
		return node;
	}

	@Override
	public T remove(int index) {
		checkIndex(index, 0, size - 1);
		Node<T> node = getNode(index);
		T object = node.obj;
		unlink(node);
		return object;
	}

	private void unlink(Node<T> node) {
		Node<T> next = node.next;
		Node<T> prev = node.prev;
		if (prev == null) {
			head = next;
		} else {
			prev.next = next;
			node.prev = null;
		}

		if (next == null) {
			tail = prev;
		} else {
			next.prev = prev;
			node.next = null;
		}
		node.obj = null;
		size--;
	}

	@Override
	public int indexOf(T pattern) {
		Node<T> current = head;
		int index = 0;
		while (index < size && !isEqual(current.obj, pattern)) {
			current = current.next;
			index++;
		}
		return index == size ? -1 : index;
	}

	@Override
	public int lastIndexOf(T pattern) {
		Node<T> current = tail;
		int index = size - 1;
		while (index > -1 && !isEqual(current.obj, pattern)) {
			current = current.prev;
			index--;
		}
		return index;
	}

	@Override
	public T get(int index) {
		checkIndex(index, 0, size - 1);
		return getNode(index).obj;
	}

	@Override
	public void set(int index, T element) {
		checkIndex(index, 0, size - 1);
		getNode(index).obj = element;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T[] toArray() {
		T[] array = (T[]) new Object[size];
		Iterator<T> it = iterator();
		for (int i = 0; i < size; i++) {
			array[i] = it.next();
		}
		return array;
	}

	@Override
	public void clear() {
		removeIf(x -> true);
	}
}