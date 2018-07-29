package interviewbits.dynamic;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

public class BestStockPrice3Test {

	BestStockPrice3 bsp;

	@Before
	public void setUp() throws Exception {
		bsp = new BestStockPrice3();
	}

	@Test
	public void test() {
		assertEquals(3, bsp.maxProfit(Arrays.asList(1, 4)));
	}

	@Test
	public void test2() {
		assertEquals(7, bsp.maxProfit(Arrays.asList(1, 4, 2, 6)));
	}
	
	@Test
	public void test3() {
		assertEquals(2, bsp.maxProfit(Arrays.asList(1, 2, 1, 2)));
	}
	
	@Test
	public void test0() {
		assertEquals(0, bsp.maxProfit(Arrays.asList(4, 0)));
	}
	
	@Test
	public void test6() {
		assertEquals(8, bsp.maxProfit(Arrays.asList(1, 6, 2, 5)));
	}
	
	@Test
	public void test7() {
		assertEquals(8, bsp.maxProfit(Arrays.asList(1, 5, 2, 6)));
	}
}


