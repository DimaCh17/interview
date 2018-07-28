package interviewbits.dynamic;

public class EvaluateTrue {
    public int cnttrue(String input) {
    	char[] symb = new char[(input.length() + 1) / 2];
    	char[] oper = new char[(input.length()) / 2];
    	for (int i = 0; i < input.length(); i++) {
    		char ch = input.charAt(i);
    		if ((i & 1) == 0) { 
    			symb[i/2] = ch;
    		} else {
    			oper[i/2] = ch;
    		}
    	}
    	int res = count(symb, oper);
    	return res % 1003;
    }

    int count(char[] symb, char[] oper) {
    	int n = symb.length;
    	int[][] T = new int[n][n];
    	int[][] F = new int[n][n];
    	
    	for (int i = 0; i < n; i++) {
    		T[i][i] = symb[i] == 'T' ? 1 : 0;
    		F[i][i] = symb[i] == 'F' ? 1 : 0;
    	}
    	
    	for (int gap = 1; gap < n; gap++) {
    		int col = gap;// i = 0;
    		int row = 0;
    		for (; col < n; row++, col++) {
    			T[row][col] = 0;
    			for (int g = 0; g < gap; g++) {// find where the extra cycle can be eliminated
    				// and understand better what it actually dowsn 
    				//int k = i + g;
    				int operIdx = row + g;
    				int colRight = row + g;
    				int rowBelow = row + g + 1;
    				// k is bigger than i;
    				int totalRight = (T[row][colRight] + F[row][colRight]) % 1003; // total count of ways
    				int totalBelow = (T[rowBelow][col] + F[rowBelow][col]) % 1003;
    				char func = oper[operIdx];
    				if (func == '&') {
    					T[row][col] += T[row][colRight] * T[rowBelow][col];
    					
    					F[row][col] += (totalRight * totalBelow
    							- T[row][colRight] * T[rowBelow][col]);
    				}
    				
    				if (func == '|') {
                        F[row][col] += F[row][colRight] * F[rowBelow][col];
                        T[row][col] += (totalRight * totalBelow
                        		- F[row][colRight] * F[rowBelow][col]);
                    }
                    if (func == '^') {
                        T[row][col] += F[row][colRight] * T[rowBelow][col]
                        	+ T[row][colRight] * F[rowBelow][col];
                        F[row][col] += T[row][colRight] * T[rowBelow][col]
                        	+ F[row][colRight] * F[rowBelow][col];
                    }
                    T[row][col] %= 1003;
                    F[row][col] %= 1003;
    			}
    		}
    	}
    	return T[0][n-1] % 1003;
    }
}
