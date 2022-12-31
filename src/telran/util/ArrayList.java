package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

public class ArrayList<T> implements List<T>, Iterable<T> {
	static final int DEFAULT_CAPACITY = 16;
	private T[] array;
	private int size;

	private class ArrayListIterator implements Iterator<T> {
		private int index = 0;

		@Override
		public boolean hasNext() {
			return index + 1 < size;
		}

		@Override
		public T next() {
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
		int shift = 0;
		for (int i = 0; i < size; i++) {
			if (predicate.test(array[i])) {
				shift++;
			} else {
				array[i - shift] = array[i];
			}
		}
		if (shift != 0) {
			Arrays.fill(array, size - shift, size - 1, null);
			size -= shift;
		}
		return shift != 0;
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

	@Override
	public T[] toArray() {
		return Arrays.copyOf(array, size);
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

	private void checkIndex(int index, int min, int max) {
		if (index > max || index < min)
			throw new IndexOutOfBoundsException(String.format("Index %1$s out of bounds for size %2$s", index, size));
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

	private boolean isEqual(T item1, T item2) {
		return item1 == null ? item1 == item2 : item1.equals(item2);
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