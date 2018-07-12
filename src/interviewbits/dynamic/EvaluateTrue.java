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
    				int k = i + g;
    				int tik = T[i][k] + F[i][k]; // total count of ways
    				int tkj = T[k+1][j] + F[k+1][j];
    				
    				if (oper[k]=='&') {
    					T[i][j] += T[i][k]*T[k+1][j];
    					F[i][j] += (tik*tkj - T[i][k]*T[k+1][j]);
    				}
    				if (oper[k] == '|')
                    {
                        F[i][j] += F[i][k]*F[k+1][j];
                        T[i][j] += (tik*tkj - F[i][k]*F[k+1][j]);
                    }
                    if (oper[k] == '^')
                    {
                        T[i][j] += F[i][k]*T[k+1][j] + T[i][k]*F[k+1][j];
                        F[i][j] += T[i][k]*T[k+1][j] + F[i][k]*F[k+1][j];
                    }
    			}
    		}
    	}
    	return T[0][n-1];
    }
}
