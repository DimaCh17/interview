package fibonacci;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FibonacciCalculatorTest {
	FibonacciCalculator calc;

	@Before
	public void setUp() {
		calc = new FibonacciCalculator();
	}

	@Test
	public void calculateNaive() {
		// we need to measure the time of each test to compare them.
		final long start = System.nanoTime();
		final long result = calc.calculateNaive(50);
		final long end = System.nanoTime();
		System.out.println(String.format("It took %d ms for the naive implementation",
			TimeUnit.NANOSECONDS.toMillis(end - start)));
		Assert.assertEquals(0, result);
	}
	class TestClass {
		int val;
	}
	@Test
	public void calculateCached() {
		// we need to measure the time of each test to compare them.
		TestClass c = new TestClass();
		c.val = 4;
		final long start = System.nanoTime();
		final long result = calc.calculateCached(50);
		final long end = System.nanoTime();
		System.out.println(String.format("It took %d ms for the cached implementation",
			TimeUnit.NANOSECONDS.toMillis(end - start)));
		Assert.assertEquals(0, result);
	}

}
