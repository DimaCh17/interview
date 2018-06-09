package interviewbits.dynamic;

import interviewbits.utils.ArrayUtils;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArithmeticProgressionTest {

    @Test
    public void solve() {
        ArithmeticProgression ap = new ArithmeticProgression();
        ap.showOutput = true;
        assertEquals(3, ap.solve(ArrayUtils.create(9, 4, 7, 2, 10)));
    }

    @Test
    public void cc0() {
        ArithmeticProgression ap = new ArithmeticProgression();
        ap.showOutput = true;
        assertEquals(0, ap.solve(ArrayUtils.create()));
    }

    @Test
    public void cc1() {
        ArithmeticProgression ap = new ArithmeticProgression();
        ap.showOutput = true;
        assertEquals(1, ap.solve(ArrayUtils.create(1)));
    }

    @Test
    public void cc2() {
        ArithmeticProgression ap = new ArithmeticProgression();
        ap.showOutput = true;
        assertEquals(2, ap.solve(ArrayUtils.create(2, 2)));
    }
}