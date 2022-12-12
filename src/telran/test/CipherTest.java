package telran.test;

import telran.cipher.BaseCipher;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class CipherTest {

	@Test
	void testCipher() {
		int[] numbers = { 0, 20, 1720, 21430, 595364, 1987345, 876, 1587923, 123798789 };
		String hexCode = "0123456789abcdef";
		String binaryCode = "01";
		BaseCipher hexCipher = new BaseCipher(16);
		BaseCipher binaryCipher = new BaseCipher(2);
		hexCipher.setKey(hexCode);
		binaryCipher.setKey(binaryCode);

		for (int i = 0; i < numbers.length; i++) {
			int number = numbers[i];
			assertEquals(Integer.toHexString(number), hexCipher.cipher(number));
			assertEquals(Integer.toBinaryString(number), binaryCipher.cipher(number));
		}
	}

	@Test
	void testDecipher() {
		for (int i = 0; i < 100000; i++) {
			int length = BaseCipher.getRandomNumber(0, 100);
			int number = BaseCipher.getRandomNumber(0, 100000);
			BaseCipher cipher = new BaseCipher(length);
			String code = cipher.cipher(number);
			assertEquals(number, cipher.decipher(code));
		}
	}

	@Test
	void testDecipherWithWrongSymbols() {
		int length = 100;
		String[] codes = { "ã╚©", "4214q", "123-", "qwerty", "" };
		BaseCipher cipher = new BaseCipher(length);
		cipher.setKey("0123456789abcdef");

		for (int i = 0; i < codes.length; i++) {
			assertEquals(null, cipher.decipher(codes[i]));

		}

	}

	public static void displayStrings(String[] strings) {
		for (String str : strings) {
			System.out.println(str);
		}
	}

	public static void displayChars(char[] chars) {
		for (char c : chars) {
			System.out.print(c);
		}
	}
}
