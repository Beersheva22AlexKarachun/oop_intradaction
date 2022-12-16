package telran.shapes.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;

import telran.shapes.Canvas;
import telran.shapes.Canvas1;
import telran.shapes.Rectangle;
import telran.shapes.Shape;
import telran.shapes.Square;
import telran.shapes.SquareLeftTriangle;
import telran.shapes.SquareRightTriangle;

import org.junit.jupiter.api.Test;

class ShapeTest {

	@Test
	@Disabled
	void testRect() {
		int width = 10;
		int height = 8;
		int offset = 10;
		Rectangle rect = new Rectangle(width, height);
		displayStrings(rect.presentation(offset));
		Rectangle.setSymbol("1");
		displayStrings(rect.presentation(offset));
	}

	@Test
	@Disabled
	void testSquare() {
		int size = 10;
		int offset = 10;
		Square square = new Square(size);
		displayStrings(square.presentation(offset));
		square.setHeight(5);
		System.out.println(square.getHeight() + " " + square.getWidth());
		square.setWidth(10);
		System.out.println(square.getHeight() + " " + square.getWidth());
	}

	@Test
	@Disabled
	void testSqaureTriangle() {
		int size = 10;
		int offset = 10;
		SquareLeftTriangle squareLeftTriangle = new SquareLeftTriangle(size);
		SquareRightTriangle squareRightTriangle = new SquareRightTriangle(size);
		displayStrings(squareLeftTriangle.presentation(offset));
		System.out.println();
		displayStrings(squareRightTriangle.presentation(offset));

		squareLeftTriangle.setHeight(15);
		displayStrings(squareLeftTriangle.presentation(offset));
	}

	@Test
//	@Disabled
	void testCanvas() {
		Canvas canvas1 = new Canvas(5, 6,
				new Shape[] { new Rectangle(3, 4), new SquareRightTriangle(6), new Square(8) });
		Canvas canvas2 = new Canvas(10, 15,
				new Shape[] { new SquareLeftTriangle(11), new Rectangle(5, 9), new SquareRightTriangle(6), canvas1 });
		canvas1.setDirection("column");
		Canvas canvas3 = new Canvas(7, 7, new Shape[] { canvas1, canvas2 });
		canvas1.setMargin(1);
		canvas2.setMargin(2);
		canvas3.setMargin(3);
		displayStrings(canvas3.presentation(10));
		canvas3.setDirection("Column");
		displayStrings(canvas3.presentation(10));
	}
	
	@Test
	@Disabled
	void testCanvas1() {
		Canvas1 canvas = new Canvas1(5, 6,
				new Shape[] { new Rectangle(3, 4), new SquareRightTriangle(6), new Square(2) });
		canvas.setMargin(1);
		displayStrings(canvas.presentation(10));
		canvas.setDirection("column");
		displayStrings(canvas.presentation(10));
	}

	public static void displayStrings(String[] strings) {
		for (String str : strings) {
			System.out.println(str);
		}
	}
}
