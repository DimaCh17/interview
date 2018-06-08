package google.google1;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class ProductCalculatorTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		int[] input = new int [] {1 , 2, 3, 4};
		assertEquals(Arrays.toString(new int[] {24, 12, 8, 6}),
				Arrays.toString(ProductCalculator.calculateProducts(input)));
	}

}
