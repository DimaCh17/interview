package structures;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BinaryHeapTest {
	
	BinaryHeap heap;

	@Before
	public void setUp() throws Exception {
		heap = new BinaryHeap(0);
	}

	@Test
	public void testOneElement() {
		heap.insert(1);
		assertEquals("1", heap.toString());
	}
	
	@Test
	public void test2Elenents() {
		heap.insert(1);
		heap.insert(2);
		assertEquals("2\\n1", heap.toString());
	}
	
	@Test
	public void test3Elements() {
		heap.insert(1);
		heap.insert(2);
		heap.insert(3);
		assertEquals("3\\n1 2", heap.toString());
	}
	
	@Test
	public void test3ElementsAgain() {
		heap.insert(1);
		heap.insert(3);
		heap.insert(2);
		assertEquals("3\\n1 2", heap.toString());
	}
	
	@Test
	public void test4Elements() {
		heap.insert(1);
		heap.insert(3);
		heap.insert(2);
		heap.insert(4);
		assertEquals("4\\n3 2\\n1", heap.toString());
	}
	
	@Test
	public void testElevels() {
		assertEquals(1, heap.getLevel(1));
		assertEquals(2, heap.getLevel(2));
		assertEquals(2, heap.getLevel(3));
		assertEquals(3, heap.getLevel(4));
		assertEquals(4, heap.getLevel(8));
	}

}
