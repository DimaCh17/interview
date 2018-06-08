package interviewcake.kthnode;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class KthNodeFinderTest {

	KthNodeFinder<String> finder;
	@Before
	public void setUp() throws Exception {
		finder = new KthNodeFinder<String>();
	}
	
	private ListItem<String> list;

	@Test
	public void test0() {
		String str = "It goes from the head to the tail";
		list = buildList(str);
		assertEquals("tail", finder.find(list, 0).value);
	}
	
	@Test
	public void testNull() {
		assertNull(finder.find(null, 2));
	}

	@Test
	public void test2() {
		String str = "It goes from the head to the tail";
		list = buildList(str);
		assertEquals("to", finder.find(list, 2).value);
	}

	@Test
	public void test3() {
		String str = "It goes from the head to the tail";
		list = buildList(str);
		assertEquals("head", finder.find(list, 3).value);
	}

	private String printList(ListItem<String> head) {
		// need to handle situation when it ends and no space needs to be added
		ListItem<String> current = head;
		StringBuilder builder = new StringBuilder();
		while (current != null) {
			if (current != head) {
				builder.append(" ");
			}
			builder.append(current.value);
			current = current.next;
		}
		return builder.toString();
	}
	private ListItem<String> buildList(String string) {
		String[] words = string.split(" ");
		ListItem<String> head = new ListItem<String>();
		head.value = words[0];
		ListItem<String> last = head;
		for (int i = 1; i < words.length; i++) {
			String str = words[i];
			ListItem<String> current = new ListItem<String> ();
			current.value = str;
			last.next = current;
			last = current; // PUN! did not mve the cursor to the next position
		}
		return head;
	}

}
