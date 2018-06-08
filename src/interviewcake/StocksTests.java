package interviewcake;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StocksTests {

	private Stocks stocks;
	@Before
	public void setUp() throws Exception {
		stocks = new Stocks();
	}

	@Test
	public void simple() {
		assertEquals(3, stocks.bestProfit(new int[] {1, 4}));
	}

	@Test
	public void onePrice() {
		assertEquals(0, stocks.bestProfit(new int[] {1}));
	}
	
	@Test
	public void oneSpike() {
		assertEquals(2, stocks.bestProfit(new int[] {1, 3, 2}));
	}

	@Test
	public void secondSpike() {
		assertEquals(4, stocks.bestProfit(new int[] {1, 3, 0, 4}));
	}
	
	@Test
	public void fistSpikeBetter() {
		assertEquals(3, stocks.bestProfit(new int[] {1, 4, 0, 2}));
	}
}
