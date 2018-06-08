package interviewbits;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class AddOneDigitTest {

	AddOneDigit underTest;
	@Before
	public void setUp() throws Exception {
		underTest = new AddOneDigit();
	}

	private void runTest(int[] data) {
		
		System.out.print(Arrays.toString(data));
		System.out.print(": ");
		System.out.println(Arrays.toString(underTest.plusOne(data)));
	}
	
	@Test
	public void test() {
		runTest(new int[] {1});
		runTest(new int[] {1, 2, 3});
		runTest(new int[] {9});
		runTest(new int[] {9, 9, 9});
	}

}
