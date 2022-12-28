package telran.util;

import java.util.Arrays;
import java.util.function.Predicate;

public class ArrayList<T> implements List<T> {
	static final int DEFAULT_CAPACITY = 16;
	private T[] array;
	private int size;

	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		array = (T[]) new Object[capacity];
	}

	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}

	@Override
	public boolean add(T element) {
		if (size == array.length) {
			reallocate();
		}
		array[size++] = element;
		return true;
	}

	private void reallocate() {
		array = Arrays.copyOf(array, array.length * 2);
	}

	@Override
	public boolean remove(T item) {
		int i = 0;
		boolean res = false;
		while (i < size && !array[i].equals(item)) {
			i++;
		}
		if (i != size) {
			System.arraycopy(array, i + 1, array, i, size-- - i);
			res = true;
		}
		return res;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		int oldSize = size;
		int numberOfConsecutive = 0;
		int i = 0;
		while(i < size) {
			if (predicate.test(array[i])) {
				numberOfConsecutive++;
			} else {
				if (numberOfConsecutive > 0) {
					System.arraycopy(array, i, array, i - numberOfConsecutive, size - i);
					i -= numberOfConsecutive;
					size -= numberOfConsecutive;
					numberOfConsecutive = 0;
				}
			}
			i++;
		}
		if (numberOfConsecutive > 0) {
			size--;
		}
		return oldSize != size;
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
		int index = 0;
		while (index < size && !array[index].equals(item)) {
			index++;
		}
		return index < size;
	}

	@Override
	public T[] toArray() {
		return Arrays.copyOf(array, size);
	}

	@Override
	public T[] toArray(T[] ar) {
		T[] res;
		if (ar.length < size) {
			res = toArray();
		} else {
			System.arraycopy(array, 0, ar, 0, size);
			if (ar.length > size)
				ar[size] = null;
			res = ar;
		}
		return res;
	}

	@Override
	public void add(int index, T element) {
		checkIndex(index, 0, size);
		if (index == size) {
			reallocate();
		}
		System.arraycopy(array, index, array, index + 1, size++ - index);
		array[index] = element;
	}

	@Override
	public T remove(int index) {
		checkIndex(index, 0, size - 1);
		T item = array[index];
		System.arraycopy(array, index + 1, array, index, size-- - index);
		return item;
	}

	private void checkIndex(int index, int min, int max) {
		if (index > max || index < min)
			throw new IndexOutOfBoundsException(String.format("Index %1$s out of bounds for size %2$s", index, size));
	}

	@Override
	public int indexOf(T pattern) {
		int res = -1, i = 0;
		while (i < size && res == -1) {
			res = array[i].equals(pattern) ? i : res;
			i++;
		}
		return res;
	}

	@Override
	public int lastIndexOf(T pattern) {
		int res = -1, i = size - 1;
		while (i > 0 && res == -1) {
			res = array[i].equals(pattern) ? i : res;
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

}