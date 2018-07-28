package interviewbits.dynamic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LongestValidParenthesesTest {

	LongestValidParentheses lvp;
	@Before
	public void setUp() throws Exception {
		lvp = new LongestValidParentheses();
	}

	@Test
	public void test2() {
		assertEquals(2, lvp.longestValidParentheses("(()"));
	}

	@Test
	public void test4() {
		assertEquals(4, lvp.longestValidParentheses(")()())"));
	}
	
	@Test
	public void test5() {
		assertEquals(4, lvp.longestValidParentheses("(()()("));
	}
	
	@Test
	public void test6() {
		assertEquals(4, lvp.longestValidParentheses("(())((()"));
	}
	
	@Test
	public void test7() {
		assertEquals(4, lvp.longestValidParentheses("(()()("));
	}
	

	@Test
	public void test30	() {
		lvp.showOutput = true;
		assertEquals(30,
			lvp.longestValidParentheses(")()))(())((())))))())()(((((())())((()())(())((((())))())((()()))(()(((()()(()((()()))(())()))((("));
	}
}
