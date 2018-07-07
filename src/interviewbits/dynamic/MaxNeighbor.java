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
	private int count = 0;
	private int[][] matrix;
	private int[][] res;
	private CacheEntry[][] cache;
	
	private class CacheEntry {
		public int ts;
		public int distance;
	}
	

	private boolean outside(int row, int col, int distance) {
		if (distance < 0 || row < 0 || col < 0 || col >= matrix[0].length) {
			// never go outside of the matrix
			return true;
		}
		return false;
	}

	public void paintWith(int color, int row, int col, int distance, int ts) {
		if (outside(row, col, distance)) {
			// never go outside of the matrix
			return;
		}
		if (cache[row][col] != null && cache[row][col].ts == ts
				&& cache[row][col].distance >= distance) {
			return;
		}
		res[row][col] = Integer.max(color, res[row][col]);
		cache[row][col] = new CacheEntry();
		cache[row][col].ts = ts;
		cache[row][col].distance = distance;
		
		paintWith(color, row - 1, col, distance - 1, ts);
		paintWith(color, row, col - 1, distance - 1, ts);
		paintWith(color, row, col + 1, distance - 1, ts);
	}

	public int getColor(int color, int row, int col, int distance) {
		if (outside(row, col, distance)) {
			// never go outside of the matrix
			return color;
		}
		if (matrix[row][col] == Integer.MIN_VALUE) {
			return color;// if we never explored this area
			// don't do it, otherwise it will be a lot of false requests.
		}
		color = Integer.max(color, matrix[row][col]);
		if (distance > 0) {
			color = Integer.max(color, getColor(color, row - 1, col, distance - 1));
			color = Integer.max(color, getColor(color, row, col - 1, distance - 1));
			color = Integer.max(color, getColor(color, row, col + 1, distance - 1));
			// TODO: use a queue instead of recurrent calls
		}
		return color;
	}
	public ArrayList<ArrayList<Integer>> solve(int k, ArrayList<ArrayList<Integer>> input) {
		
		if (input.size() == 0 || input.get(0).size() == 0) {
			return new ArrayList<>();
		}
		final int rows = input.size();
		final int cols = input.get(0).size();
		
		matrix = new int[rows][cols];
		res = new int[rows][cols];
		cache = new CacheEntry[rows][cols];
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				matrix[row][col] = input.get(row).get(col);
				res[row][col] = Integer.MIN_VALUE;
			}
		}
		
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				paintWith(matrix[row][col], row, col, k, row * cols);
				res[row][col] = getColor(matrix[row][col], row, cols, k);
			}
		}
	
		ArrayList<ArrayList<Integer>> out = new ArrayList<ArrayList<Integer>>();
		for (int row = 0; row < rows; row++) {
			ArrayList<Integer> line = new ArrayList<Integer>();
			out.add(line);
			for (int col = 0; col < cols; col++) {
				line.add(res[row][col]);
			}
		}
		return out;
    }
}