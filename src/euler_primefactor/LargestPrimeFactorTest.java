package euler_primefactor;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LargestPrimeFactorTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		LargestPrimeFactor factor = new LargestPrimeFactor();
		assertEquals(29, factor.getLargestPrimeFactor(13195l));
	}	
	
	@Test
	public void test2() {
		LargestPrimeFactor factor = new LargestPrimeFactor();
		assertEquals(6857, factor.getLargestPrimeFactor(600851475143l));
	}	

}
