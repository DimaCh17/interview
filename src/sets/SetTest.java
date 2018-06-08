package sets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class SetTest {

	//https://docs.oracle.com/javase/7/docs/api/java/util/HashSet.html
	public static void main() {
		Long startTime = System.nanoTime();
		Collection<Integer> data  = new ArrayList<Integer>();
		data.add(3);
		data.add(4);
		HashSet<Integer> set = new HashSet<Integer>(data);

		long stopTime = System.nanoTime();
		System.out.println(stopTime - startTime);

	}
}
