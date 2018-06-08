package interviewcake;

public class ProductOfOthers {
	/*https://www.interviewcake.com/question/product-of-other-numbers
	 * */
	/**
	 * If we just walk 
	 * @param data
	 * @return
	 * so we have A B C D E
	 * the resuls would be 
	 * BCDE ACDE ABDE ABCE ABCD
	 */
	// Lesson - initialize all data
	// the first implementation didn't initalize the 
	// result with index 0, thus the result for the element was 0
	// thus failing the test

	int [] getOtherProducts(int [] data) {
		int [] result = new int[data.length];
		int productOnLeft = 0;
		if (data.length > 1) { 
			productOnLeft = 1;
		}
		for (int i = 0; i < data.length; i++) {
			// The result is index 0, should be 1 as we need to multiple it 
			// on the product on the right
			if (i != 0) {
				// we keep track of temporary product, that doesn't include
				// the current one.
				productOnLeft *= data[i-1];
			}
			// we store product of all elements left to the current position.
			result[i] = productOnLeft;
		}
		int productOnRight = 1;
		for (int j = data.length - 2; j >=0; j--) {
			productOnRight *= data[j + 1];
			// we multiply the current result on the product of all parts on the
			// right and the product of the numbers on left.
			result[j] *= productOnRight;
		}
		return result;
	}
}
