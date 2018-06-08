package magicsquare;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SquareCounterTest {

	private SquareCounter counter;
	@Before
	public void setUp () {
		counter = new SquareCounter();
	}

	@Test
	public void normal() {
		int[][] array = new int[][] {
			{4,9,2},
			{3,5,7},
			{8,1,6}
		};
		assertEquals(true, counter.isMagic3Square(array, 0, 0));
		assertEquals(1, SquareCounter.count3squares(array));
	}
	
	@Test
	public void wrongPositionLeft() {
		int[][] array = new int[][] {
			{4,9,2},
			{3,5,7},
			{8,1,6}
		};
		assertEquals(false, counter.isMagic3Square(array, -1, -1));
	}
	
	@Test
	public void equal() {
		int[][] array = new int[][] {
			{5,5,5},
			{5,5,5},
			{5,5,5}
		};
		assertEquals(true, counter.isMagic3Square(array, 0, 0));
		assertEquals(1, SquareCounter.count3squares(array));
	}

	@Test
	public void nullArray() {
		assertEquals(false, counter.isMagic3Square(null, 0, 0));
	}

	@Test
	public void emptyArray() {
		assertEquals(false, counter.isMagic3Square(new int[][]{{}}, 0, 0));
	}
}
