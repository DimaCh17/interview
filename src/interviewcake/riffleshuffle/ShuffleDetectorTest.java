package interviewcake.riffleshuffle;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class ShuffleDetectorTest {

	ShuffleDetector detector;
	static final int DECK_SIZE = 52;
	@Before
	public void setUp() throws Exception {
		detector = new ShuffleDetector();
	}

	public int[][] cutStandardHalf(int cutPos) {
		return cutInHalf(cutPos, DECK_SIZE);
	}
	
	public List<Integer> join(int[] firstHalf, int[] secondHalf) {
		List<Integer> res = new ArrayList<Integer> ();
		for (int i = 0; i < firstHalf.length; i++) {
			res.add(firstHalf[i]);
		}
		for (int i = 0; i < secondHalf.length; i++) {
			res.add(secondHalf[i]);
		}
		return res;
	}

	public int[][] cutInHalf(int cutPos, int deckSize) {
		int [] firstHalf = new int [cutPos];
		for (int i = 1; i < cutPos + 1; i++) {
			firstHalf[i - 1] = i;
		}
		int [] secondHalf = new int [deckSize - cutPos];
		for (int i = cutPos; i < deckSize; i++) {
			secondHalf[i - cutPos] = i + 1;
		}
		return new int [][] {firstHalf,  secondHalf};
	}

	@Test
	public void testHashMap() {
		int cut = 0;
		HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();
		hash.get(0);
	}
	
	@Test
	public void cut0Cards() {
		int cut = 0;
		assertArrayEquals(new int [] {}, cutStandardHalf(cut)[0]);
		assertEquals(1, cutStandardHalf(cut)[1][0]);
		assertTrue(detector.isRiffleShuffled(join(cutStandardHalf(cut)[0], cutStandardHalf(cut)[1])));
		assertTrue(detector.isRiffleShuffled(join(cutStandardHalf(cut)[1], cutStandardHalf(cut)[0])));
	}
	
	@Test
	public void cutNotRiffleShuffledCards() {
		List<Integer> deck = new ArrayList<Integer>();
		assertFalse(detector.isRiffleShuffled(deck));
	}
	
	@Test
	public void cut2Cards() {
		int cut = 2;
		int [] firstHalf = cutInHalf(cut, 3)[0];
		int [] secondHalf = cutInHalf(cut, 3)[1];
		assertArrayEquals(new int [] {1, 2}, firstHalf);
		assertEquals(3, secondHalf[0]);
		assertTrue(detector.isRiffleShuffled(join(firstHalf, secondHalf)));
		assertTrue(detector.isRiffleShuffled(join(secondHalf, firstHalf)));

	}

	@Test
	public void cut1Cards() {
		int cut = 1;
		assertArrayEquals(new int [] {1}, cutStandardHalf(cut)[0]);
		assertEquals(2, cutStandardHalf(cut)[1][0]);
	}
}
