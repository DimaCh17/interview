package interviewbits.dynamic;

import java.util.*;
import java.util.function.Predicate;

/*
Find longest Arithmetic Progression in an integer array and return its length. More formally, find longest sequence of indices, 0 < i1 < i2 < … < ik < ArraySize(0-indexed) such that sequence A[i1], A[i2], …, A[ik] is an Arithmetic Progression. Arithmetic Progression is a sequence in which all the differences between consecutive pairs are the same, i.e sequence B[0], B[1], B[2], …, B[m - 1] of length m is an Arithmetic Progression if and only if B[1] - B[0] == B[2] - B[1] == B[3] - B[2] == … == B[m - 1] - B[m - 2].
Examples
1) 1, 2, 3 (All differences are equal to 1)
2) 7, 7, 7 (All differences are equal to 0)
3) 8, 5, 2 (Yes, difference can be negative too)

Samples
1) Input: 3, 6, 9, 12
Output: 4

2) Input: 9, 4, 7, 2, 10
Output: 3(If we choose elements in positions 1, 2 and 4(0-indexed))

(ID1) we store the map between the step and the last element with such progression.
one element can be in multiple progressions.
update max_len every step.
each time we find new element, we look at all sequences
it may also start a new sequence, as the sequence may

each element either to continue existent sequences, or start a new one, both situations need to be handled

(1) iterate cur
(2) check if if fits any existing sequence. the sequence may have the last element, and step, this the anticipated element can
be found. for the same start element can be many sequences with different steps. for the same steps it may be
multiple sequence with different start elements.
we may store anticipated elements for each sequence, thus it will be simpler to handle.


TC1 - sequence starts with the 0 index element
TC2 - sequence starts with an element other than the first one.

(ID2)

*/

public class ArithmeticProgression {
    public boolean showOutput = false;

    private void print(String format, Object ... args) {
        if (!showOutput) return;
        System.out.print(String.format(format, args));
    }

    private <T> void printIfDiffers(T was, T now) {
        if (!was.equals(now)) {
            print("The value changed from %s to %s", was, now);
        }
    }
    private class Sequence {
        public final int start; // index
        public final int step; // step

        private final Deque<Integer> elements = new LinkedList <>();
        private int count = 0;

        public Sequence(int start, int next) {
            this.start = start;
            this.step = next - start;
            count = 2;
            elements.addFirst(start);
            elements.addLast(next);
            count = 2;
        }

        // TODO: check if to handle out of sequence elements by ignoring them.
        public void add(int val) {
            elements.addLast(val);
            elements.removeFirst();
            count += 1;
        }

        public int safeAdd(int val) {
            if (val == next()) {
                add(val);
            }
            return count;
        }

        private int last() {
            return elements.peekLast();
        }

        public int next() {
            return last() + step;
        }

    }

    public int solve(final List<Integer> list) {
        // we keep track of two last elements per sequence, along with the total count

        // check existing sequences if they can be continued
        Map<Integer, List<Sequence>> sequenceBuckets = new HashMap<>();
        Set<Integer> startSet = new HashSet <>();
        int maxLen = 0;
        for (int i = 0; i < list.size(); i++) {
            int val = list.get(i);
            for (int step : sequenceBuckets.keySet()) {
                for (Sequence sequence : sequenceBuckets.get(step)) {
                    int wasMax = maxLen;
                    sequence.safeAdd(val); // LL too complicated
                    printIfDiffers(wasMax, maxLen);
                    maxLen = Math.max(maxLen, sequence.count); //
                }
            }
            for (int start : startSet) {
                int newKey = val - start;
                Sequence newSequence = new Sequence(start, val);
                List<Sequence> sequenceList = sequenceBuckets.getOrDefault(newKey, new ArrayList <>());
                sequenceList.add(newSequence);
                sequenceBuckets.put(newKey, sequenceList);
                maxLen = Math.max(maxLen, newSequence.count); //
            }
            startSet.add(val);
            maxLen = Math.max(maxLen, 1);// LL - check CC
        }
        return maxLen;
    }
}
