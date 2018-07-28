package interviewbits.dynamic;

import java.util.Deque;
import java.util.LinkedList;

/*
((1) go from left to right -> idx
(2) if it ss <left> then push to stack idx
(3) if it is <right> and stack.size > 0
       res = max (res, idx stack.pop() + 1)
(4) loop (1)
(5) RET res


A : ")()))(())((())))))())()(((((())())((()())(())((((())))())((()()))(()(((()()(()((()()))(())()))((("
     )())) (())((())))))()) ()((( ((())()) ( (()()) (()) ((((())))()) ((()())) ( ()((( ()() (() ((()())) (()) ())) (((

Ac: 24
Ex: 30


A2. Track the tail of the sequence.
Each time when the bracket closes, it may have a tail of previously closed sequences
the tail only adds when the bracket is closes, thus it moves to the tail, like
in the python game. The tail belongs to the opening brace. it may be multiple tails
in case like:
[[[][[][]]][
the last bracket eliminates continuation case

[[[][[][]]][

the same case but the stack is never empty except case 0.

(1) tail init. when the bracket is closed, it's length added to the tail.
  this tail doesn't belong to any bracket.
(2) <OPEN>. goes on stack. if the bracket closes, then it should attach to the tail
(3) the new tail may be 0 if the previous bracket was not closed
[[][][

(STATES):
(INIT)
    tail = 0
    max_len = 0
(OPEN):
    add index to stack
    stack.push(idx)
    tails[idx] = tail
    tail = 0
(CLOSE):
    if not stack:
       tail = 0 // we start from the scratch. <INIT>
    else:
       head_start = stack.pop()
       head_len = idx - head_start + 1
       left_tail = tails[head_start]
       tail = bracket.len + left_tail
       update max_len

use tail index, just another array, thus for each opening bracket we can find it's
tail

*/
public class LongestValidParentheses {

	public boolean showOutput = false;
    private void print(String format, Object ... args) {
        if (!showOutput) return;
        System.out.print(String.format(format, args));
    }
	public int longestValidParentheses(String input) {
		int res = 0; // (UEC)
		Deque<Integer> stack = new LinkedList<>();
		int[] tails = new int[input.length()];
		int tail = 0;
		for (int idx = 0; idx < input.length(); idx++) {
			char ch = input.charAt(idx);
			if (ch == '(') { // (OPEN)
				stack.push(idx);
				tails[idx] = tail;
				tail = 0;
			} else if (ch == ')') {
				if (!stack.isEmpty()) {
					int head_start = stack.pop();
					int head_len = idx - head_start + 1;
					int left_tail = tails[head_start];
					tail = head_len + left_tail;
					res = updateRes(res, tail, "t", input, head_start, idx);
				} else {
					tail = 0; // (INIT)
				}
			}
		}
		return res;
    }
	
	private int counter = 0;
	private int updateRes(int res, int candidate, String marker, String input, int start, int end) {
		if (candidate > res) {
			print("%2d:%s %2d -> %2d = %s\n", counter++, marker, res, candidate,
				input.substring(start, end + 1));
			return candidate;
		}
		return res;
	}
}
