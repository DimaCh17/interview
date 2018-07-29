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
		int[] max_profits = new int[n]; // get a cache or max profits
		int max_profit  = 0;
		int[] sell_window = new int[n]; // UEC
		for (int sell_idx = 1; sell_idx < n; sell_idx++) {
			for (int buy_idx = 0; buy_idx < sell_idx; buy_idx++) {
				int profit = input.get(sell_idx) - input.get(buy_idx);
				sell_window[buy_idx] = Integer.max(profit,  sell_window[buy_idx]); // LL - use the result of 
				// the operation, but not the input
				max_profits[sell_idx] = Integer.max(sell_window[buy_idx],  max_profits[sell_idx]);
			}
			for (int buy_idx = n - 1; buy_idx >= 0; buy_idx--) {
				int pair_profit = 0;
				int fist_profit = sell_window[buy_idx];
				// simple, as the it's should be a rolling profit
				// that may use a maximum from a previous day
				// rather than replying on the current profit
				// i.e. - the sale may have happened before
				// than the sell day
				int second_profit = 0;
				if (buy_idx > 0) {
					second_profit = max_profits[buy_idx - 1];
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
