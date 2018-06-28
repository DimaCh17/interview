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
        return go2("", mask, words).length();
    }

    private void initCache(int size) {
        cache = new String[size];
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

        return go2("", mask, words);
    }

    private String[] cache;
    private String go2(String merged, int mask, ArrayList<String> words) {

        int idx = 0;
        String res = "";
        if (mask == 0) {
            return merged;
        }
        if (cache[mask] != null) {
            return cache[mask];
        }
        while (idx < words.size()) {
            int bitMask = 1 << idx;
            if ((bitMask & mask) != 0) {
                String mixWord = words.get(idx);
                //print("%s + %s\n", mixWord, merged);
                String childMerged = mergeStrings(merged, mixWord);
                int newMask = mask & (~bitMask);
                String newRes = go2(childMerged, newMask, words);
                if (res.length() == 0 || newRes.length() < res.length()) {
                    res = newRes;
                    //System.out.println(res);
                }
            }
            idx++;
        }
        cache[mask] = res;
        print("%16s: %s\n", Integer.toBinaryString(mask), res);
        return res;
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
        int headMerge = 0;
        int tailMerge = 0;
        String body = merged.length() >= other.length() ? merged : other;
        String added = body.equals(merged) ? other : merged;
        for (int merge = 1; merge <= added.length(); merge++) {
            int tailAttempt = 0;
            int headAttempt = 0;
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
                if (headAttempt >= 0) { // (GTB)
                    if (added.charAt(added.length() - merge + i) != body.charAt(i)) {
                        headAttempt = -1;
                    } else {
                        headAttempt = i + 1;
                    }
                }
            }
            tailMerge = Integer.max(tailMerge, tailAttempt);
            headMerge = Integer.max(headMerge, headAttempt);
        }
        if (headMerge == 0 && tailMerge == 0) {
            return body + added;
        }
        if (headMerge > tailMerge) {
            return added.substring(0, headMerge) + body;
        } else {
            return body + added.substring(tailMerge);
        }
    }

    public boolean showOutput = false;
    private void print(String format, Object ... args) {
        if (!showOutput) return;
        System.out.print(String.format(format, args));
    }
}
