package interviewbits.dynamic;

import interviewbits.utils.ArrayUtils;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArithmeticProgressionTest {

    @Test
    public void solve16() {
        ArithmeticProgression ap = new ArithmeticProgression();
        assertEquals(16, ap.solve(ArrayUtils.create(
    729652, 1382614, 740882302, 688260158, 2035576, 2688538, 879350478, 3341500, 3994462, 4647424, 5300386, 174780165, 902753309, 67221396, 261170013, 5953348, 20981554, 697413800, 634979324, 6606310, 7259272, 7912234, 8565196, 365426960, 9218158, 9871120, 10524082, 214497117, 780770428, 33203066, 530134298, 207525479, 339378323, 108777178, 466377836, 135845030, 338758184, 16429964, 614474430, 366933075, 974577226, 276742682, 124388355, 808614382, 721196653, 221239448, 7325136, 544225976, 564151016, 573481752
        )));
    }

    @Test
    public void solve() {
        ArithmeticProgression ap = new ArithmeticProgression();
        assertEquals(3, ap.solve(ArrayUtils.create(9, 4, 7, 2, 10)));
    }

    @Test
    public void solve2() {
        ArithmeticProgression ap = new ArithmeticProgression();
        assertEquals(4, ap.solve(ArrayUtils.create(3, 6, 9, 12)));
    }

    @Test
    public void cc0() {
        ArithmeticProgression ap = new ArithmeticProgression();
        assertEquals(0, ap.solve(ArrayUtils.create()));
    }

    @Test
    public void cc1() {
        ArithmeticProgression ap = new ArithmeticProgression();
        assertEquals(1, ap.solve(ArrayUtils.create(1)));
    }

    @Test
    public void cc2() {
        ArithmeticProgression ap = new ArithmeticProgression();
        assertEquals(2, ap.solve(ArrayUtils.create(2, 2)));
    }


    @Test
    public void cc3() {
        ArithmeticProgression ap = new ArithmeticProgression();
        assertEquals(10, ap.solve(ArrayUtils.create(1, 1, 1, 1, 1, 1, 1, 1, 1, 1)));
    }

    @Test
    public void cc3_1() {
        ArithmeticProgression ap = new ArithmeticProgression();
        assertEquals(3, ap.solve(ArrayUtils.create(1, 1, 1)));
    }
}