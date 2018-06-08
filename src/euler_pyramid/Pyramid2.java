package euler_pyramid;
public class Pyramid2 {
    // non inclusive max Level, the actual level is from 0 to maxLevel - 1
    public final int maxLevel;
    int data[];

    public Pyramid2 (int maxLevel) {
       this.maxLevel = maxLevel;
       data = new int[getLevelElements(maxLevel)];
    }
    
    int getLevelElements(int level) {
        // level in non inclusive
        // we get average amount of elements multiplied on amount of levels.
        // we start from 1 to level, divided by 2 (not rounded) then multiply on the level count (level)
        return (int)((level + 1) / 2.0 * level); 
    }

    int getDataPos(int level, int pos) {
        return getLevelElements(level) + pos;
    }

    void put(int value, int level, int pos) {
        // insert check on argument correctness
        data[getDataPos(level, pos)] = value;
    }
    
    int get(int level, int pos) {
        return data[getDataPos(level, pos)];
    }
    public static int findMaxPath (Pyramid2 pyramid) {
        // we go from bottom (excluding the last row) and check put into each element a
        // sum of itself and a greatest of its children
        for (int level = pyramid.maxLevel - 2; level >= 0; level --) {
            for (int pos = 0; pos <= level; pos++) {
                pyramid.put(pyramid.get(level, pos) + Math.max(pyramid.get(level + 1, pos), pyramid.get(level + 1, pos + 1)), level, pos);
            }
        }
        return pyramid.get(0, 0);
    }
    public static Pyramid2 build(String rawData) throws Exception {
        String[] lines = rawData.split("\n");
        Pyramid2 result = new Pyramid2(lines.length);
        for (int level = 0; level < lines.length; level++) {
            String[] values = lines[level].trim().split(" ");
            for (int index = 0; index < values.length; index++) {
                result.put(Integer.parseInt(values[index]), level, index);
            }
        }
        return result;
    }

    // TC level = 0, count = 0
    // level = 1, count = 1
    // level = 2, count = 3
    // level = 3, count = 6
}