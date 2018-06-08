package euler_largetpolindrom;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LargestPolidromTest {
	
	LargestPolindrom palindrom;

	@Before
	public void setUp() throws Exception {
		palindrom = new LargestPolindrom();
	}

	@Test
	public void testIsPalindrom() {
		Assert.assertTrue(palindrom.isPolinome(99099));
	}

	@Test
	public void testIsPalindromFalse() {
		Assert.assertFalse(palindrom.isPolinome(99098));
	}

}
