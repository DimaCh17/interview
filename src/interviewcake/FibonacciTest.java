package interviewcake;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FibonacciTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Fibbonacci fibonacci = new Fibbonacci();
		for (int i = 0; i <= 10; i++) {
			System.out.println(String.format("fib(%d):(%d)", i, fibonacci.fib(i)));
		}
		
	}

}
