package google.google1;

public class ProductCalculator {
	public static int[] calculateProducts (int input[]) {
		  // create a result
		  int res[] = new int[input.length];
		  // handle the situation
		  if (input.length == 0) {
		    return res;
		  }
		  res[0] = 1;
		  for (int i = 1; i < res.length; i++) {
		    res[i] = res[i - 1] * input [i - 1];
		  }

		  int temp = 1;
		  for (int i = res.length - 2; i >= 0; i--) {
		    temp = temp * input[i + 1];
		    res[i] = res[i] * temp;
		  }
		  return res;
	}

	public static int[] calculateProducts4 (int[] input) {
		int res[] = new int[input.length];
		if (res.length == 0) {
			return res;
		}
		res[0] = 1;
		for (int i = 1; i < input.length; i++) {
			res [i] = res[i - 1] * input[i - 1];
		}
		int accumulator = 1;
		// do an extra pass to be safe
		for (int i = input.length - 2 ; i >= 0; i--) {
			accumulator = input [i + 1] * accumulator;
			res[i] = res [i] * accumulator;
		}
		return res;
	}

	public static int[] calculateProducts3 (int[] input) {
		int res[] = new int[input.length];
		if (res.length == 0) {
			return res;
		}
		int temp[] = new int[input.length];
		temp[0] = 1;
		for (int i = 1; i < input.length; i++) {
			temp [i] = temp[i - 1] * input[i - 1];
		}
		res[input.length - 1] = temp[input.length - 1];
		int accumulator = 1;
		// do an extra pass to be safe
		for (int i = input.length - 2 ; i >= 0; i--) {
			accumulator = input [i + 1] * accumulator;
			res[i] = temp [i] * accumulator;
		}
		return res;
	}

	public static int[] calculateProducts2 (int[] input) {
		int res[] = new int[input.length];
		if (res.length == 0) {
			return res;
		}
		int temp[] = new int[input.length];
		temp[0] = 1;
		for (int i = 1; i < input.length; i++) {
			temp [i] = temp[i - 1] * input[i - 1];
		}
		res[input.length - 1] = 1;
		// do an extra pass to be safe
		for (int i = input.length - 2 ; i >= 0; i--) {
			res[i] = res [i + 1] * input [i + 1];
			//res[i] = input [i + 1] * temp[i]; // PUNT need to use input instead of
		}
		for (int i = input.length - 1 ; i >= 0; i--) {
			res[i] = res [i] * temp [i];
		}
		return res;
	}
}
