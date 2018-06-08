package interval;

import static org.junit.Assert.*;

import org.junit.Test;

public class IntervalTest {


	@Test
	public void test() {
		IntervalCollection collection = new	IntervalCollection();
		collection.add(0, 1);
		collection.add(0, 2);
		System.out.println(collection);
		assertEquals(1, collection.size());
	}

}
