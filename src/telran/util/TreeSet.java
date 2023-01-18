package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TreeSet<T> extends AbstractCollection<T> implements Sorted<T> {
	static private class Node<T> {
		T obj;
		Node<T> parent;
		Node<T> left;
		Node<T> right;

		Node(T obj) {
			this.obj = obj;
		}
	}

	private class TreeSetIterator implements Iterator<T> {
		private Node<T> current;
		private Node<T> prevNode = null;
		private boolean flNext;

		public TreeSetIterator() {
			current = root != null ? getLeastNode(root) : root;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			prevNode = current;
			flNext = true;

			current = getNextNode(current);
			return prevNode.obj;
		}

		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			removeNode(prevNode);
			flNext = false;
		}
	}

	private Node<T> root;
	private Comparator<T> comp;

	public TreeSet(Comparator<T> comp) {
		this.comp = comp;
	}

	@SuppressWarnings("unchecked")
	public TreeSet() {
		this((Comparator<T>) Comparator.naturalOrder());
	}

	public TreeSet(Collection<T> collection) {
		this();
		for (T item : collection) {
			add(item);
		}
	}

	private Node<T> getLeastNode(Node<T> node) {
		while (node.left != null)
			node = node.left;
		return node;
	}

	private Node<T> getLargestNode(Node<T> node) {
		while (node.right != null)
			node = node.right;
		return node;
	}

	private Node<T> getGreaterParent(Node<T> node) {
		while (node.parent != null && node.parent.left != node) {
			node = node.parent;
		}
		return node.parent;
	}

	private Node<T> getLesserParent(Node<T> node) {
		while (node.parent != null && node.parent.right != node) {
			node = node.parent;
		}
		return node.parent;
	}

	private Node<T> getNextNode(Node<T> node) {
		return node.right == null ? getGreaterParent(node) : getLeastNode(node.right);
	}

	@Override
	public boolean add(T element) {
		boolean res = false;
		Node<T> parent = getNode(element);
		int compRes = 0;
		if (parent == null || (compRes = comp.compare(element, parent.obj)) != 0) {
			res = true;
			size++;
			Node<T> node = new Node<>(element);
			node.parent = parent;
			if (parent == null) {
				root = node;
			} else {
				if (compRes < 0) {
					parent.left = node;
				} else {
					parent.right = node;
				}
			}
		}

		return res;
	}

	@Override
	public boolean remove(T pattern) {
		Node<T> removed = getNode(pattern);
		boolean res = false;
		if (removed != null && comp.compare(pattern, removed.obj) == 0) {
			res = true;
			removeNode(removed);
		}
		return res;
	}

	private void removeNode(Node<T> removed) {
		size--;
		if (removed == root) {
			removeRoot();
		} else if (removed.left != null && removed.right != null) {
			removeNodeWithTwoChildren(removed);
		} else {
			removeLineNode(removed);
		}
	}

	private void removeRoot() {
		if (root.left == null && root.right == null) {
			root = null;
		} else if (root.right == null || root.left == null) {
			root = root.right != null ? root.right : root.left;
			root.parent = null;
		} else {
			removeNodeWithTwoChildren(root);
		}
	}

	private void removeNodeWithTwoChildren(Node<T> removed) {
		Node<T> least = getLeastNode(removed.right);
		Node<T> parent = removed.parent;
		removeLineNode(least);

		least.parent = removed.parent;
		least.left = removed.left;
		least.right = removed.right;

		removed.left.parent = least;
		if (removed.right != null) {
			removed.right.parent = least;
		}
		if (parent != null) {
			if (comp.compare(parent.obj, removed.obj) > 0) {
				parent.left = least;
			} else {
				parent.right = least;
			}
		}
	}

	private void removeLineNode(Node<T> removed) {
		Node<T> parent = removed.parent;
		Node<T> child = removed.left != null ? removed.left : removed.right;
		if (comp.compare(removed.obj, parent.obj) > 0) {
			parent.right = child;
		} else {
			parent.left = child;
		}
		if (child != null) {
			child.parent = parent;
		}
	}

	private Node<T> getNode(T element) {
		Node<T> current = root;
		Node<T> parent = null;
		int compRes;
		while (current != null && (compRes = comp.compare(element, current.obj)) != 0) {
			parent = current;
			current = compRes < 0 ? current.left : current.right;
		}
		return current == null ? parent : current;
	}

	@Override
	public boolean contains(T pattern) {
		Node<T> node = getNode(pattern);
		return node != null && comp.compare(pattern, node.obj) == 0;
	}

	@Override
	public Iterator<T> iterator() {
		return new TreeSetIterator();
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
		TreeSet<T> other = (TreeSet<T>) obj;
		res = size == other.size();
		if (res) {
			Iterator<T> itThis = iterator();
			Iterator<T> itOther = other.iterator();
			while (itThis.hasNext() && res) {
				res = itThis.next().equals(itOther.next());
			}
		}
		return res;
	}

	@Override
	public T floor(T element) {
		Node<T> node = getNode(element);
		if (node != null && comp.compare(element, node.obj) < 0) {
			node = getLesserParent(node);
		}
		return node == null ? null : node.obj;
	}

	@Override
	public T ceiling(T element) {
		Node<T> node = getNode(element);
		if (node != null && comp.compare(element, node.obj) > 0) {
			node = getGreaterParent(node);
		}
		return node == null ? null : node.obj;
	}

	@Override
	public T first() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return getLeastNode(root).obj;
	}

	@Override
	public T last() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return getLargestNode(root).obj;
	}
}