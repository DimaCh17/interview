package interviewcake.reversestring;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StringReverserTest {
	StringReverser reverser;

	@Before
	public void setUp() throws Exception {
		reverser = new StringReverser();
	}

	@Test
	public void test() {
		assertEquals("abcde", reverser.reverse("edcba"));
	}

}
