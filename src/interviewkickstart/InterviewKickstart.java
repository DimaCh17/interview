package interviewkickstart;

public class InterviewKickstart {

	public int[] findElementsWithDifference(int[] data) {
		int[] result = new int[2];
		// we find both minimum and maximum elements, those are two with the 
		// biggest difference.
		for (int i = 0; i < data.length; i++) {
			final int current = data[i];
			if (i == 0) {
				// first time we have just one element
				result[0] = current;
				continue;
			} 
			if (i == 1) {
				if (current > result[0]) {
					result[1] = current;
				} else {
					result[1] = result[0];
					result[0] = current;
				}
				continue;
			}
			// we have the minimum and maximum elements set.
			if (current < result[0]) {
				result[0] = current;
			}
			if (current > result[1]) {
				result[1] = current; // !!!!MADE a MISTAKE on the index here.
			}
		}
		return result;
	}

}
