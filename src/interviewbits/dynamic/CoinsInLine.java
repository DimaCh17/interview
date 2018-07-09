package interviewbits.dynamic;

import java.util.ArrayList;

public class CoinsInLine {
	ArrayList<Integer> input;
	
	public boolean showOutput = false;
    private void print(String format, Object ... args) {
        if (!showOutput) return;
        System.out.print(String.format(format, args));
    }
    private Integer[][][] cache;
	public int maxcoin(ArrayList<Integer> input) {
		this.input = input;
		cache = new Integer[input.size()][input.size()][2];
		return makeChoice(0, input.size() - 1, 0);
    }

	int get(int index) {
		return input.get(index);
	}

	// this one is cacheable, no need to make the same decision again and again.
	private int makeChoice(int from, int to, int opp) {
		if (from > to) {
			return 0; // (TERM1)
		}
		if (cache[from][to][opp] != null) {
			return cache[from][to][opp];
		}
		boolean me = opp == 0;
		int nextPlayer = (opp + 1) % 2;
		int res;
		if (me) {
			// choice one
			int choiceOne = get(from) + makeChoice(from + 1, to, nextPlayer);
			int choiceTwo = get(to) + makeChoice(from, to - 1, nextPlayer);
			res = Math.max(choiceOne, choiceTwo);
		} else {
			int choiceOne = makeChoice(from + 1, to, nextPlayer);
			int choiceTwo = makeChoice(from, to - 1, nextPlayer);
			res = Math.min(choiceOne, choiceTwo);
		}
		cache[from][to][opp] = res;
		return res;
	}
}
