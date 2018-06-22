package interviewbits.dynamic;

import java.util.function.BinaryOperator;

public class NDigits {
    // n - digits, s - sum
	private final int MODULO = 1000000007;
	public int solve(int digits, int sum) {
        return solveBy(this::grid, digits, sum);
    }

	private <T> T solveBy(BinaryOperator<T> op, T digits, T sum) {
		return op.apply(digits, sum);
	}
	
	private int grid(int digits, int sum) {
		if (sum == 0) {
			return 1;
		}
		if (digits == 0) {
			return 0;
		}
		int[][] data = new int[digits + 1][sum + 1];
		// init the first column
		// no need to reinitialize 0, as it was done before
		for (int i = 1; i <= 9 && i <= sum; i++) {
			// init the first digit
			data[1][i] = 1;
		}
		for (int digit = 2; digit <= digits; digit++) {
			for (int number = 1; number <= sum; number++) {
				// look behind
				for (int k = 0; k <= 9; k++) {
					int indexBehind = number - k;
					 // ? check with modulo here
					if (indexBehind < 0) {
						continue; // (!! check the variant when modulo brings it back)
					}
					data[digit][number] += data[digit - 1][indexBehind];
					data[digit][number] %= MODULO;
				}
			}
		}
		return data[digits][sum];
	}

}
