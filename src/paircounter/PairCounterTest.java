package paircounter;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class PairCounterTest {

	@Test
	public void sort() {
		PairCounter counter = new PairCounter();
		int[] data = new int [] {1, 7, 16, 0};
		final int[] sorted = new int [] {0, 1, 7, 16};
		final String expected = Arrays.toString(sorted);
		counter.sort(data, 0, data.length - 1);
		final String result = Arrays.toString(data);
		assertEquals(expected, result);
	}

	@Test
	public void sortEmpty() {
		PairCounter counter = new PairCounter();
		int[] data = new int [] {};
		final int[] sorted = new int [] {};
		final String expected = Arrays.toString(sorted);
		counter.sort(data, 0, data.length - 1);
		final String result = Arrays.toString(data);
		assertEquals(expected, result);
	}	

	@Test
	public void sortOneElementy() {
		PairCounter counter = new PairCounter();
		int[] data = new int [] {1};
		final int[] sorted = new int [] {1};
		final String expected = Arrays.toString(sorted);
		counter.sort(data, 0, data.length - 1);
		final String result = Arrays.toString(data);
		assertEquals(expected, result);
	}

}
