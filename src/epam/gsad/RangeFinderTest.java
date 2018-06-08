package epam.gsad;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

public class RangeFinderTest {

	private RangeFinder rangeFinder;
	
	@Before
	public void setUp() throws Exception {
		rangeFinder = new RangeFinder();
	}

	@Test
	public void testFindRange() {
		assertTrue(rangeFinder.find("{}"));
	}
	
	public void testTreeSet() {
		Set<Integer> set = new TreeSet<Integer>();

		for (int i = 0; i < 1000000; i++) {
			set.add(i);
		}
		boolean contains = false;
			long startTime = System.currentTimeMillis();;
			for (int j = 0; j < 1000; j++) {
			for (int i = 0; i < 1000000; i++) {
				contains = set.contains(i);
			}
			}
		long elapsed = System.currentTimeMillis() - startTime;
		assertEquals(0, elapsed);
		assertEquals(true, contains);
	}
	
	public void testHashSet() {
		Set<Integer> set = new HashSet<Integer>();

		for (int i = 0; i < 1000000; i++) {
			set.add(i);
		}
		boolean contains = false;
			long startTime = System.currentTimeMillis();;
			for (int j = 0; j < 1000; j++) {
			for (int i = 0; i < 1000000; i++) {
				contains = set.contains(i);
			}
			}
		long elapsed = System.currentTimeMillis() - startTime;
		assertEquals(0, elapsed);
		assertEquals(true, contains);
	}
}
