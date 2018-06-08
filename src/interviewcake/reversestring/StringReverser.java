package interviewcake.reversestring;

public class StringReverser {

	public String reverse(String value) {
		  char [] tmp = new char [value.length()] ;
		  for (int i = 0; i < value.length(); i++) {
		    tmp[value.length() - 1 - i] = value.charAt(i);
		  }
		  return new String(tmp);
		}

}
