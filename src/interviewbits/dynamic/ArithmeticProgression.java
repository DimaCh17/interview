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
    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public int solve(final List<Integer> A) {

        int n = A.size();
        if (n <= 2) {
            return n; // this is good CC, we always have the sequence with 2 or less elements
        }
        // of the same length

        int set[] = new int[n];
        for(int i = 0; i < n; i++) {
            set[i] = A.get(i);
        }
        Arrays.sort(set);
        int L[][] = new int[n][n];
        int llap = 2;
        for (int i = 0; i < n; i++) {
            L[ i ][ n - 1 ] = 2;
        }
        for (int j = n-2; j>= 1; j--)
        {
            int i = j - 1, k = j + 1;
            while (i >= 0 && k <= n-1)
            {
                if (set[i] + set[k] < 2 * set[j]) {
                    k++;
                }
                else if (set[i] + set[k] > 2 * set[j])
                {
                    L[i][j] = 2; i--;
                } else {
                    L[i][j] = L[j][k] + 1;
                    llap = Math.max(llap, L[i][j]);
                    i--; k++;
                }
            }
            while (i >= 0)
            {
                L[i][j] = 2;
                i--;
            }
        }
        return llap;
    }
}

