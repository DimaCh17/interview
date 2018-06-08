package fibonacci;
import java.util.HashMap;

public class FibonacciCalculator {
	HashMap<Long, Long> map = new HashMap<Long, Long>();

	public long calculateNaive(long n) {
		// F numbers are calculated as a sum as two previous 
		// two numbers added to each other
		if (n == 1 || n == 2) {
			return n;
		}
		return calculateNaive (n - 2) + calculateNaive (n - 1);
	}

	public long calculateCached(long n) {
		// F numbers are calculated as a sum as two previous 
		// two numbers added to each other
		if (n == 1 || n == 2) {
			return n;
		}
		long prev1, prev2;
		if (map.containsKey(n - 2)) {
			prev1 = map.get(n - 2);
		} else {
			prev1 = calculateCached (n - 2);
		}
		if (map.containsKey(n - 1)) {
			prev2 = map.get(n - 1);
		} else {
			prev2 = calculateCached (n - 1);
		}
		final long res = prev1 + prev2;
		map.put(n, res);
		return res;
	}

}
