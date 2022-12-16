package telran.shapes;

public abstract class Shape {
	private int width;
	private int height;
	public static final String SYMBOL = "*";

	protected static String symbol = SYMBOL;

	public Shape(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public static String getSymbol() {
		return symbol;
	}

	public static void setSymbol(String symbol) {
		Shape.symbol = symbol;
	}

	public final int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	protected final String getOffset(int offset) {
		return " ".repeat(offset);
	}

	public abstract String[] presentation(int offset);
}