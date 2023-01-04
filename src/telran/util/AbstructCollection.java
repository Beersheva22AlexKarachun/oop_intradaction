package telran.util;

public abstract class AbstructCollection<T> implements Collection<T> {
	protected int size;
  
	public int size() {
		return size;
	}
}
