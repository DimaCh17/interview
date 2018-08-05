package interviewbits.dynamic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
				//print(" cube_res: %3d", cubeRes);
				if (areaMap.containsKey(area)) {
					int found = areaMap.get(area);
					// handle multiple entries with the same area that
					// previously got found
					areaMap.put(area, found + 1);
					res += found; // (/?) how does it work with the previous
					// line when we use found
					//print(" ++");
				}
				// put only the big area to the map,
				// thus we can re-use the map on later
				// steps, as only areas for smaller columns are there
				// which is a DP point
				//areaMap.put(area, areaMap.getOrDefault(area, 0) + 1);
				areaMap.put(area, areaMap.getOrDefault(area, 0) + 1);
				if (bigArea == 0) {
					res++;
				}
			}
			
			print("\n");
		}
		return res;
	}

	public boolean showOutput = false;

	private void print(String format, Object... args) {
		if (!showOutput)
			return;
		System.out.print(String.format(format, args));
	}
}
