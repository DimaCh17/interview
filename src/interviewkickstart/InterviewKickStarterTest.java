package interviewkickstart;

import java.util.Arrays;

import org.junit.Test;

public class InterviewKickStarterTest {

	@Test
	public void testFindElementsWithTwoElements() {
		// we need to find two elements with the biggest difference
		InterviewKickstart starter = new InterviewKickstart();
		int[] data = new int[] {1, 2, 7, 8, 9, 11};
		int[] results = starter.findElementsWithDifference(data);
		System.out.println(Arrays.toString(results));
	}
}
