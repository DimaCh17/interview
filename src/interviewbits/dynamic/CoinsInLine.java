package interviewbits.dynamic;

import java.util.ArrayList;

public class CoinsInLine {
	ArrayList<Integer> input;
	
	public boolean showOutput = false;
    private void print(String format, Object ... args) {
        if (!showOutput) return;
        System.out.print(String.format(format, args));
    }
    
	public int maxcoin(ArrayList<Integer> input) {
		this.input = input;
		return makeChoice(0, input.size() - 1, 0);
    }

	int get(int index) {
		return input.get(index);
	}

	private int makeChoice(int from, int to, int opponent) {
		boolean me = opponent == 0;
		if (to - from == 1) {
			return Math.max(get(from), get(to));
		}

		int nextPlayer = (opponent + 1) % 2;
		if (me) {
			// choice one
			int choiceOne = get(from) + makeChoice(from + 1, to, nextPlayer);
			int choiceTwo = get(to) + makeChoice(from, to - 1, nextPlayer);
			return Math.max(choiceOne, choiceTwo);
		} else {
			int choiceOne = makeChoice(from + 1, to, nextPlayer);
			int choiceTwo = makeChoice(from, to - 1, nextPlayer);
			return Math.min(choiceOne, choiceTwo);
		}
	}
}
