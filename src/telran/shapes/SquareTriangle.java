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
		char[][] middleSection = getMiddleSection();

		for (int i = 0; i < res.length - 1; i++) {
			res[i] = getOffset(offset) + new String(middleSection[i]);
		}

		res[res.length - 1] = getOffset(offset) + getSymbol().repeat(getWidth());
		return res;

	}

	private char[][] getMiddleSection() {
		char[][] res = new char[getHeight() - 1][getWidth()];
		for (int i = 0; i < res.length; i++) {
			res[i] = getMiddleLine(i);
		}
		return res;
	}

	private char[] getMiddleLine(int index) {
		char[] res = new char[getWidth()];
		char symbol = getSymbol().charAt(0);
		Arrays.fill(res, ' ');
		int[] indexes = isLeftDiagonal ? new int[] { 0, index } : new int[] { getWidth() - index - 1, getWidth() - 1 };
		for (int ind : indexes) {
			res[ind] = symbol;
		}
		return res;
	}


	public boolean getIsLeftDiagonal() {
		return isLeftDiagonal;
	}

}
