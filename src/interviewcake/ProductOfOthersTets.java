package interviewcake;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class ProductOfOthersTets {

	ProductOfOthers product;

	@Before
	public void setUp() throws Exception {
		product = new ProductOfOthers();
	}

	@Test
	public void productTest() {
		int data[] = new int[] {1, 2, 3, 4};
		int result[] = product.getOtherProducts(data);
		assertEquals(Arrays.toString(new int[] {24, 12, 8, 6}),
				Arrays.toString(result));
	}

	@Test
	public void productEmpty() {
		int data[] = new int[0];
		int result[] = product.getOtherProducts(data);
		assertEquals(Arrays.toString(new int[0]),
				Arrays.toString(result));
	}

	@Test
	public void productOneElement() {
		// I think that we can't calculate the product of non existent
		// elements
		int data[] = new int[] {6};
		int result[] = product.getOtherProducts(data);
		assertEquals(Arrays.toString(new int[] {0}),
				Arrays.toString(result));
	}

	@Test
	public void productOneElementIsZeo() {
		// I think that we can't calculate the product of non existent
		// elements
		int data[] = new int[] {5 , 0};
		int result[] = product.getOtherProducts(data);
		assertEquals(Arrays.toString(new int[] {0, 5}),
				Arrays.toString(result));
	}

	@Test
	public void productTwoElements() {
		// I think that we can't calculate the product of non existent
		// elements
		int data[] = new int[] {1 , 2};
		int result[] = product.getOtherProducts(data);
		assertEquals(Arrays.toString(new int[] {2, 1}),
				Arrays.toString(result));
	}

}
