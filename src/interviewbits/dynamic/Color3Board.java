package interviewbits.dynamic;

import java.util.*;

/*
Given a 3Xn board, find the number of ways to color it using at most 4 colors such that no two adjacent boxes have same color. Diagonal neighbors are not treated as adjacent boxes.
Output the ways%1000000007 as the answer grows quickly.

1<= n < 100000

Example:
Input: n = 1
Output: 36


(ID1) each row is 3 cells. we consider it's a state. for each state we calculate
how may ways to transition to each further state, as they have a definite number
(4 power 3 minus 1). we store amount of transitions from each state in
a cache, thus don't have to recalc transisitons again, only unique
transitions. each jump we multiple found transitions on the previously existing
we also may cache transitions that are not possible, thus we quickly find
what are possible states.


in the end we need to check if extra step is needed to use the current row
*/
public class Color3Board {
    public boolean showOutput = false;
    private void print(String format, Object ... args) {
        if (!showOutput) return;
        System.out.print(String.format(format, args));
    }
    private class Row {
        private int colorData = 0;

        Row(Row row) {
            colorData = row.colorData;
        }

        Row(int ... colors) {
            // colors go from 0 to 4
            // 0 means no color set
            // so we use the colorData, to store all of them
            if (colors.length != 3) {
                throw new RuntimeException("Only 3 cells are supported");
            }
            int mask = 0b111;
            int shift = 0;
            for (int i = 0; i < colors.length; i++) {
                // we shift the mask, use the and operator to clea
                int maskedColor = colors[i] & mask;
                maskedColor <<= shift;
                colorData |= maskedColor;
                shift += 3;
            }
            // we use an extra bit to store empty // invalida colors
        }

        public int getColor(int index) {
            int shift = index * 3;
            int mask = 0b111 << shift;
            int maskedColor = colorData & mask;
            maskedColor >>= shift;
            return maskedColor;
        }

        public void setColor(int index, int color) {
            int mask = 0b111;
            int maskedColor = color & mask;
            maskedColor <<= index * 3;;
            colorData |= maskedColor;
        }

