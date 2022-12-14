package telran.shapes;

public class Rectangle extends Shape {

	public static final String SYMBOL = "*";
	private static String symbol = SYMBOL;

	public Rectangle(int width, int height) {
		super(width, height);
	}

	public String[] presentation(int offset) {
		String[] res = new String[getHeight()];
		res[0] = getLine(offset);
		int lastLine = getHeight() - 1;
		res[lastLine] = getLine(offset);
		for (int i = 1; i < lastLine; i++) {
			res[i] = getMiddleLine(offset);
		}
		return res;
	}

	private String getMiddleLine(int offset) {
		return getOffset(offset) + symbol + getOffset(getWidth() - 2) + symbol;
	}

	protected String getLine(int offset) {
		return getOffset(offset) + symbol.repeat(getWidth());
	}

	public static String getSymbol() {
		return symbol;
	}

	public static void setSymbol(String symbol) {
		Rectangle.symbol = symbol;
	}

}
