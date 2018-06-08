package interviewcake;

public class Fibbonacci {
	
	
	int fib(int n) {
		if (n<2) return n;
		int idx = 0x1;
		int buf[] = new int[] {0 , 1};
		for(int i=2; i<=n; i++) {
		  idx^=0x1;
		  buf[idx] = buf[0] + buf[1];
		}
		return buf[idx];
		}

}
