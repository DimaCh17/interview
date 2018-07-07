package interviewbits.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringJoiner;

public class ArrayUtils {
    public static <T> ArrayList<T> create(T ... input) {
        return new ArrayList<T>(Arrays.asList(input));
    }
    public static ArrayList<ArrayList<Integer>> parseMatrix(String input) {
    	ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    	input = input.trim();
    	input = input.substring(1, input.length() - 1); // skip the brackets
    	String[] lines = input.split("\\],\\[");
    	for (String line : lines) {
    		ArrayList<Integer> list = new ArrayList<Integer>();
    		res.add(list);
    		line = line.replace("[","");
    		line = line.replace("]","");
    		String[] values = line.split(",");
    		for (String value : values) {
    			list.add(Integer.parseInt(value));
    		}
    	}
    	return res;
    }
    public static <T> ArrayList<ArrayList<T>> createMatrix(int cols,
    		@SuppressWarnings("unchecked") T ... input) {
    	ArrayList<ArrayList<T>> res = new ArrayList<ArrayList<T>>();
    	int rows = input.length / cols;
    	int pos = 0;
    	for (int row = 0; row < rows; row++) {
    		ArrayList<T> line = new ArrayList<>();
    		for (int col = 0; col < cols; col++) {
    			line.add(input[pos]);
    			pos++;
    		}
    		res.add(line);
    	}
        return res;
    }

	public static String printMatrix(ArrayList<ArrayList<Integer>> input) {
		StringJoiner sj = new StringJoiner(",","[","]");
		for (ArrayList<Integer> line : input) {
			StringJoiner lineJoiner = new StringJoiner(",","[","]");
			for (Integer val : line) {
				lineJoiner.add(Integer.toString(val));
			}
			sj.add(lineJoiner.toString());
		}
		return sj.toString();
	}
}
