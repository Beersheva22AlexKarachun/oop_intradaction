package telran.shapes;

import java.util.Arrays;

public class Canvas1 extends Shape {
	private Shape[] shapes;
	private String direction = "row";
	private int margin = 5;

	public Canvas1(int width, int height, Shape[] shapes) {
		super(width, height);
		this.shapes = shapes;
	}
	@Override
	public int getHeight() {
		return direction.equals("row") ? super.getHeight() : (heightsSum() + margin * (shapes.length - 1));
	}

	@Override
	public String[] presentation(int offset) {
		
		return direction.equals("row") ? presentationInRow(offset) : presentationInColumn(offset);
	}

	private String[] presentationInColumn(int offset) {
		String res[] = new String[heightsSum() + (shapes.length - 1) * margin];
		Arrays.fill(res, " ".repeat(getWidth()));
		int length = mergeLines(0, res, getPresentation(0, offset));
		for(int i = 1; i < shapes.length; i++) {
			length = mergeLines(length + margin, res, getPresentation(i, offset));
		}
		return res;
	}

	private int heightsSum() {
		int sum = 0;
		for(Shape i: shapes) {
			sum += i.getHeight();
		}
		return sum;
	}

	private int mergeLines(int length, String[] res, String[] shapePresentation) {
		System.arraycopy(shapePresentation, 0, res, length, shapePresentation.length);
		return length + shapePresentation.length;
	}

	private String[] presentationInRow(int offset) {
		String[] res = getPresentation(0, offset);
		for(int i = 1; i < shapes.length; i++) {
			res = join(res, getPresentation(i, margin));
		}
		return res;
	}

	private String[] join(String[] res, String[] cur) {
		String [] joinRes = new String[res.length];
		for(int i = 0; i < joinRes.length; i++) {
			joinRes[i] = res[i] + cur[i];
		}
		return joinRes;
	}

	private String[] getPresentation(int shapeIndex, int offset) {
		if (direction.equals("row")) {
			shapes[shapeIndex].setHeight(getHeight());
		} else {
			shapes[shapeIndex].setWidth(getWidth());
		}
		return shapes[shapeIndex].presentation(offset);
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public void setMargin(int margin) {
		this.margin = margin;
	}

	

}