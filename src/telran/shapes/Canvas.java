package telran.shapes;

public class Canvas extends Shape {
	private Shape[] shapes;
	private String direction = ROW;
	private int margin = 5;
	private static final String ROW = "row";
	private static final String COLUMN = "column";

	public Canvas(int width, int height, Shape[] shapes) {
		super(width, height);
		this.shapes = shapes;
	}

	@Override
	public String[] presentation(int offset) {
		String[] res = getPresentationWithOutChange(0, offset);
		offset = isDirectionRow() ? margin : offset;
		for (int i = 1; i < shapes.length; i++) {
			String[] presentation = getPresentationWithOutChange(i, offset);
			res = joinArrays(res, presentation);
		}
		return res;
	}

	private boolean isDirectionRow() {
		return direction.equals(ROW);
	}

	private String[] getPresentationWithOutChange(int i, int offset) {
		int savedValue = isDirectionRow() ? shapes[i].getHeight() : shapes[i].getWidth();
		if (isDirectionRow())
			shapes[i].setHeight(getHeight());
		else
			shapes[i].setWidth(getWidth());

		String[] presentation = shapes[i].presentation(offset);

		if (isDirectionRow())
			shapes[i].setHeight(savedValue);
		else
			shapes[i].setWidth(savedValue);

		return presentation;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		if (direction.equalsIgnoreCase(ROW) || direction.equalsIgnoreCase(COLUMN)) {
			this.direction = direction;
		}
	}

	public int getMargin() {
		return margin;
	}

	public void setMargin(int margin) {
		this.margin = margin > 0 ? margin : 0;
	}

	private String[] joinArrays(String[] array1, String[] array2) {
		return isDirectionRow() ? joinArraysHorizontal(array1, array2) : joinArraysVertical(array1, array2);
	}

	private String[] joinArraysVertical(String[] array1, String[] array2) {
		String[] res;
		res = new String[array1.length + array2.length + margin];
		int i, offset = array1.length + margin;

		for (i = 0; i < array1.length; i++)
			res[i] = array1[i];

		for (i = array1.length; i < offset; i++)
			res[i] = getOffset(getWidth());

		for (i = offset; i < offset + array2.length; i++)
			res[i] = array2[i - offset];
		return res;
	}

	private String[] joinArraysHorizontal(String[] array1, String[] array2) {
		String[] res;
		res = new String[array1.length];
		for (int i = 0; i < res.length; i++) {
			res[i] = array1[i] + array2[i];
		}
		return res;
	}
}