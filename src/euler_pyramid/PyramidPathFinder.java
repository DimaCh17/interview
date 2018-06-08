package euler_pyramid;

public class PyramidPathFinder {

	// https://projecteuler.net/problem=18
	
	public static void main(String[] args) {
		/*
		Pyramid p = new Pyramid(4);
		System.out.print(p.add("3")
			.add("7 4")
			.add("2 4 6")
			.add("8 5 9 3")
			.toString());*/
		Pyramid p = new Pyramid(15);
		p.add("75")
		.add("95 64")
		.add("17 47 82")
		.add("18 35 87 10")
		.add("20 04 82 47 65")
		.add("19 01 23 75 03 34")
		.add("88 02 77 73 07 63 67")
		.add("99 65 04 28 06 16 70 92")
		.add("41 41 26 56 83 40 80 70 33")
		.add("41 48 72 33 47 32 37 16 94 29")
		.add("53 71 44 65 25 43 91 52 97 51 14")
		.add("70 11 33 28 77 73 17 78 39 68 17 57")
		.add("91 71 52 38 17 14 91 43 58 50 27 29 48")
		.add("63 66 04 68 89 53 67 30 73 16 69 87 40 31")
		.add("04 62 98 27 23 09 70 98 73 93 38 53 60 04 23");
		System.out.println("Max path sum is: ");
		System.out.print(getMaxPathValue(p));
		
	}
	
	public static int getMaxPathValue (Pyramid p) {
		// we move from bottom to top, and calculate the max sum from the 
		// current element and max of their child sums
		// we may modify the original pyramid not to store extra space
		for (int level = p.maxLevel - 2; level >= 0; level--) {
			for (int pos = 0; pos <= level; pos++) {
				p.put(p.get(level, pos) + Math.max(p.get(level + 1, pos),
						p.get(level + 1, pos + 1)), level, pos);
			}
		}
		return p.get(0, 0);
	}
	/*
	public static int getMaxPathValue (Pyramid p) {
		int [] path = findMaxPath(p);
		int sum = 0;
		for (int i = 0; i < path.length; i++) {
			sum += path[i];
		}
		return sum;
	}

	public static int [] findMaxPath (Pyramid p) {
		// each element will contain a path from the previous one
 		Pyramid paths = new Pyramid(p.maxLevel);
		// the sum for the previously calculated paths
		Pyramid sums = new Pyramid(p.maxLevel);
		for (int pos = p.maxLevel - 1; pos >= 0; pos--) {
			// we put the value of elements as the summ of the tree
			// to simplify the cycle
			sums.put(p.get(p.maxLevel - 1, pos), p.maxLevel - 1, pos);
		}
		for (int level = p.maxLevel - 2; level >=  0; level--) {
			for (int pos = 0; pos <= level; pos++) {
				int childPos = pos;
				if (sums.get(level + 1, pos + 1) > sums.get(level + 1, pos)) {
					childPos = pos + 1;
				} 
				paths.put(childPos, level, pos);
				// we need to update the sum with the
				// value of the current element and the child
				sums.put(sums.get(level + 1, childPos) + p.get(level, pos),
						level, pos);
			}
		}
		// we walk thought the path, and put elements based on this path
		// from the original data to the result
		int[] res = new int[p.maxLevel];
		int pos = 0;
		for (int level = 0; level < p.maxLevel; level++) {
			res[level] = p.get(level, pos);
			pos = paths.get(level, pos);
		}
		return res;
	}
	*/
}
