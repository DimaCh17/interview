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
    		int j = gap, i = 0;
    		for (; j < n; i++, j++) {
    			T[i][j] = 0;
    			for (int g = 0; g < gap; g++) {
    				//int k = i + g;
    				int operIdx = i + g;
    				int nextCol = i + g;
    				int nextRow = i + g + 1;
    				// k is bigger than i;
    				int tik = T[i][nextCol] + F[i][nextCol]; // total count of ways
    				int tkj = T[nextRow][j] + F[nextRow][j];
    				
    				// entry for each step: 
    				// [i][k]
    				// [k+1][j]
    				
    			
    				if (oper[operIdx]=='&') {
    					T[i][j] += T[i][nextCol]*T[nextRow][j];
    					F[i][j] += (tik*tkj - T[i][nextCol]*T[nextRow][j]);
    				}
    				if (oper[operIdx] == '|')
                    {
                        F[i][j] += F[i][nextCol]*F[nextRow][j];
                        T[i][j] += (tik*tkj - F[i][nextCol]*F[nextRow][j]);
                    }
                    if (oper[operIdx] == '^')
                    {
                        T[i][j] += F[i][nextCol]*T[nextRow][j] + T[i][nextCol]*F[nextRow][j];
                        F[i][j] += T[i][nextCol]*T[nextRow][j] + F[i][nextCol]*F[nextRow][j];
                    }
    			}
    		}
    	}
    	return T[0][n-1];
    }
}
