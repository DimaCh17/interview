package euler_pyramid;

public class Pyramid {
	final int [] data;
	final public int maxLevel; 
	int currentLevel;
	public Pyramid (int maxLevel) {
		data = new int[(maxLevel + 1) * maxLevel /2];
		this.maxLevel = maxLevel;
	}

	public int getDataPos(int level, int pos) {
		// get the sum of all indices and then plus 1
		
		// we need to calculate how many elements are on the previous levels
		// 0 - 0, 1 - 1, 2 - 3, 
		// (level - 1) * (level - 1)
		if (level == 0) {
			return 0;
		}
		int res = 0;
		for (int i = 1; i <= level; i++) {
			res += i;
		}
		return res + pos;
	}

	public int get(int level, int pos) {
		return data[getDataPos(level, pos)];
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < maxLevel; i++) {
			builder.append(i);
			builder.append(": ");
			for (int j = 0; j <= i; j++) {
				builder.append(get(i, j));
				if (j != i) {
					builder.append(" ");
				}
			}
			builder.append("\n");
		}
		return builder.toString();
	}

	public void put(int value, int level, int pos) {
		if (level > maxLevel) {
			throw new IllegalArgumentException("level");
		}
		if (pos > level) {
			throw new IllegalArgumentException("pos");
		}
		data[getDataPos(level, pos)] = value;
	}

	public Pyramid add(String string) {
		int counter = 0;
		if (currentLevel >= maxLevel) {
			throw new IllegalArgumentException("The current level is more than maximum");
		}
		for (String value: string.split(" ")) {
			put(Integer.parseInt(value), currentLevel, counter);
			counter++;
		}
		currentLevel++;
		return this;
	}
}
