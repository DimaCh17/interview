package interviewbits.dynamic;

import interviewbits.utils.ArrayUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShortestCommonStringTest {

    ShortestCommonString scs;
    @Before
    public void setUp() throws Exception {
        scs = new ShortestCommonString();
    }

    @Test
    public void test() {
        assertEquals(8, scs.solve(ArrayUtils.create("abcd", "cdef", "fgh", "de")));
    }

    @Test
    public void test2() {
        scs.showOutput = false;
        assertEquals(4, scs.solve(ArrayUtils.create("abc", "bcb")));
    }

    @Test
    public void test3() {
        // it's more than min amount of char needed
        assertEquals(5, scs.solve(ArrayUtils.create("abc", "bcb", "ba")));
    }

    @Test
    public void testLong() {
        // it's more than min amount of char needed
        assertEquals(45, scs.solve(ArrayUtils.create("cpsklryvmcp", "nbpbwllsrehfmx", "kecwitrsglre", "vtjmxypu")));
    }

    public void testLong2() {
        assertEquals(97, scs.solve(ArrayUtils.create(
            "qkourllircql", "smvtrmvjpr", "yagcifbarp", "lbjtunkgbfuw", "nlvyb", "tdqchahic", "xypbkkvywecd",
                "ydonbnqpjtjlbj", "jnajop", "aagbamddoe")));
    }
    @Test
    public void mergeIn() {
        // it's more than min amount of char needed
        assertEquals("abc", scs.mergeStrings("abc", "b"));
        assertEquals("abc", scs.mergeStrings("abc", "c"));
        assertEquals("abc", scs.mergeStrings("a", "abc"));
    }

    @Test
    public void mergeOut() {
        // it's more than min amount of char needed
        assertEquals("abcd", scs.mergeStrings("abc", "d"));
        assertEquals("bcde", scs.mergeStrings("bcd", "e"));
    }

    @Test
    public void mergeOut2() {
        // it's more than min amount of char needed
        assertEquals("abdc", scs.mergeStrings("ab", "dc"));
    }
}