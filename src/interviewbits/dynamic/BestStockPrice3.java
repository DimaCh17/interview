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
		return twoPass(input);
    }
	
	// got from left to right
	//  then from right to left
	// the idea is that at each point 
	// we consider two parts of the sale.
	// one is that we sell on that day
	// another one is that we buy on that day.
	// it should be a gap between those, otherwise
	// it will not work
	private int twoPass(final List<Integer> input) {
		if (input == null || input.size() < 1) {
			return 0;
		}
		final int n = input.size();
		int[] sellList = new int[n];
		
		int minPrice = input.get(0);
		for (int i = 1; i < n; i++) {
			int curPrice = input.get(i);
			int lastPrice = input.get(i - 1);
			int curProfit = curPrice - minPrice;
			// the same approach, we look at the max profit
			// if we have only one transaction
			sellList[i] = Math.max(sellList[i - 1], curProfit);
			// rolling price min
			minPrice = Math.min(minPrice, curPrice);
		}
		
		int maxProfit = 0;
		int maxPrice = input.get(n - 1);
		int[] buyList = new int [n];// we use this list to lokup 
		// the previous profit for selling the second stock
		for (int i = n - 2; i >=0; i--) {
			int curPrice = input.get(i);
			//int profit = maxPrice - curPrice;
			buyList[i] = Math.max(buyList[i + 1], maxPrice - curPrice);
			// this is basically, a separate cycle
			maxPrice = Math.max(maxPrice, curPrice);
			maxProfit = Math.max(maxProfit, buyList[i] + sellList[i]);
		}
		return maxProfit;
	}
}
