package rotatedSearch;

public class RotateSearch {
	/**
	 * Find a position of an element in the array or null otherwise. end is inclusive
	 * @return a position of an element or -1 otherwise
	 */
	public int findElement(final int element, int[] data, int start, int end) {// end inclusive
		// handle cases when there is no data or one element
		if (start > end) {
			return -1;
		}
		if (element == data[start]) {
			return start;
		}
		if (element == data[end]) {
			return end;
		}
		// if the end goes directly after start, there is no match
		if (start == end - 1) {
			return -1;
		}
		// start is less than right, use the regular binary search.
		int medianPos = start + (end - start) / 2;
		if (data[start] > data[end]) {
			// the rotated part
			if (element > data[end] && element < data[start]) {
				return -1;
			}
		}
		// it looks like we may start in wrong direction here, can't start recursion here.
		int foundOnLeft = findElement(element, data, start, medianPos);
		if (foundOnLeft != -1) {
			return foundOnLeft;
		}
		return findElement(element, data, medianPos, end);
	}
}
