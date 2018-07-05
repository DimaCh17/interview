package interviewbits.dynamic;

import java.util.ArrayList;
import java.util.Objects;
import java.util.TreeMap;

/*
Given a matrix M of size nxm and an integer K, find the maximum element in the K manhattan distance neighbourhood for all elements in nxm matrix.
In other words, for every element M[i][j] find the maximum element M[p][q] such that abs(i-p)+abs(j-q) <= K.

Note: Expected time complexity is O(N*N*K)

Constraints:

1 <= n <= 300
1 <= m <= 300
1 <= K <= 300
0 <= M[i][j] <= 1000
Example:

Input:
M  = [[1,2,4],[4,5,8]] , K = 2

Output:
ans = [[5,8,8],[8,8,8]]
Given a matrix M of size nxm and an integer K, find the maximum element in the K manhattan distance neighbourhood for all elements in nxm matrix.
In other words, for every element M[i][j] find the maximum element M[p][q] such that abs(i-p)+abs(j-q) <= K.

Note: Expected time complexity is O(N*N*K)

Constraints:

1 <= n <= 300
1 <= m <= 300
1 <= K <= 300
0 <= M[i][j] <= 1000
Example:

Input:
M  = [[1,2,4],[4,5,8]] , K = 2

Output:
ans = [[5,8,8],[8,8,8]]

(I1) go from left to right, and keep a count map (tree map) of values to their counts, that are within
the pattern. once the value goes away
keep a copy of the matrix to store max, thus they don't interact with the original.
(A)
(1) start with 0, 0. get all elements in range (it will be a half pattern + 1), put them into the map.
(2) move the target left (or down) until the last element is reached, may use a single coordinate
    but that may be too much now
(3) remove all elements that are not covered by the previous (direction) pattern
  based on (direction) passed to the step.
(4) loop 2 until the last element is reached.
*/

public class MaxNeighbor {
	private class CountMap {
		private TreeMap<Integer, Integer> map = new TreeMap<>();
		private int count = 0;
		
		@Override
		public String toString() {
			return map.keySet().toString();
		}

		public void add(int key) {
			map.put(key, map.getOrDefault(key, 0) + 1);
			count++;
		}
		public int getMax() {
			return map.lastKey();
		}

		public void remove(int key) {
			Integer oldValue = map.get(key);
			Objects.requireNonNull(oldValue);
			if (oldValue == 1) {
				map.remove(key);
			} else {
				map.put(key, oldValue + 1);
			}
			count--;
		}
		
		public int size() {
			return count;
		}
	}

	@FunctionalInterface
	private interface ChangeCell {
		void at(int row, int col);
	}
	
	public ArrayList<ArrayList<Integer>> solve(int k, ArrayList<ArrayList<Integer>> input) {
		CountMap count = new CountMap();
		if (input.size() == 0 || input.get(0).size() == 0) {
			return new ArrayList<>();
		}
		final int rows = input.size();
		final int cols = input.get(0).size();
		java.util.function.BiFunction<Integer, Integer, Boolean> inRange =
			(r, c) -> c >= 0 && c < cols && r >= 0 && r < rows;
		int[][] data = new int[rows][cols];
		int[][] res = new int[rows][cols];
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				data[row][col] = input.get(row).get(col);
				res[row][col] = Integer.MIN_VALUE;
			}
		}
		ChangeCell remove = (r, c) -> {
			if (inRange.apply(r, c)) {
				count.remove(get(r, c, data));
			}
		};
		ChangeCell add = (r, c) -> {
			if (inRange.apply(r, c)) {
				count.add(get(r, c, data));
			}
		};
		// init the first position
		int direction = 0;// 0 - left, 1 - down
		add.at(0, 0);
		int row = 0;
		int col = 0;
		while (row < rows && col < cols) {
			if (direction == 0) {
				add.at(row - 1, col);
				add.at(row, col + 1);
				add.at(row + 1, col);
				remove.at(row - 1, col - 1);
				remove.at(row, col - 2);
				remove.at(row + 1, col - 1);
				res[row][col] = count.getMax();
				if (col == cols - 1) {
					direction = 1;
					row += 1;
				} else {
					col += 1;
				}
			} else if (direction == 1) { // vertical, on the right
				add.at(row, col - 1);
				add.at(row + 1, col);
				remove.at(row - 2, col);
				remove.at(row - 1, col - 1);
				direction = 2;
				res[row][col] = count.getMax();
				col -= 1;
			} else if (direction == 2) { // going left
				add.at(row - 1, col);
				add.at(row, col - 1);
				add.at(row + 1, col);
				remove.at(row - 1, col + 1);
				remove.at(row, col + 2);
				remove.at(row + 1, col + 1);
				res[row][col] = count.getMax();
				if (col == 0) {
					direction = 3;
					row += 1;
				} else {
					col -= 1;
				}
			} else if (direction == 3) {// on the left, going down
				add.at(row, col + 1);
				add.at(row + 1, col);
				remove.at(row - 2, col);
				remove.at(row - 1, col + 1);
				direction = 0;
				res[row][col] = count.getMax();
				col += 1;
			}
			
		}
		ArrayList<ArrayList<Integer>> out = new ArrayList<ArrayList<Integer>>();
		for (row = 0; row < rows; row++) {
			ArrayList<Integer> line = new ArrayList<Integer>();
			out.add(line);
			for (col = 0; col < cols; col++) {
				line.add(res[row][col]);
			}
		}
		return out;
    }
	
	private static int get(int row, int col, int[][] data) {
		return data[row][col];
	}

}