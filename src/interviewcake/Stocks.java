package interviewcake;

public class Stocks {
	// https://www.interviewcake.com/
	// Write an efficient function that takes stock_prices_yesterday and returns
	// the best profit I could have made from 1 purchase and 1 sale of 1 Apple stock yesterday.
	// we go from left to right and store spikes (low, high for the price)
	// we track the current profit and the best profit
	// we start tracking only when we make profit
	// we consider the buy price as the last one before it starts going
	public int bestProfit(int data[]) {
		// we need to keep track of the current buy price
		// and the current sell price
		int buyPrice = data[0];
		int maxProfit = 0;
		for (int i = 1; i < data.length;  i++) {
			// if the current profit is better than 0 the current one
			final int currentPrice = data[i];
			final int currentProfit = currentPrice - buyPrice;
			if (currentProfit > maxProfit) {
				maxProfit = currentProfit;
			}
			if (currentPrice < buyPrice) {
				buyPrice = currentPrice;
			}
		}
		return maxProfit;
	}
}
