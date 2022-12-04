package telran.shapes;

import java.util.Arrays;

public class SquareTriangle extends Square {
	private boolean isLeftDiagonal;

	protected SquareTriangle(int size, boolean isLeftDiagonal) {
		super(size);
		this.isLeftDiagonal = isLeftDiagonal;
	}

	public String[] presentation(int offset) {
		String[] res = new String[getHeight()];
		char[][] triangleMatrix = getTriangle();
		String strOffset = getOffset(offset);
		for (int i = 0; i < res.length; i++) {
			res[i] = strOffset + new String(triangleMatrix[i]);
		}
		return res;
	}

	private char[][] getTriangle() {
		char[][] res = getVerticalLine();
		int width = getWidth();
		int index;
		for (int i = 0; i < res.length - 1; i++) {
			index = isLeftDiagonal ? i : width - i - 1;
			addSymbolAtIndex(res[i], index);
		}
		res[width - 1] = getLine();
		return res;
	}

	private char[] getLine() {
		char[] res = new char[getWidth()];
		Arrays.fill(res, getSymbol().charAt(0));
		return res;
	}

	private char[][] getVerticalLine() {
		char[][] res = new char[getHeight()][getWidth()];
		int index = isLeftDiagonal ? 0 : getWidth() - 1;
		for (int i = 0; i < res.length; i++) {
			Arrays.fill(res[i], ' ');
			addSymbolAtIndex(res[i], index);
		}
		return res;
	}

	private void addSymbolAtIndex(char[] arr, int index) {
		arr[index] = getSymbol().charAt(0);
	}

	public boolean getIsLeftDiagonal() {
		return isLeftDiagonal;
	}

}
