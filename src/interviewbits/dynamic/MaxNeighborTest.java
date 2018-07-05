package interviewbits.dynamic;

import static org.junit.Assert.*;
import interviewbits.utils.ArrayUtils;

import org.junit.Before;
import org.junit.Test;

public class MaxNeighborTest {
	MaxNeighbor mn;
	@Before
	public void setUp() throws Exception {
		mn = new MaxNeighbor();
	}

	@Test
	public void test() {
		assertEquals("[[5,8,8],[8,8,8]]",
				ArrayUtils.printMatrix(mn.solve(2, ArrayUtils.createMatrix(
				3,
				1,2,4,
				4,5,8))));
	}
	
	@Test
	public void testk1() {
		assertEquals("[[4,5,8],[5,8,8]]",
				ArrayUtils.printMatrix(mn.solve(2, ArrayUtils.createMatrix(
				3,
				1,2,4,
				4,5,8))));
	}

}
