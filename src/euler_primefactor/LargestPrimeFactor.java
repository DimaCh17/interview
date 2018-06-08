package euler_primefactor;

public class LargestPrimeFactor {
// https://projecteuler.net/problem=3
	// instead of trying to find all prime number, we calculate prime factors.
	long getLargestPrimeFactor(long num) {
		long primeFactor = 1;
		long current = num;
		while (current > 1) { // Terminal case, when we stop the cycle
			// !PUNT added one more ++ than needed
			primeFactor++; //optimization - check if prime factor is divisible on 2.
			if ((primeFactor & 1L) == 0) {
				continue;
			}
			while (current % primeFactor == 0) {
				current /= primeFactor;
			}
		}
		return primeFactor;
	}
	
	//TC num  = 1
	// TC num = 2
	// TC num = 5
	// TC num = 15
}
