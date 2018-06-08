package findduplicate;

public class DuplicateFinder {
	// find one element that was a duplicate in the sequence
	// integers 1..N, with N+1 elements in the sequence
	public int findPuplicate(int[] data) {
		if (data.length < 2) {
			return 0;
		}
		for (int i = 0; i < data.length; i++) {
			if (i < data.length - 1) {
				// we need to add 1 as elements start from 1, not 0.
				
			}
		}
		return 0;
	}

}
