package interviewbits.dynamic;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;;

public class NDigits {
    // n - digits, s - sum
    public int solve(int n, int s) {
    	return solveRecursively(n, s);
    }
    
    public boolean showOutput = false;
    private void print(String format, Object ... args) {
        if (!showOutput) return;
        System.out.print(String.format(format, args));
    }
    private class QueueEntry {
    	public final int count;
    	public final int digits;
    	public QueueEntry(int count, int digits) {
    		this.count = count;
    		this.digits = digits;
    	}
    }
    public int solveRecursively(int digits, int sum) {
        // use dp, we know how many digits we have left. we can keep the sorted structure that
        // keeps the order of all current sums and their count.
        // once we have only k digits left and k * 9 + a[i] < s, this will not work, and we can
        // remove [i] from the consideration
        // we also need not track how exactly we got to a certain count, just the count.
    	//TreeMap<Integer, Integer> countMap = new TreeMap<Integer, Integer>();
    	if (digits == 0) { // (CC1) (CC2)
    		if (sum == 0) {
    			return 1;
    		} else {
    			return 0;
    		}
    	}
    	Deque<QueueEntry> countQueue = new LinkedList<>();
    	// start pos 0
    	for (int digit = 1; digit <= 9; digit++) {
    		if (digit <= sum) {
    			countQueue.addLast(new QueueEntry(digit, 1)); // (INIT)
    		}
    	}
    	int foundWays = 0;
        while (!countQueue.isEmpty()) {
        	if (countQueue.size() > 1000) {
        		throw new RuntimeException("Something goes wrong");
        	}
        	QueueEntry entry = countQueue.pollFirst();
    		if (entry.digits > digits) {
    			continue;
    		}
    		if (entry.count == sum) {
	    		if (entry.count == sum) {
	    			foundWays = (foundWays + 1) % 1000000007;
	    			
	    		}
    		} else if (entry.count < sum) {
	        	for (int i = 0; i <= 9; i++) {
	        		countQueue.addLast(new QueueEntry(entry.count + i, entry.digits + 1));
	        	}
    		}
        }
        return foundWays;
    }
}