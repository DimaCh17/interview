package paircounter;

public class PairCounter {
	
	/*
	public int countPairs (int[] data, int startPos, int endPos, int difference) {
		// finds amount of pairs in a sorted array array with the difference k
		int count = 0;
		int consideredStart = 0;
		int pivotPos = findPivotPos(data, startPos, endPos);
		for(int currentPos = consideredStart; currentPos < data.length; currentPos++) {
			
		}
	}*/

	public void sort(int[] data, int startPos, int endPos) {
		// end recursion here
		if (startPos < endPos) {
			int pivotPos = partition(data, startPos, endPos);
			sort(data, startPos, pivotPos - 1); // 0, -1
			sort(data, pivotPos + 1, endPos);   // 1, 0
		}
	}
	private int findPivotPos (int[] data, int startPos, int endPos) {
		return (startPos + endPos) / 2;
	}
	
	// check 0 elements
	// check 1 element
	// check the last one
	// check the first one
	private int partition(int[] data, int startPos, int endPos) {
		int pivotPos = findPivotPos(data, startPos, endPos);
		int pivotValue = data[pivotPos];
		// Move pivot value right
		swap(data, pivotPos, endPos);
		int insertPos = startPos;
		// loop for all elements except the last one.
		for (int currentPos = startPos; currentPos < endPos; currentPos++) {
			if (data[currentPos] < pivotValue) {
				swap(data, insertPos, currentPos);
				insertPos++;
			}
		}
		// Move pivotValue to the final position
		swap(data, insertPos, endPos);
		return insertPos;
	}
	
	private void swap(int[] data, int firstPos, int secondPos) {
		if (firstPos == secondPos) {
			return;
		}
		int temp = data[firstPos];
		data[firstPos] = data[secondPos];
		data[secondPos] = temp;
	}
}
