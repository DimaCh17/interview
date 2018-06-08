package epam.cycles;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ListWalkerTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testNoCycle() {
		List list = new List();
		ListItem last = null;
		for (int i = 0; i < 5; i++) {
			ListItem current = list.add(i);
			if (i == 0) {
				last = current;
			}
		}
		last.next = list.head;
		assertTrue(ListWalker.hasLoop(list));
	}
	
	@Test
	public void testCycle() {
		List list = new List();
		for (int i = 0; i < 5; i++) {
			list.add(i);
		}
		assertFalse(ListWalker.hasLoop(list));
	}

}
