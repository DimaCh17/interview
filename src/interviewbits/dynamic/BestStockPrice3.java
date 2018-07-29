package interviewbits.dynamic;

import java.util.List;

/*
Say you have an array for which the ith element is the price of a given stock on day i.
Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

(A1) Make a matrix, each row represents a buy price, each column represents a sell price
((1) init n*n matrix
((2) go from left to right
((3) if the profit is more than the last one (the same row) then this cell
    has this value
    otherwise use the previous value from the same row.
((4) once the matrix is ready, then go with finding the best sum of 2.
((5) go from bottom to top. for each cell with the same sell price (col) find the max
    of a sum of this value and the previous sell price (use the buy price from the last sell
    as it defines the row, then decrement it to get the previous sell price)
((CC) don't croww boundaries, as in an example when the buy price is the 0 indexed
*/
public class BestStockPrice3 {
	public int maxProfit(final List<Integer> input) {
		return dp(input);
    }
	private int dp(final List<Integer> input) {
		int n = input.size();
		int[][] matrix = new int[n][n];
		for (int i = 0; i < n; i++) {
			int max_profit  = 0;
			for (int j = i + 1; j < n; j++) {
				int profit = input.get(j) - input.get(i);
				if (profit > max_profit) {
					max_profit = profit;
				}
				matrix[i][j] = max_profit;
			}
		}
		int[] max_profits = new int[n]; // get a cache or max profits 
		// for a sell price
		for (int j = 0; j < n; j++) {
			for (int i = 0; i < j; i++) { // (??) check indexes
				max_profits[j] = Integer.max(max_profits[j], matrix[i][j]);
			}
		}
		int max_profit = 0;
		for (int i = n - 1; i >= 0; i--) {
			int pair_profit = 0;
			for (int j = n - 1; j > i; j--) {
				int fist_profit = matrix[i][j];
				int second_profit = 0;
				if (i > 0) {
					second_profit = max_profits[i - 1];
				}
				pair_profit = fist_profit + second_profit;
				if (pair_profit > max_profit) {
					max_profit = pair_profit;
				}
			}
		}
		return max_profit;
	}
	public int[] get_best_price(final List<Integer> input, int start, int end) { // start and end inclusive
		// return price, sell and buy
		int[] res = new int[3]; // 0 - profit, 1 - buy, 2 - sell
		if (start > end) {
			return res;
		}
		if (start >= input.size()) {
			return res;
		}
		int buy = start;
		for (int i = start + 1; i <= end; i++) {
			int cur = input.get(i);
			if (cur <= input.get(buy)) { // <= to have a shorter window?
				buy = i;
			} else { // we can try selling
				int profit  = cur - input.get(buy);
				if (profit > res[0]) {
					res[0] = profit;
					res[1] = buy;
					res[2] = i;
				}
			}
		}
		return res;
	}
}