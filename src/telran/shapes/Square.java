package telran.shapes;

public class Square extends Rectangle {

	public Square(int size) {
		super(size, size);
	}

	private void setSize(int value) {
		super.setWidth(value);
		super.setHeight(value);
	}

	public void setWidth(int value) {
		setSize(value);
	}

	public void setHeight(int value) {
		setSize(value);
	}

}
