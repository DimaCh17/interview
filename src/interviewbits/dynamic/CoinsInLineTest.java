package interviewbits.dynamic;

import static org.junit.Assert.*;
import interviewbits.utils.ArrayUtils;

import org.junit.Before;
import org.junit.Test;

public class CoinsInLineTest {

	CoinsInLine cil;
	@Before
	public void setUp() throws Exception {
		cil = new CoinsInLine();
	}

	@Test
	public void test() {
		cil.showOutput = true;
		assertEquals(6, cil.maxcoin(ArrayUtils.create(1, 2, 3, 4)));
	}

}
