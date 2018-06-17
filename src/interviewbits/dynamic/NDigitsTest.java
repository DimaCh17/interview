package interviewbits.dynamic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NDigitsTest {

    NDigits nd;
    @Before
    public void setUp() throws Exception {
        nd = new NDigits();
    }

    @Test
    public void test4() {
    	assertEquals(4, nd.solve(2, 4));
    }
    
    @Test
    public void test1() {
    	assertEquals(1, nd.solve(5, 1));
    }
    
    @Test
    public void test2() {
    	assertEquals(5, nd.solve(5, 2));
    }
    
    @Test
    public void test3long() {
    	assertEquals(5, nd.solve(75, 22));
    }
}