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
		private Node<T> current = root;
		private Node<T> prevNode = null;
		private boolean flNext;

		public TreeSetIterator() {
			if (current != null) {
				current = getLeastNode(current);
			}
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

			if (current.right != null) {
				current = current.right;
				current = getLeastNode(current);
			} else {
				current = getGreaterParent(current);
			}
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

	@Override
	public boolean add(T element) {
		Node<T> newNode = new Node<T>(element);
		Node<T> current = root;
		if (current == null) {
			root = newNode;
		} else {
			while (current.left != newNode && current.right != newNode) {
				int compRes = comp.compare(element, current.obj);
				if (compRes > 0) {
					if (current.right == null) {
						current.right = newNode;
					} else {
						current = current.right;
					}
				} else if (compRes < 0) {
					if (current.left == null) {
						current.left = newNode;
					} else {
						current = current.left;
					}
				} else {
					return false;
				}
			}
			newNode.parent = current;
		}
		size++;
		return true;
	}

	@Override
	public boolean remove(T pattern) {
		Node<T> removed = getNode(pattern);
		boolean res = false;
		if (removed != null) {
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
			removeListNode(removed);
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
		removeListNode(least);

		least.parent = removed.parent;
		least.left = removed.left;
		least.right = removed.right;

		removed.left.parent = least;
		if (removed.right != null) {
			removed.right.parent = least;
		}
		if (removed.parent != null) {
			if (comp.compare(removed.parent.obj, removed.obj) > 0) {
				removed.parent.left = least;
			} else {
				removed.parent.right = least;
			}
			;
		}
	}

	private void removeListNode(Node<T> removed) {
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

	private Node<T> getNode(T pattern) {
		Node<T> current = root;
		int compRes;
		while (current != null && (compRes = comp.compare(pattern, current.obj)) != 0) {
			current = compRes > 0 ? current.right : current.left;
		}
		return current;
	}

	@Override
	public boolean contains(T pattern) {
		return getNode(pattern) != null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T ceiling(T element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T first() {
		return root != null ? getLeastNode(root).obj : null;
	}

	@Override
	public T last() {
		return root != null ? getLargestNode(root).obj : null;
	}

}