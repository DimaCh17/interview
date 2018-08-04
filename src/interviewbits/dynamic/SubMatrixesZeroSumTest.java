package interviewbits.dynamic;

import static org.junit.Assert.*;

import java.util.Arrays;

import interviewbits.utils.ArrayUtils;

import org.junit.Before;
import org.junit.Test;

public class SubMatrixesZeroSumTest {

	SubMatrixesZeroSum smzs;
	@Before
	public void setUp() throws Exception {
		smzs = new SubMatrixesZeroSum();
	}

	@Test
	public void test() {
		smzs.showOutput = true;
		assertEquals(2, smzs.solve(ArrayUtils.parseMatrix(
				"[[-8,5,7],[3,7,-8],[5,-8,9]]")));
	}
	
	
	@Test
	public void cc1_zero() {
		assertEquals(1, smzs.solve(ArrayUtils.parseMatrix(
				"[[0]]")));
	}
	
	@Test
	public void cc2_zeroRow() {
		assertEquals(3, smzs.solve(ArrayUtils.parseMatrix(
				"[[0, 0]]")));
	}
	
	@Test
	public void cc3_zeroCol() {
		assertEquals(3, smzs.solve(ArrayUtils.parseMatrix(
				"[[0],[0]]")));
	}
	
	
	public void testPre2() {
		// -8 5  7
		//3  7 -8
		//5 -8  9
		smzs.showOutput = true;
		StringBuilder sb = new StringBuilder();
		// print the matrix array.
		String printed = ArrayUtils.printMatrixArray(smzs.getPrefixSum(
				ArrayUtils.parseMatrix("[[-8,5,7],[3,7,-8],[5,-8,9]]")));
		System.out.print(printed + "\n");
	}

}
