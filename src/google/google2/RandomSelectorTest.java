package google.google2;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class RandomSelectorTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		RandomSelector selector = new RandomSelector();
		ArrayList<Integer> data = new ArrayList<Integer>();
		for (int i = 0; i < 1000; i++) {
			data.add(i);
		}
		System.out.print(Arrays.toString(selector.select(data, 10).toArray()));
	}

}
