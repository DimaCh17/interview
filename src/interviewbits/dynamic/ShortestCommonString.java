package interviewbits.dynamic;

/*
Given a set of strings. Find the length of smallest string which
has all the strings in the set as substring

Constraints:
1) 1 <= Number of strings <= 18
2) Length of any string in the set will not exceed 100.

Example:
Input: [“abcd”, “cdef”, “fgh”, “de”]
Output: 8 (Shortest string: “abcdefgh”)

(ID1) go from short string to longer ones, and see if it matches all substrings.
(1) the previous step can be used as such. if a string has been matched then no need
to match it.
(2) we don't want to go to longer strings unless shorter strings have been reviewed
(3) start with a one letter.
check if it matched

(ID2) we get a concatenation of all strings, that we know as matching
then we try to change it so it will still match it

(1) prime factor, divide on min set and extra array (they are not sets, as there
are duplicate)
(2) start with min set
(3) sort the current set
(4) permutate the current set, using deque, and index to permutate, shift
   a char to the top
(5) check the set if matching. if yes, (TERM1)
(6) once permutations are done, move a char from the exta set to the main one
(7) loop (3)
*/


import java.util.*;
import java.util.function.BiFunction;

public class ShortestCommonString {
    public int solve(ArrayList<String> words) {
        int bitMask = 1;
        int mask = 0;
        for (int i = 0; i < words.size(); i++) {
            mask |= bitMask;
            bitMask <<= 1;
        }
        initCache(mask + 1);
        return go2("", mask, words, 0).length();
    }

    private void initCache(int size) {
        cache = new String[size][2];
    }

    public String solve2(ArrayList<String> words) {

        Map<Character, Integer> minSet = new HashMap <>(); // (UEC)
        int bitMask = 1;
        int mask = 0;
        for (int i = 0; i < words.size(); i++) {
            mask |= bitMask;
            bitMask <<= 1;
        }
        initCache(mask + 1);

        return go2("", mask, words, 0);
    }

    private String[][] cache;
    private String go2(String merged, int mask, ArrayList<String> words, int right) {

        int idx = 0;
        String res = "";
        if (mask == 0) {
            return merged;
        }
        if (cache[mask][right] != null) {
            return cache[mask][right];
        }
        while (idx < words.size()) {
            int bitMask = 1 << idx;
            if ((bitMask & mask) != 0) {
                String mixWord = words.get(idx);
                if (mixWord.equals("ydonbnqpjtjlbj")) {
                //if (mixWord.equals("ydonbnqpjtjlbj")) {
                    //printState(mask, merged, mixWord, "");
                }
                String child1 = mergeStrings(merged, mixWord);

                String child2 = mergeStrings(mixWord, merged);
                int newMask = mask & (~bitMask);
                String res1 = go2(child1, newMask, words, 0);
                cache[mask][0] = res1;
                String newRes = res1;
                if (!child1.equals(child2)) {
                    String res2 = go2(child2, newMask, words, 1);
                    cache[mask][1] = res2;
                    newRes = res1.length() <= res2.length() ? res1 : res2;
                }
                // we loose result below, as mixing other words will give different results
                // for the same parent words
                // we can merge only top level results, when
                // ?? can we merge results here and how exactly
                if (res.length() == 0 || newRes.length() < res.length()) {
                    res = newRes;
                    //System.out.println(res);
                }
            }
            idx++;
        }
        return res;
    }

    private void printState(int mask, String merged, String mixWord, String res) {
        print("%16s: (%d)%s + %s = (%d)%s\n", Integer.toBinaryString(mask), merged.length(),
                merged, mixWord, res.length(), res);
    }

    public String mergeStrings(String merged, String other) {
        if (merged.length() >= other.length()) {
            if (merged.contains(other)) {
                return merged;
            }
        } else {
            if (other.contains(merged)) {
                return other;
            }
        }
        // seek from the head.
        //int headMerge = 0;
        int tailMerge = 0;
        String body = merged;
        //String body = merged.length() >= other.length() ? merged : other;
        //String added = body.equals(merged) ? other : merged;
        String added = other;
        for (int merge = 1; merge <= Integer.min(added.length(), merged.length()); merge++) {
        //for (int merge = 1; merge <= added.length(); merge++) {
            int tailAttempt = 0;
            //int headAttempt = 0;
            for (int i = 0; i < merge; i++) {
                // tail merge
                char bodyChar = body.charAt(body.length() - merge + i);
                if (tailAttempt >= 0) { // (LL) mixing guard variable may require a lot of time to get out of it.
                    if (added.charAt(i) != bodyChar) { // (GTB)
                        // this is not the merge
                        tailAttempt = -1; // we stop attemping on this merge length
                    } else {
                        tailAttempt = i + 1; // 0 - means no tail has been merged
                        // i is 0 based
                    }
                }
            }
            tailMerge = Integer.max(tailMerge, tailAttempt);
        }
        return body + added.substring(tailMerge);
    }

    public boolean showOutput = false;
    private void print(String format, Object ... args) {
        if (!showOutput) return;
        System.out.print(String.format(format, args));
    }
}
