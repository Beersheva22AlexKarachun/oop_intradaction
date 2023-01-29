package telran.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class MdArray<T> {
	private MdArray<T>[] array;
	private T value;

	public MdArray(int[] dimensions, T value) {
		this(dimensions, 0, value);
	}

	@SuppressWarnings("unchecked")
	private MdArray(int[] dimensions, int firstDim, T value) {
		if (firstDim == dimensions.length) {
			this.value = value;
			array = null;
		} else {
			this.value = null;
			array = new MdArray[dimensions[firstDim]];
			for (int i = 0; i < array.length; i++) {
				array[i] = new MdArray<>(dimensions, firstDim + 1, value);
			}
		}
	}

	public void forEach(Consumer<T> consumer) {
		forEach(this, consumer);
	}

	private void forEach(MdArray<T> currArray, Consumer<T> consumer) {
		if (currArray.array == null) {
			consumer.accept(currArray.value);
		} else {
			for (int i = 0; i < currArray.array.length; i++) {
				forEach(currArray.array[i], consumer);
			}
		}
	}

	public T[] toArray(T[] ar) {
		ArrayList<T> res = new ArrayList<>();
		forEach(res::add);
		return res.toArray(ar);
	}

	public T getValue(int[] route) {
		return findByRoute(route).value;
	}

	public void setValue(int[] route, T value) {
		findByRoute(route).value = value;
	}

	private MdArray<T> findByRoute(int[] route) {
		MdArray<T> res = this;
		for (int num : route) {
			if (res.array == null) {
				throw new NoSuchElementException();
			}
			res = res.array[num];
		}
		if (res.array != null) {
			throw new IllegalStateException();
		}
		return res;
	}
}