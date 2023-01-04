package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class ArrayList<T> implements List<T> {
	static final int DEFAULT_CAPACITY = 16;
	private T[] array;
	private int size;

	private class ArrayListIterator implements Iterator<T> {
		private int index = 0;

		@Override
		public boolean hasNext() {
			return index < size;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return array[index++];
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
		size = collection.size();
		array = Arrays.copyOf(collection.toArray(), size);
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
	public boolean removeIf(Predicate<T> predicate) {
		int oldSize = size;
		int tIndex = 0;
		for (int i = 0; i < oldSize; i++) {
			if (predicate.test(array[i])) {
				size--;
			} else {
				array[tIndex++] = array[i];
			}
		}
		Arrays.fill(array, size, oldSize, null);
		return oldSize > size;

	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean contains(T item) {
		return indexOf(item) != -1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		T[] res = (T[]) new Object[size];
		System.arraycopy(array, 0, res, 0, size);
		return res;
	}

	@Override
	public T[] toArray(T[] ar) {
		if (ar.length < size) {
			ar = Arrays.copyOf(array, size);
		}
		System.arraycopy(array, 0, ar, 0, size);
		Arrays.fill(ar, size, ar.length, null);
		return ar;
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

	@Override
	public void clear() {
		Arrays.fill(array, null);
		size = 0;
	}
}