package interviewbits.dynamic;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class Color3BoardTest {

    Color3Board cb;
    @Before
    public void setUp() throws Exception {
        cb = new Color3Board();
    }

    @Test
    public void getInitialStates() {
        //cb.showOutput = true;
        Set<?> res = cb.getAllStates();
        assertEquals(36, res.size());
    }

    @Test
    public void getTranstions() {
        cb.showOutput = false;
        //
        Set<?> res = cb.getFinalTransitions(3);
        assertEquals(36, res.size());
    }

    @Test
    public void solve() {
        cb.showOutput = false;
        assertEquals(36, cb.solve(1));
    }

    @Test
    public void solve87() {
        cb.showOutput = false;
        assertEquals(277700620, cb.solve(87));
    }

    @Test
    public void solve2() {
        cb.showOutput = false;
        assertEquals(588, cb.solve(2));
    }
}