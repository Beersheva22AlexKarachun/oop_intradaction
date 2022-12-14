package telran.shapes.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;

import telran.shapes.Canvas;
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
	void testCanvas() {
		Shape[] shapes = {new Rectangle(10, 15), new Square(6), new SquareRightTriangle(11), new Rectangle(5, 22), new SquareLeftTriangle(16)};
		
		Shape canvas1 = new Canvas(5,6,shapes);
		Shape canvas2 = new Canvas(10,15,shapes);
		Canvas canvas3 = new Canvas(5,10, new Shape[] {canvas1, canvas2});
		canvas3.setMargin(2);
		displayStrings(canvas3.presentation(10));
	}

	public static void displayStrings(String[] strings) {
		for (String str : strings) {
			System.out.println(str);
		}
	}
}
