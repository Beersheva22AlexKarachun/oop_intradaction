package telran.cipher;

import java.util.Arrays;

public class BaseCipher {
	private static final int FIRST_ASCII_CODE = 33;
	private static final int LAST_ASCII_CODE = 126;
	private static final int MIN_LENGHT = 2;
	private static final int MAX_LENGHT = LAST_ASCII_CODE - FIRST_ASCII_CODE + 1;
	private int radix;
	private String key;
	private int[] symbolsValue = new int[MAX_LENGHT];

	public BaseCipher(int length) {
		length = checkLenght(length);
		this.key = generateUniqueKey(length);
		this.radix = length;
		fillSymbolsValue();
	}

	private void fillSymbolsValue() {
		Arrays.fill(symbolsValue, -1);
		for (int i = 0; i < radix; i++) {
			symbolsValue[key.charAt(i) - FIRST_ASCII_CODE] = i;
		}
	}

	private String generateUniqueKey(int lenght) {
		char[] res = new char[lenght];
		boolean[] helper = new boolean[MAX_LENGHT];
		int i = 0;
		while (i < res.length) {
			int number = getRandomNumber(FIRST_ASCII_CODE, LAST_ASCII_CODE);
			if (!helper[number - FIRST_ASCII_CODE]) {
				res[i] = (char) number;
				helper[number - FIRST_ASCII_CODE] = true;
				i++;
			}
		}
		return new String(res);
	}

	public static int getRandomNumber(int min, int max) {
		return (int) (min + Math.random() * (max - min + 1));
	}

	private int checkLenght(int lenght) {
		lenght = (lenght > MIN_LENGHT) ? lenght : MIN_LENGHT;
		lenght = (lenght < MAX_LENGHT) ? lenght : MAX_LENGHT;
		return lenght;
	}

	/**
	 * 
	 * @param number
	 * @return String presentation comprising of the symbols from the generated key
	 */
	public String cipher(int number) {
		int resLenght = number == 0 ? 1 : (int) (Math.log(number) / Math.log(radix)) + 1;
		char[] res = new char[resLenght];
		for (int i = 0; i < res.length; i++) {
			int remainder = number % radix;
			res[res.length - i - 1] = key.charAt(remainder);
			number /= radix;
		}

		return new String(res);
	}

	/**
	 * 
	 * @param expression
	 * @return if expression contains only symbols from key - decimal number
	 *         converted from expression using key, otherwise - null
	 */
	public Integer decipher(String expression) {
		Integer res = null;
		if (checkExpression(expression)) {
			res = 0;
			int length = expression.length();
			for (int i = 0; i < length; i++) {
				char symbol = expression.charAt(length - i - 1);
				res = addPlace(res, symbol, i);
			}
		}
		return res;
	}

	private Integer addPlace(Integer number, char symbol, int place) {
		int value = getSymbolValue(symbol);
		;
		return number + (value * (int) Math.pow(radix, place));
	}

	private int getSymbolValue(char symbol) {
		return symbolsValue[(int) symbol - FIRST_ASCII_CODE];
	}

	/**
	 * The use of regex is complicated by the presence of regex service characters
	 * in the key, which must be screened
	 * 
	 * @param expression
	 * @return true if expression contains only symbols from key, otherwise false.
	 */
	private boolean checkExpression(String expression) {
		int expressionLength = expression.length();
		boolean res = false;
		if (expressionLength > 0) {
			boolean[] keyHelper = new boolean[MAX_LENGHT];
			fillArray(keyHelper, key);
			int i = 0;
			res = true;
			while (i < expressionLength && res) {
				int asciiCodeOfSymbols = (int) expression.charAt(i);
				if (asciiCodeOfSymbols < FIRST_ASCII_CODE || asciiCodeOfSymbols > LAST_ASCII_CODE
						|| !keyHelper[asciiCodeOfSymbols - FIRST_ASCII_CODE]) {
					res = false;
				}
				i++;
			}
		}
		return res;
	}

	private void fillArray(boolean[] arr, String str) {
		for (int i = 0; i < str.length(); i++) {
			arr[(int) str.charAt(i) - FIRST_ASCII_CODE] = true;
		}

	}

	public String getKey() {
		return key;
	}

	/**
	 * This method have been created only for tests
	 * 
	 * @param str
	 */
	public void setKey(String str) {
		this.key = str;
		this.radix = str.length();
		fillSymbolsValue();
	}

}
