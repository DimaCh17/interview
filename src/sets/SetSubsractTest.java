package sets;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class SetSubsractTest {

	@Test
	public void emptyLeft() {
		TreeSet<Integer> set1 = new TreeSet<Integer>();
		SetSubstract ss1 = new SetSubstract();
		Set<Integer> res = ss1.substract(set1, new TreeSet<Integer>());
		assertEquals("[]", print(res));
	}

	@Test
	public void emptyRight() {
		TreeSet<Integer> set1 = new TreeSet<Integer>();
		set1.add(1);
		SetSubstract ss1 = new SetSubstract();
		Set<Integer> res = ss1.substract(set1, new TreeSet<Integer>());
		assertEquals("[1]", print(res));
	}
	
	@Test
	public void testOne() {
		TreeSet<Integer> set1 = new TreeSet<Integer>();
		set1.add(1);
		set1.add(2);
		TreeSet<Integer> set2 = new TreeSet<Integer>();
		set2.add(0);
		SetSubstract ss1 = new SetSubstract();
		Set<Integer> res = ss1.substract(set1, set2);
		assertEquals("[1, 2]", print(res));
	}
	
	@Test
	public void test2() {
		TreeSet<Integer> set1 = new TreeSet<Integer>();
		set1.add(1);
		set1.add(2);
		TreeSet<Integer> set2 = new TreeSet<Integer>();
		set2.add(0);
		set2.add(3);
		SetSubstract ss1 = new SetSubstract();
		Set<Integer> res = ss1.substract(set1, set2);
		assertEquals("[1, 2]", print(res));
	}
	
	@Test
	public void test3() {
		TreeSet<Integer> set1 = new TreeSet<Integer>();
		set1.add(1);
		set1.add(2);
		TreeSet<Integer> set2 = new TreeSet<Integer>();
		set2.add(0);
		set2.add(1);
		SetSubstract ss1 = new SetSubstract();
		Set<Integer> res = ss1.substract(set1, set2);
		assertEquals("[1, 2]", print(res));
	}
	
	String print(Set<Integer> res) {
		return Arrays.toString(res.toArray());
		
	}

}
