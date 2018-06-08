package epam.bracket;
import java.util.HashMap;
import java.util.Map;

public class BracketMatcher {


boolean match(String range) {
  if (range.length() % 2 != 0) {
    return false;
  }

  Map<Character, Character> pairs = new HashMap<Character, Character> ();
  pairs.put('{', '}');
  pairs.put('[', ']');
  pairs.put('(', ')');
  
  // here we iterate and check that the item on the right
  // matches the one on the left.
  for (int i = 0; i < range.length() / 2; i++) {
    Character left = range.charAt(i);
    if (!pairs.containsKey(left)) {
      return false;
    }
    Character right = range.charAt(range.length() - 1 - i);
    if (pairs.get(left) != right) {
      return false;
    }
  }
  return true;
}
	
	public boolean match2(String string) {
		if (string.length() % 2 == 1) {// String has
			// method length(). Also remember that the
			// int is not boolean
			return false;
		}
		// we need to use boxed types here.
		// brackes are supported in Java 1.7 or aove
		Map<Character, Character> pairs = new HashMap<Character, Character> ();
		pairs.put('{', '}');
		pairs.put('(', ')');
		pairs.put('[', ']');
		for (int i = 0; i < string.length() / 2; i++) {
			// we iterate using the middle point and sledct itemts
			// on the left and right.
			// if any of those don't match each other then it's no match
			if (!pairs.containsKey(string.charAt(i))){
				return false;
			}
			if (pairs.get(string.charAt(i)) != string.charAt(string.length() - 1 - i)) {
				return false;
			}
		}
		// TODO Auto-generated method stub
		return true;
	}

}
