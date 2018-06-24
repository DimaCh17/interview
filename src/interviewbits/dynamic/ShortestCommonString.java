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
        Map<Character, Integer> minSet = new HashMap <>(); // (UEC)
        TreeMap<Character, Integer> completeSet = new TreeMap <>(); // (UEC)+
        for (String word : words) {
            Map<Character, Integer> wordMap = new HashMap <>(); // (UEC)+
            for (char ch : word.toCharArray()) {
                wordMap.compute(ch, this::increaseMapCount);
                completeSet.compute(ch, this::increaseMapCount); // Assuming null can be a previous value
            }
            wordMap.forEach((k, v) -> minSet.put(k, Math.max(minSet.getOrDefault(k, 0), v)));
        }
         // we remove items from the complete set, to keep only items are not processed
        for (Character key : minSet.keySet()) {
            int left = completeSet.get(key) - minSet.get(key);
            if (left == 0) {
                completeSet.remove(key);
            } else {
                completeSet.put(key, left);
            }
        }
        // (MAN) check prime factorizarion
        List<Character> state = new LinkedList <>();
        minSet.forEach((k, v) -> {
            for (int i = 0; i < v; i++) {
                state.add(k);
            }
        });
        for (;;) {
            state.sort(Character::compareTo);
            StringBuilder sb = new StringBuilder();
            state.forEach(c -> sb.append(c));
            String snapshot = sb.toString();
            Deque<String> dq = new LinkedList<>();
            dq.addLast(snapshot);
            Deque<Integer> mutateTank = new LinkedList<>();
            mutateTank.addLast(0);
            while (!(dq.isEmpty())) { // check permutation here
                String cur = dq.removeFirst();
                int mutation = mutateTank.removeFirst();
                if (mutation < cur.length()) {
                    print("%s\n", cur);
                    if (found(cur, words)) {
                        return cur.length(); // (TERM1)
                    }
                    // we are still within the string
                    // process the current entry
                    // basically, we (1) do (2) mutate
                    for (int index = mutation; index < cur.length(); index++) {
                        StringBuilder mutantBuilder = new StringBuilder(cur);
                        char subject = mutantBuilder.charAt(index);
                        mutantBuilder.deleteCharAt(index);
                        mutantBuilder.insert(mutation, subject);
                        dq.addLast(mutantBuilder.toString());
                        mutateTank.addLast(mutation + 1);
                    }
                }
            }
            char firstChar = completeSet.firstKey();
            int newCharCount = completeSet.firstEntry().getValue() - 1;
            if (newCharCount == 0) {
                completeSet.pollFirstEntry();
            } else {
                completeSet.put(firstChar, newCharCount);
            }
            state.add(firstChar);
        }
        //throw new IllegalStateException("This state should not be entered");
    }

    private boolean found(String cur, ArrayList<String> words) {
        for (String word : words) {
            if (!cur.contains(word)) {
                return false;
            }
        }
        return true;
    }

    private int increaseMapCount(Character key, Integer count) {
        return count != null ? count + 1 : 1;
    }

    public boolean showOutput = false;
    private void print(String format, Object ... args) {
        if (!showOutput) return;
        System.out.print(String.format(format, args));
    }
}
