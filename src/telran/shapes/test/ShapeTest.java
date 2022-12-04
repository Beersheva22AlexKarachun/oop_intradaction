package telran.shapes.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;

import telran.shapes.Rectangle;
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

	public static void displayStrings(String[] strings) {
		for (String str : strings) {
			System.out.println(str);
		}
	}
}
