package interviewbits.dynamic;

import java.util.Deque;
import java.util.LinkedList;

/*
(1) go from left to right -> idx
(2) if it ss <left> then push to stack idx
(3) if it is <right> and stack.size > 0
       res = max (res, idx stack.pop() + 1)
(4) loop (1)
(5) RET res
*/
public class LongestValidParentheses {
	public int longestValidParentheses(String input) {
		int res = 0; // (UEC)
		int start = Integer.MAX_VALUE; //(UEC)
		Deque<Integer> stack = new LinkedList<>();
		for (int idx = 0; idx < input.length(); idx++) {
			char ch = input.charAt(idx);
			if (ch == '(') {
				stack.push(idx);
			} else if (ch == ')') {
				if (stack.size() != 0) {
					int popped = stack.pop();
					res = Integer.max(res, idx - popped + 1);
					start = Integer.min(start, popped);
					res = Integer.max(res, idx - start + 1);
				} else {
					start = Integer.MAX_VALUE;
				}
			}
		}
		return res;
    }
}
