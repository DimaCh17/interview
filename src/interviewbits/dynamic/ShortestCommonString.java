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
	
    
	  public int solve(ArrayList<String> listStr) {
	        if (listStr == null) return 0;
	        int n = listStr.size();
	        if (n == 0) return 0;
	        String[] arrStr = listStr.toArray(new String[n]);
	        
	        int len = n;
	        while (len > 1) {
	            int overlapMax = 0;
	            int I = 0, J = 0;
	            String resStr = "";
	            for (int i = 0; i < len; i++) {
	                for (int j = i + 1; j < len; j++) {
	                    String curStr = findOverlap(arrStr[i], arrStr[j]);
	                    int overlapCur = - curStr.length() + arrStr[i].length() + arrStr[j].length();
	                    //System.out.println(i + " " + j + " " + curStr);
	                    if (overlapMax < overlapCur) {
	                        overlapMax = overlapCur;
	                        resStr = curStr;
	                        I = i;
	                        J = j;
	                    }
	                }
	            }
	            --len;
	            if (overlapMax == 0) {
	                arrStr[0] += arrStr[len];
	            } else {
	                arrStr[I] = resStr;
	                arrStr[J] = arrStr[len];
	            }
	        }
	        return arrStr[0].length();
	    }
	  
	    private String findOverlap(String a, String b) {
	        int la = a.length();
	        int lb = b.length();
	        String res = a + b;
	        for (int k = 1; k <= la; k++) {
	            if (k > lb) break;
	            if (b.endsWith(a.substring(0, k))) {
	                res = b + a.substring(k);
	            }
	        }
	        for (int k = 1; k <= lb; k++) {
	            if (k > la) break;
	            if (a.endsWith(b.substring(0, k))) {
	                String tmpRes = a + b.substring(k);
	                if (res.length() > tmpRes.length())
	                    res = tmpRes;
	            }
	        }
	        return res;
	    }
	}

