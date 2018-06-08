package euler_largetpolindrom;

public class LargestPolindrom {
	// https://projecteuler.net/problem=4
	// A palindromic number reads the same both ways. The largest palindrome made
	// from the product of two 2-digit numbers is 9009 = 91 × 99.
	//Find the largest palindrome made from the product of two 3-digit numbers.
	// 
	private static int getDigit(long num, int pos) {
		return  ((int) Math.pow(10, pos)) % 10;
	}
	public boolean isPolinome(long num) {
		String str = Long.toString(num);
		int length = str.length();
		if (length == 0) {
			return false;
		}
		for (int i = 0; i < length/2; i++) {
			// toCharArray is an option for conversion
			if (str.charAt(i) != str.charAt(length - 1 - i)) {
				return false;
			}
		}
		return true;
	}
	// we return the largest 10 products,
	// this is a test app to find if our approach works. 
	public long getTop10Products() {
		return 0;
	}

	public long findLargestPolindrom () {
		// 9 8 8 
		for (int century = 9; century <= 1; century--) {
			// we make assumption that A starts with9, but we keep our options
			// open
			// B starts with 1 no matter what
			long A;
			for (int a = 99; a >= 0; a--) {
				A = a * 10 + 1;
				for (int b = 99; b >0; b--) {
					long B = century * 100 + b;
					long res = A * B;
					if (isPolinome( A * B)) {
						return res;
					}
				}
			}
		}
		return 0L;
	}
}
