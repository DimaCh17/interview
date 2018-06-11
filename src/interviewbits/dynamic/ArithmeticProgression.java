package interviewbits.dynamic;

import java.util.*;

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

    private class Sequence {
        public final int start; // index
        public final int step; // step

        private int dataIndex = 0;
        private int[] data = new int[2];
        private int count = 0;

        public Sequence(int start, int next) {
            this.start = start;
            this.step = next - start;
            data[0] = start;
            data[1] = next;
            dataIndex = 1;
            count = 2;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, step);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if (other == null) {
                return false;
            }
            if (!(other instanceof Sequence)) {
                return false;
            }
            Sequence sequence = (Sequence) other;
            return start == sequence.start && step == sequence.step && count == sequence.count;
        }

        public void add(int val) {
            if (val != next()) {
                throw new RuntimeException();
            }
            data[(dataIndex + 1) % 2] = val;
            dataIndex = (dataIndex + 1) % data.length;
            count += 1;
        }


        public int next() {
            return data[dataIndex] + step;
        }

    }

    public int solve(final List<Integer> list) {
        // we keep track of two last elements per sequence, along with the total count

        // check existing sequences if they can be continued
        //Map<Integer, List<Sequence>> sequenceBuckets = new HashMap<>(); // list of sequence hides one *N
        Set<Integer> startSet = new HashSet <>();
        // holds values for each value that will be be added to a sequence.
        Map<Integer, Set<Sequence>> anticipationMap = new HashMap<>();
        int maxLen = 0;
        for (int i = 0; i < list.size(); i++) {
            int val = list.get(i);
            // use the anticipation sets here.
            Set<Integer> stepsUsed = new HashSet <>();// once the value became a part of a sequence of this step
            // it can't start a sequence with the same step from the start set.
            Set<Sequence> anticipationSet = anticipationMap.getOrDefault(val, new HashSet <>());
            List<Sequence> sequencesHit = new LinkedList <>();
            for (Sequence sequence : anticipationSet) { // (LL) don't modify the collection while iterating it

                sequence.add(val); // if the sequence lands in the same set we don't want to delete it
                int nextVal = sequence.next();

                Set<Sequence> nextSet = anticipationMap.getOrDefault(nextVal, new HashSet <>());
                nextSet.add(sequence);

                if (!anticipationSet.equals(nextSet)) {
                    sequencesHit.add(sequence); // (LL) handle the situation when we hit the same bucket
                    // in case of the same value, and step == 0
                }

                // the outcome completely
                anticipationMap.put(nextVal, nextSet);
                maxLen = Math.max(maxLen, sequence.count);
                stepsUsed.add(sequence.step);
            }
            sequencesHit.forEach(s ->  anticipationSet.remove(s));
            anticipationMap.put(val, anticipationSet);// remove the sequence from old


            for (int start : startSet) {
                //int step = val - start;
                Sequence sequence = new Sequence(start, val);
                if (!stepsUsed.contains(sequence.step)) {
                    Set <Sequence> nextSet = anticipationMap.getOrDefault(sequence.next(), new HashSet <>());
                    nextSet.add(sequence);
                    anticipationMap.put(sequence.next(), nextSet);
                    maxLen = Math.max(maxLen, sequence.count);
                }
            }
            startSet.add(val);
            maxLen = Math.max(maxLen, 1);// LL - check CC
        }
        return maxLen;
    }
}
