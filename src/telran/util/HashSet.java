package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashSet<T> extends AbstractCollection<T> implements Set<T> {
	private static final int DEFAULT_TABLE_SIZE = 16;
	private static final float DEFAULT_FACTOR = 0.75f;
	private List<T>[] hashTable;
	private float factor;

	private class HashSetIterator implements Iterator<T> {
		int index = 0;
		int currentItIndex = 0;
		Iterator<T> currentIt;
		private boolean flNext;
		int amount = 0;

		@Override
		public boolean hasNext() {
			return amount < size;
		}

		private boolean hasNextInCurIt() {
			return currentIt != null && currentIt.hasNext();
		}

		private int getNextIndex() {
			while (index < hashTable.length && hashTable[index] == null) {
				index++;
			}
			return index;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			if (!hasNextInCurIt()) {
				getNextIndex();
				currentItIndex = index;
				currentIt = hashTable[index++].iterator();
			}
			flNext = true;
			amount++;
			return currentIt.next();
		}

		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			currentIt.remove();
			flNext = false;
			size--;
			amount--;
			if (hashTable[currentItIndex].isEmpty()) {
				hashTable[currentItIndex] = null;
			}
		}

	}

	@SuppressWarnings("unchecked")
	public HashSet(int tableSize, float factor) {
		if (tableSize < 1) {
			throw new IllegalArgumentException("Wrong initial size of Hash Table");
		}
		if (factor < 0.3 || factor > 1) {
			throw new IllegalArgumentException("Wrong factor value");
		}
		hashTable = new List[tableSize];
		this.factor = factor;
	}

	public HashSet() {
		this(DEFAULT_TABLE_SIZE, DEFAULT_FACTOR);
	}

	public HashSet(Collection<T> collection) {
		this(collection.size() * 2, DEFAULT_FACTOR);
		for (T item : collection) {
			add(item);
		}
	}

	@Override
	public boolean add(T element) {
		if (size >= hashTable.length * factor) {
			tableRecreation();
		}
		int index = getHashIndex(element);
		boolean res = false;
		List<T> list = hashTable[index];
		if (list == null) {
			list = new LinkedList<>();
			hashTable[index] = list;
		}
		if (!list.contains(element)) {
			res = true;
			list.add(element);
			size++;
		}
		return res;
	}

	private void tableRecreation() {
		HashSet<T> hashSet = new HashSet<>(hashTable.length * 2, factor);
		for (List<T> list : hashTable) {
			if (list != null) {
				for (T obj : list) {
					hashSet.add(obj);
				}
			}
		}
		hashTable = hashSet.hashTable;
	}

	private int getHashIndex(T element) {
		return Math.abs(element.hashCode()) % hashTable.length;
	}

	@Override
	public boolean remove(T pattern) {
		int index = getHashIndex(pattern);
		boolean res = false;
		if (hashTable[index] != null) {
			res = hashTable[index].remove(pattern);
			if (res) {
				size--;
				if (hashTable[index].isEmpty()) {
					hashTable[index] = null;
				}
			}
		}
		return res;
	}

	@Override
	public boolean contains(T pattern) {
		boolean res = false;
		int index = getHashIndex(pattern);
		if (hashTable[index] != null) {
			res = hashTable[index].contains(pattern);
		}
		return res;
	}

	@Override
	public Iterator<T> iterator() {

		return new HashSetIterator();
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
		HashSet<T> other = (HashSet<T>) obj;
		res = size == other.size();
		if (res) {
			Iterator<T> it = other.iterator();
			while (it.hasNext() && res) {
				res = this.contains(it.next());
			}
		}
		return res;
	}
}