package interviewbits.utils;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayUtils {
    public static <T> ArrayList<T> create(T ... input) {
        return new ArrayList<T>(Arrays.asList(input));
    }
}
