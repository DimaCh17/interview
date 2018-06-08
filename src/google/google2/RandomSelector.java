package google.google2;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

// http://stackoverflow.com/questions/54059/efficiently-selecting-a-set-of-random-elements-from-a-linked-list
// http://www.geeksforgeeks.org/reservoir-sampling/
public class RandomSelector {
	public List<Integer> select(List<Integer> items, int k) {
		int elementsSeen = 0;
		List<Integer> result = new ArrayList<Integer>();
		
		for (; elementsSeen < k; elementsSeen++) {
			result.add(items.get(elementsSeen));
		}
		// after the first n elements elementsSeen = k
		// we get a random number from 0 to N (current), inclusive
		Random generator = new Random();
		for (; elementsSeen < items.size(); elementsSeen++) {
			Integer element = items.get(elementsSeen);
			int index = generator.nextInt(elementsSeen + 1);
			if (index < result.size()) {
				result.set(index, element);
			}
		}
		return result;
	}
	
	public List<Integer> select2(Collection<Integer> items, int n) {
	// we need to assign random weight
		Iterator<Integer> iterator = items.iterator();
		List<Integer> buffer = new ArrayList<Integer> ();
		while (iterator.hasNext() && buffer.size() < n) {
			buffer.add(iterator.next());
		}
		int elements_seen = buffer.size();

		Random generator = new Random();
		// TODO: Add a chart displaying which element got selected
		while (iterator.hasNext()) {
			// from 0.0 (inclusive) to 1.0 (not inclusive)
			Integer element = iterator.next();
			// !PUNT didn't increase the element seen count
			elements_seen++; // 11 should be from 0 to 10 (inclusive)
			int index = generator.nextInt(elements_seen);
			if (index < n) {
				// old element to replace;
				buffer.set(index, element);
			}
		}
		return buffer;
	}
}
