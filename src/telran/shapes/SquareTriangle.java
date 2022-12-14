package telran.shapes;

import java.util.Arrays;

public class SquareTriangle extends Square {
	private boolean isLeftDiagonal;

	protected SquareTriangle(int size, boolean isLeftDiagonal) {
		super(size);
		this.isLeftDiagonal = isLeftDiagonal;
	}
	@Override
	public String[] presentation(int offset) {
		int height = getHeight();
		
		char[][] presentationBuffer = new char[height - 1][offset + height];
		fillBuffer(presentationBuffer, offset);
		return getLines(presentationBuffer, offset);
		
	}
	private String[] getLines(char[][] buffer, int offset) {
		String[] res = new String[getHeight()];
		for(int i = 0; i < buffer.length; i++) {
			res[i] = new String(buffer[i]);
		}
		res[res.length - 1] = getLine(offset);
		return res;
	}
	private void fillBuffer(char[][] buffer, int offset) {
		int edgePos = isLeftDiagonal ? offset : buffer[0].length - 1;
		char symbol = getSymbol().charAt(0);
		for (int i = 0; i < buffer.length; i++) {
			Arrays.fill(buffer[i], ' ');
			int diagonalPos = isLeftDiagonal ? edgePos + i : edgePos - i;
			buffer[i][edgePos] = symbol;
			buffer[i][diagonalPos] = symbol;
		}
		
	}
	
}