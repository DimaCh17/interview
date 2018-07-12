package interviewbits.dynamic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EvaluateTrueTest {

	EvaluateTrue et;
	@Before
	public void setUp() throws Exception {
		et = new EvaluateTrue();
	}

	@Test
	public void test() {
		assertEquals(1, et.cnttrue("T|F"));
	}
	

	@Test
	public void tes2t() {
		assertEquals(0, et.cnttrue("T&F"));
	}

}
