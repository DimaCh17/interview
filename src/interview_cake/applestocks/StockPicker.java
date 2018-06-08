package interview_cake.applestocks;

public class StockPicker {
	// find the max profit. We basically measure a profit in every moment, and store only
	//one that is maximum.

	public int getMaxProfit(int[] stockPrices) {
	  if (stockPrices == null || stockPrices.length == 0) {
	    return 0;
	  }
	  int maxProfit = 0;
	  int buyPrice = stockPrices[0]; // to sell, we need to buy
	  int sellPrice = buyPrice; // we need to initialize the sell price
	  for (int i = 1; i < stockPrices.length; i++) {
	    int price = stockPrices[i];
	    if (price > sellPrice) {
	      sellPrice = price;
	    }
	    int profit = price - buyPrice;
	    if (profit > maxProfit) {
	      maxProfit = profit;
	    }
	  }
	  return maxProfit;
	}
}
