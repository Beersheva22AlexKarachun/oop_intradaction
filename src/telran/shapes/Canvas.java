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
		String[] res = getPresentationWithOutChange(shapes[0], offset);
		offset = isDirectionRow() ? margin : offset;
		for (int i = 1; i < shapes.length; i++) {
			String[] presentation = getPresentationWithOutChange(shapes[i], offset);
			res = joinArrays(res, presentation);
		}
		return res;
	}

	private boolean isDirectionRow() {
		return direction.equals(ROW);
	}

	private String[] getPresentationWithOutChange(Shape shape, int offset) {
		String savedDirection = (shape instanceof Canvas) ? ((Canvas) shape).getDirection() : null;
		int savedValue = isDirectionRow() ? shape.getHeight() : shape.getWidth();
		int newValue = isDirectionRow() ? getHeight() : getWidth();

		castShape(shape, newValue, getDirection());
		String[] presentation = shape.presentation(offset);
		castShape(shape, savedValue, savedDirection);

		return presentation;
	}

	private void castShape(Shape shape, int newValue, String direction) {
		if (isDirectionRow()) {
			shape.setHeight(newValue);
		} else {
			shape.setWidth(newValue);
		}

		if (shape instanceof Canvas) {
			((Canvas) shape).setDirection(direction);
		}
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