        @Override
        public String toString() {
            return String.format("%d %d %d", getColor(0), getColor(1), getColor(2));
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Row)) {
                return false;
            }
            return ((Row) obj).colorData == colorData;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(colorData);
        }

        public boolean isValid() {
            // returns false if the neighboring cells have equal colors.
            if (colorData == 0) {
                return false;
            }
            int last = getColor(0);
            for (int i = 1; i < 3; i++) {
                int cur = getColor(i);
                if (cur == 0) {
                    return false;
                }
                if (cur == last) {
                    return false;
                }
                last = cur;
            }
            return true;
        }

        public boolean isNeighbor(Row row) {
            for (int i = 0; i < 3; i++) {
                int color = getColor(i);
                int otherColor = row.getColor(i);
                if (color == 0 || otherColor == 0) {
                    return true;
                }
                if ((getColor(i) ^ row.getColor(i)) == 0) {
                    return false;
                }
            }
            return true;
        }
    }

    /*
    private List<Row> getTransitionsCached(Row source, Map<Row, List<Row>> cache) {
        if (cache.containsKey(source)) {
            return cache.get(source);
        }
        // otherwise permutate and find all results from there.
        List<Row> transitions = getTransitions(source);
        cache.put(source, transitions);
        return transitions;
    }*/

    public Set<Row> getAllStates() {
        // return all possible combinations of the row
        // which has a limited (4 pow 3) - 1 amount
        // to prepare to calculate transitions
        // (??) do the states need to be valid?
        // (..) possible, but not definite
        // ( ) postpone this decision till later.
        Row seedRow = new Row(0,0,0); // this color has no conflicts
        return getTransitions(seedRow);
    }


    public Set<Row> getFinalTransitions(int steps) {
        Row seedRow = new Row(0,0,0); // this color has no conflicts
        return getTransitions(seedRow, steps);
    }

    public Set<Row> geTransitions(int[] colors, int steps) {
        Row seedRow = new Row(colors); // this color has no conflicts
        return getTransitions(seedRow, steps);
    }

    public Set<Row> getTransitions(Row source, int steps) {
        // return all valid transitions for this row.
        // permutate the row in all possible colors, track possible transitions
        Set<Row> result = new HashSet <>();
        Deque<Row> dq = new LinkedList<>();
        dq.addLast(source);
        Deque<Integer> mutateDq = new LinkedList<>();
        mutateDq.addLast(0);
        Deque<Integer> stepsDq = new LinkedList<>();
        stepsDq.addLast(0);
        int count = 0;
        while (!dq.isEmpty()) {
            Row row = dq.removeFirst();
            int mutateIndex = mutateDq.removeFirst();
            int step = stepsDq.removeFirst();
            print(count++ + "/" + step + " :" + row);
            if (!row.equals(source)) {
                if (row.isValid() && row.isNeighbor(source) && step == steps) {
                    result.add(row);
                    print(" +\n");
                } else {
                    print(" -\n");
                }
            } else {
                print(" =\n");
            }
            if (mutateIndex < 3) {
                for (int color = 1; color <= 4; color++) {
                    Row mutant = new Row(row);
                    mutant.setColor(mutateIndex, color);
                    dq.addLast(mutant);
                    mutateDq.addLast(mutateIndex + 1);
                    stepsDq.addLast(step + 1);
                }
            }

        }
        return result;
    }
    private Set<Row> getTransitions(Row source) {
        // return all valid transitions for this row.
        // permutate the row in all possible colors, track possible transitions
        Set<Row> result = new HashSet <>();
        Deque<Row> dq = new LinkedList<>();
        dq.addLast(source);
        Deque<Integer> mutateDq = new LinkedList<>();
        mutateDq.addLast(0);
        int count = 0;
        while (!dq.isEmpty()) {
            Row row = dq.removeFirst();
            int mutateIndex = mutateDq.removeFirst();

            print(count++ + ":" + row);
            if (!row.equals(source)) {
                if (row.isValid() && row.isNeighbor(source)) {
                    result.add(row);
                    print(" +\n");
                } else {
                    print(" -\n");
                }
            } else {
                print("  ");
            }
            if (mutateIndex < 3) {
                for (int color = 1; color <= 4; color++) {
                    Row mutant = new Row(row);
                    mutant.setColor(mutateIndex, color);
                    dq.addLast(mutant);
                    mutateDq.addLast(mutateIndex + 1);
                }
            }

        }
        return result;
    }

    public int solve(int n) {

        // so there is no conflict with any of the existing colors
        // then we find all exit states with 4 colors that go from
        // this state, basically those are all we got. then put
        // all of the states to the queue (or use recursion to walk them)
        // through

        // we go from left to right
        // put all reasonable combinations to the queue.
        // store a cache of transitions thus the same row
        // needs to recalculations
        int totalCount = 0;
        Deque<Row> queue = new LinkedList<>();
        Deque<Integer> levels = new LinkedList<>();
        getAllStates().forEach( r -> {queue.addLast(r); levels.addLast(1);});
        Set<Row> allStates = getAllStates();
        Map<Row, Set<Row>> transMap = new HashMap<>();

        while (!queue.isEmpty()) {
            Row row = queue.removeFirst();
            int level = levels.removeFirst();
            if (level == n) {
                //
                totalCount++;
                totalCount%=1000000007;
                continue;// we reached the end (TERM1)
            }
            // ? backflow problem - how not to get to the same state where
            // we have been before?
            if (transMap.containsKey(row)) {
                transMap.get(row).forEach(r -> {queue.addLast(r); levels.addLast(level + 1);}); // just keep counting
            } else {
                // need to figure out all transitions and add them to the map
                // (?) and may be to the reverse map
                allStates.stream().filter(r -> r.isNeighbor(row)).forEach(
                    r -> {
                        Set<Row> forwardSet = transMap.getOrDefault(r, new HashSet <>());
                        transMap.put(r, forwardSet);
                        Set<Row> reverseSet = transMap.getOrDefault(row, new HashSet <>());
                        transMap.put(row, reverseSet);
                        queue.addLast(r);
                        levels.addLast(level + 1);
                    });
            }

            // int nextMutate = mutateIndex + 1;
        }
        return totalCount; // (??) How to we calculate correct mutations.
    }
}
