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
((CC) don't cross boundaries, as in an example when the buy price is the 0 indexed
*/
public class BestStockPrice3 {
	public int maxProfit(final List<Integer> input) {
		return dp(input);
    }

	private int dp(final List<Integer> input) {
		int n = input.size();
		int[] maxProfits = new int[n]; // get a cache or max profits
		int maxProfit  = 0;
		int[] sellWindow = new int[n]; // UEC
		for (int sellIdx = 1; sellIdx < n; sellIdx++) {
			for (int buyIdx = 0; buyIdx < sellIdx; buyIdx++) {
				int profit = input.get(sellIdx) - input.get(buyIdx);
				int bestSellPrice = Integer.max(profit,  sellWindow[buyIdx]);
				sellWindow[buyIdx] = bestSellPrice;
				// LL - use the result of 
				// the operation, but not the input
				maxProfits[sellIdx] = Integer.max(bestSellPrice,
					maxProfits[sellIdx]);
				int pairProfit = 0;
				int firstProfit = sellWindow[buyIdx];
				// simple, as the it's should be a rolling profit
				// !!
				// that may use a maximum from a previous day
				// rather than replying on the current profit
				// i.e. - the sale may have happened before
				// than the sell day
				int secondProfit = 0;
				if (buyIdx > 0) {
					secondProfit = maxProfits[buyIdx - 1];
				}
				pairProfit = firstProfit + secondProfit;
				if (pairProfit > maxProfit) {
					maxProfit = pairProfit;
				}
			}
		}
		return maxProfit;
	}	
}
