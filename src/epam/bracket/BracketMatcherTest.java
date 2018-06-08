package epam.bracket;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BracketMatcherTest {

	BracketMatcher bracketMatcher;
	@Before
	public void setUp() throws Exception {
		bracketMatcher = new BracketMatcher();
	}

	
	@Test
	public void onePair() {
		assertTrue(bracketMatcher.match("{}"));
	}
	
	@Test
	public void twoPair() {
		assertTrue(bracketMatcher.match("{}"));
	}
	
	@Test
	public void emtpy() {
		assertTrue(bracketMatcher.match("{}"));
	}
	
	@Test
	public void oneSymbol() {
		assertFalse(bracketMatcher.match("{"));
	}
	
	@Test
	public void mismatchedPairs() {
		assertFalse(bracketMatcher.match("{]"));
	}
	

}
