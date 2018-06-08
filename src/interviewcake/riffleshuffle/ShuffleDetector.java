package interviewcake.riffleshuffle;
import java.util.List;

public class ShuffleDetector {
	
	// make a function that returns true
	// if this deck has been riffled once
	// and false otherwise
	private boolean checkSize(List<Integer> shuffledDeck) {
		return shuffledDeck != null && shuffledDeck.size() == 52;
	}
	
	public boolean isRiffleShuffled(List<Integer> deck) {
			
		  if (deck == null || deck.size() == 0) {
			  return false;
		  }
		  int [] halves = new int[2]; // we use ins to handle it.
		  // each of them has 0 (which is not the correct one)
		  for (int i = 0; i < deck.size(); i++) {
		    int checkSum = halves[0] + halves[1];
		    if (checkSum == 0) {
		    	halves[0] = deck.get(i);
		    	continue;
		    }
		    // !PUNT the start sequence seems off.
		    for (int j = 0; j < halves.length; j++) {
		      if (deck.get(i) == halves[j] + 1) {
		        halves[j] = halves[j] + 1; // it does not trigger erro
		        break;
		      }
		    }
		    if (halves[0] + halves[1] == checkSum) {
		      return false;
		    }
		  }
		  return true;
		}

	public boolean isRiffleShuffled2(List<Integer> shuffledDeck) {
		if (!checkSize(shuffledDeck)) {
			return false;
		}

		int[] halves = new int[2];
		for (int i = 0; i< shuffledDeck.size(); i++) {
			int current = shuffledDeck.get(i); // PUNT it was index 0, not i
			boolean found = false;
			for (int j = 0; j < halves.length; j++) {
				if (current == halves[j] + 1) {
					halves[j] = current;
					found = true;
					break;
				}
			}
			if (!found) {
				return false;
			}
		}
		return true;
	}

}



/// the second idea is to make passes from two opposite sides.
// we also may not store ites

// the special cases are:
// when it starts, and both queue are empty
// also it is true when one halsf is still empty.
