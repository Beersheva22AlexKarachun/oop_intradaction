package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

import telran.util.absClasses.AbstractCollection;
import telran.util.interfaces.Collection;
import telran.util.interfaces.List;

public class LinkedList<T> extends AbstractCollection<T> implements List<T> {
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

	private class LinkedListIterator implements Iterator<T> {
		Node<T> current = head;
		private boolean flNext = false;

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
			flNext = true;
			return obj;
		}

		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			Node<T> removedNode = current == null ? tail : current.prev;
			unlink(removedNode);
			flNext = false;
		}
	}

	public LinkedList() {
	};

	public LinkedList(Collection<T> collection) {
		super(collection);
	}

	/************************************************************************************/
	// Comments only for LinkedList task of loop existence
	public void setNext(int index1, int index2) {
		// sets next of element at index1 to element at index2
		checkIndex(index1, 0, size - 1);
		checkIndex(index2, 0, size - 1);
		if (index1 < index2) {
			throw new IllegalArgumentException();
		}
		getNode(index1).next = getNode(index2);
	}

	public boolean hasLoop() {
		Node<T> oneStep = head;
		Node<T> twoStep = head;
		boolean res = false;
		while (!res && twoStep != null && twoStep.next != null) {
			oneStep = oneStep.next;
			twoStep = twoStep.next.next;
			if (oneStep == twoStep) {
				res = true;
			}
		}
		return res;
	}

	/*********************************************************************************************/

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

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		boolean res = false;
		LinkedList<T> other = (LinkedList<T>) obj;
		res = size == other.size();
		if (res) {
			Iterator<T> it1 = this.iterator();
			Iterator<T> it2 = other.iterator();
			while (it1.hasNext() && res) {
				res = it1.next().equals(it2.next());
			}
		}
		return res;
	}
}