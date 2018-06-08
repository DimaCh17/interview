package epam;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class CycleFinderTest {

	@Before
	public void setUp() throws Exception {
	}

	// outputs the next elements
	private static String print(List list, int numElements) {
		ListElement element = list.head;
		int count = numElements;
		StringBuilder stringBuilder = new StringBuilder();
		while (element != null && count > 0) {
			count--;
			stringBuilder.append(element.value);
			stringBuilder.append(",");
			// !PUNT forgot to move the element to the next pne
			element = element.next;
		}
		return stringBuilder.toString();
	}

	@Test
	public void testCycle() {
		List list = List.create(new Integer[] {});
		ListElement loopElement = null;
		ListElement last = null;
		for (int i = 0; i < 20; i++) {
			last = list.add(i);
			if (i == 0) {
				loopElement = last;
			}
		}
		// we make a loop element;
		loopElement.next = last;
		System.out.println("To String");
		System.out.println(list);
		System.out.println("To Print");
		// we print next 40 elements, and we have a loop there
		System.out.println(print(list, 41));
		assertTrue(CycleFinder.findLoop(list));
	}

	@Test
	public void testNoCycle() {
		List list = List.create(new Integer[] {});
		for (int i = 0; i < 20; i++) {
			list.add(i);
		}
		assertFalse(CycleFinder.findLoop(list));
	}

}
