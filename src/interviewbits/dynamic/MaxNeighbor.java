package interviewbits.dynamic;

import java.util.ArrayList;
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
	private int[][] matrix;
	private int[][] res;
	int ROWS;
	int COLS;
	int direct = 0;

	private boolean outside(int row, int col) {
		if (row < 0 || col < 0 || col >= COLS || row >= ROWS) {
			// never go outside of the matrix
			return true;
		}
		return false;
	}


	public int getColor(int[][] data, int color, int row, int col) {
		if (outside(row, col)) {
			// never go outside of the matrix
			return color;
		}
		color = data[row][col];
		return color;
	}

	public ArrayList<ArrayList<Integer>> solve(int k, ArrayList<ArrayList<Integer>> input) {
		if (input.size() == 0 || input.get(0).size() == 0) {
			return new ArrayList<>();
		}
		ROWS = input.size();
		COLS = input.get(0).size();
		direct = 0;
		matrix = new int[ROWS][COLS];
		res = new int[ROWS][COLS];
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				matrix[row][col] = input.get(row).get(col);
				res[row][col] = matrix[row][col];
			}
		}
		
		
		for (int gen = 1; gen <= k; gen++) {
			increase(input);
			direct += 1;
			direct %= 2;
		}
		
		int[][] outdir = direct == 1 ? res : matrix;
		
		ArrayList<ArrayList<Integer>> out = new ArrayList<ArrayList<Integer>>();
		for (int row = 0; row < ROWS; row++) {
			ArrayList<Integer> line = new ArrayList<Integer>();
			out.add(line);
			for (int col = 0; col < COLS; col++) {
				line.add(outdir[row][col]);
			}
		}
		return out;
	}
	
	public void increase(ArrayList<ArrayList<Integer>> input) {
		int[][] was = direct == 0 ? matrix : res;
		int[][] now = (was == matrix) ? res : matrix;
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				now[row][col] = was[row][col];
				now[row][col] = Integer.max(now[row][col], getColor(was, now[row][col], row - 1, col));
				now[row][col] = Integer.max(now[row][col], getColor(was, now[row][col], row + 1, col));
				now[row][col] = Integer.max(now[row][col], getColor(was, now[row][col], row, col - 1));
				now[row][col] = Integer.max(now[row][col], getColor(was, now[row][col], row, col + 1));
			}
		}
    }
}