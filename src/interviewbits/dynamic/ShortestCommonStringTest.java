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
        assertEquals(10, scs.solve(ArrayUtils.create("abcd", "cdef", "fgh", "de")));
        // abcdedghde
    }

    @Test
    public void testLong() {
        // it's more than min amount of char needed
        assertEquals(45, scs.solve(ArrayUtils.create("cpsklryvmcp", "nbpbwllsrehfmx", "kecwitrsglre", "vtjmxypu")));
    }

    @Ignore
    @Test
    public void testLong2() {
        assertEquals(97, scs.solve(ArrayUtils.create(
            "qkourllircql", "smvtrmvjpr", "yagcifbarp", "lbjtunkgbfuw", "nlvyb", "tdqchahic", "xypbkkvywecd",
                "ydonbnqpjtjlbj", "jnajop", "aagbamddoe")));
    }

    @Test
    public void test39() {
        assertEquals(39, scs.solve(ArrayUtils.create(
                "qfgxmuvgfaj", "lfvenhyuhuor", "osamibdnj", "beyhkbso")));
    }
}