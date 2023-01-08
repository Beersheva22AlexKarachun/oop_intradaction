package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<T> extends AbstractCollection<T> implements List<T> {
	static final int DEFAULT_CAPACITY = 16;
	private T[] array;

	private class ArrayListIterator implements Iterator<T> {
		private int index = 0;
		private boolean flNext;

		@Override
		public boolean hasNext() {
			return index < size;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			flNext = true;
			return array[index++];
		}

		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			ArrayList.this.remove(--index);
			flNext = false;
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		array = (T[]) new Object[capacity];
	}

	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}

	public ArrayList(Collection<T> collection) {
		this(collection.size());
		for (T item : collection) {
			this.add(item);
		}
	}

	@Override
	public boolean add(T element) {
		checkSize();
		array[size++] = element;
		return true;
	}

	public void checkSize() {
		if (size == array.length) {
			reallocate();
		}
	}

	private void reallocate() {
		array = Arrays.copyOf(array, array.length * 2);
	}

	@Override
	public T remove(int index) {
		checkIndex(index, 0, size - 1);
		T res = array[index];
		size--;
		System.arraycopy(array, index + 1, array, index, size - index);
		array[size] = null;
		return res;
	}

	@Override
	public boolean remove(T item) {
		int index = indexOf(item);
		if (index > -1) {
			remove(index);
		}
		return index > -1;
	}

	@Override
	public void add(int index, T element) {
		checkIndex(index, 0, size);
		checkSize();
		System.arraycopy(array, index, array, index + 1, size - index);
		size++;
		array[index] = element;
	}

	@Override
	public int indexOf(T pattern) {
		int res = -1, i = 0;
		while (i < size && res == -1) {
			res = isEqual(array[i], pattern) ? i : res;
			i++;
		}
		return res;
	}

	@Override
	public int lastIndexOf(T pattern) {
		int res = -1, i = size - 1;
		while (i > 0 && res == -1) {
			res = isEqual(array[i], pattern) ? i : res;
			i--;
		}
		return res;
	}

	@Override
	public T get(int index) {
		checkIndex(index, 0, size - 1);
		return array[index];
	}

	@Override
	public void set(int index, T element) {
		checkIndex(index, 0, size - 1);
		array[index] = element;
	}

	@Override
	public Iterator<T> iterator() {
		return new ArrayListIterator();
	}

}