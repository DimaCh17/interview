package interviewbits.dynamic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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


(A2). use the dp to check rectanges in a strip
(1) iterate the cols
(2) use areas in the strip, when upper rows areas are removed
    by use prefix sums.
(3) start from the left
(4) check if any of cached rectangles in the strip has the same area
(5) if it does, then the strip segment (a difference between the big and cached)
    is 0, need to increase res ...
(6) ?? does each cached rectangle introduce a different result (adds to the crowd)
(7) what does it mean if there were 2 rectangles in the strip with the same area
    zeros is a great example.
IDX:  0  1  2
INP: [0, 0, 0]
both [0], and [0, 1] (we cache NOT [1] as it doesn't start on the left)
it means that [1, 2] and [2] have 0 area.
*/

public class SubMatrixesZeroSum {

	public int[][] getPrefixSum(ArrayList<ArrayList<Integer>> input) {
		final int rows = input.size();
		final int cols = input.get(0).size();
		int[][] pre = new int[rows][cols];
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				pre[row][col] = input.get(row).get(col);
				int added = 0;
				if (row > 0) {
					pre[row][col] += pre[row - 1][col];
					added++;
				}
				if (col > 0) {
					pre[row][col] += pre[row][col - 1];
					added++;
				}
				if (added == 2) {
					pre[row][col] -= pre[row - 1][col - 1];
				}
			}
		}
		return pre;
	}

	public int solve(ArrayList<ArrayList<Integer>> input) {
		if (input == null || input.size() == 0 || input.get(0).size() == 0) {
			return 0;
		}
		final int rows = input.size();
		final int cols = input.get(0).size();
		int[][] pre = getPrefixSum(input);
		// make all pairs of r1, r2.
		Set<Long> pairSet = new TreeSet<>();

		for (int row1 = 0; row1 < rows; row1++) {
			for (int row2 = 0; row2 < rows; row2++) {
				Long val = 0L;
				int[] pair = new int[2];
				if (row1 <= row2) {
					pair[0] = row1;
					pair[1] = row2;
				} else {
					pair[0] = row2;
					pair[1] = row1;
				}
				val |= pair[0];
				val <<= 32;
				val |= pair[1];
				if (!pairSet.contains(val)) {
					// && pair[0] != pair[1]
					pairSet.add(val);
					print("put:{%d, %d}\n", pair[0], pair[1]);
				}
			}
		}
		int res = 0;
		for (Long val : pairSet) {
			int r1 = (int) (val >> 32);
			int r2 = (int) (val & 0xFFFFFFFF);
			Map<Integer, Integer> areaMap = new HashMap<>();
			for (int col = 0; col < cols; col++) {
				final int bigArea = pre[r2][col];
				int smallArea = 0;
				if (r1 >= 1) {
					//print(" [r1 - 1]: %3d", pre[r1 - 1][col]);
					smallArea = pre[r1 - 1][col];
				}
				int area = bigArea - smallArea;
				if (areaMap.containsKey(area)) {
					int found = areaMap.get(area);
					// handle multiple entries with the same area that
					// previously got found
					//areaMap.put(area, found + 1);
					res += found; // (/?) how does it work with the previous
					// line when we use found
					//print(" ++");
				}
				// put only the big area to the map,
				// thus we can re-use the map on later
				// steps, as only areas for smaller columns are there
				// which is a DP point
				//areaMap.put(area, areaMap.getOrDefault(area, 0) + 1);
				putToCache(area, getFromCache(area, areaMap) + 1, areaMap);
				if (area == 0) { //? do we actually need that or it produces dups?
					res++;
				}
			}
			
			print("\n");
		}
		return res;
	}

	private void putToCache(int key, int value, Map<Integer, Integer> cache) {
		cache.put(key, value);
	}
	
	private int getFromCache(int key, Map<Integer, Integer> cache) {
		return cache.getOrDefault(key, 0);
	}

	public boolean showOutput = false;

	private void print(String format, Object... args) {
		if (!showOutput)
			return;
		System.out.print(String.format(format, args));
	}
}
