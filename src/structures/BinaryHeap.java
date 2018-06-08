package structures;

import java.util.Arrays;

public class BinaryHeap {
	public int capacity;
	int[] data;
	int size;
	
	public BinaryHeap(int capacity) {
		this.capacity = capacity;
		data = new int[capacity + 1];
	}

	private int get(int index) {
		return data[index];
	}
	
	private int getParent(int index) {
		return data[index/2];
	}

	private boolean hasLeftChild(int index) {
		return index * 2 < size;
	}

	private boolean getLeftChild(int index) {
		return index * 2 < size;
	}
	
	private boolean hasRightChild(int index) {
		return index * 2 + 1 < size;
	}

	private int getRightChild(int index) {
		return data[index * 2 + 1];
	}

	public void insert(int value) {
		if (size >= capacity - 1) {
			// increase size
			capacity = capacity == 0 ? 2 : capacity * 2;
			// it will not increase;
			int[] newData = new int[capacity];
			System.arraycopy(data, 1, newData, 1, size);
			data = newData;
		}
		size++;
		data[size] = value;
		// percolate up.
		// we insert the element to the last position
		// and then compare it to the parent until the parent is equal or
		// more than the current element
		// TODO: find a good library to display the progress
		// animate it
		
		for (int pos = size; pos > 1 && data[pos] > data[pos / 2]; pos = pos/2) {
			int temp = data[pos / 2];
			data[pos / 2] = data[pos];
			data[pos] = temp;
			// ? Can java swap to variables
			// TODO: instead of swapping the 
			// elements we can find the correct position and then put the element there?
			// check the article for details.
		}
	}
	public void removeMax() {
		// we switch the last and first elements
		// and then percolate down
		data[1] = data[size];
		size--;
		int curPos = 1;
		int newPos = curPos;
		while (curPos <= size) {
			// we check if there are two elements, we check with the
			// the larger one, and switch them
			// given it's larger, it will be a valid parent for the second 
			// child if it exists
			int candidate = data[curPos];
			if (curPos * 2 > size) {// no left child 
				return;
			} else if (curPos * 2 + 1 <= size) { // the right child exist then the 
				// left exists too
				// check if the any of the children is bigger than the
				// parent
				// and switch 
				candidate = Math.max(candidate, b)
			} else { // only left child exist
			}
			// update current pos
		}
	}
	public int getLevel(int oneBasedIndex) {
		// helps printing
		// !PUNT Math.log is natural algorithm
		// that I learned but forgot. 
		for (int i = 0; i < 64; i++) {
			if (Math.pow(2, i)> oneBasedIndex) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		int currentLevel = 1;
		for (int i = 1; i <= size; i++) {
			int level = getLevel(i);
			// i = 0 ; level = 0
			// i = 1; level = 1 
			if (level > currentLevel) {
				builder.append("\\n");
				// TODO: check how separator works in Java
			} else {
				builder.append(" ");
			}
			builder.append(data[i]);
			currentLevel = level;
		}
		return builder.toString().trim();
	}
}
