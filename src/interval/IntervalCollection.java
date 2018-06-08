package interval;

import java.util.ArrayList;

public class IntervalCollection {
	// we keep merged intervals only
	ArrayList<Interval> intervals = new ArrayList<Interval>();
	private class Interval {
		public Interval(int left, int right) {
			this.left = left;
			this.right = right;
		}
		public int left;
		public int right;

		/*
		public boolean contains(int point) {
			return left <= point && right >= point;
		}*/
	};

	public int size() {
		return intervals.size();
	}

	public int findLowerLessThan(int left) {
		// replace by binary search here.
		for (int i = intervals.size() - 1; i >=0 ; i--) {
			Interval current = intervals.get(i);
			if (current.left > left) {
				return i;
			}
		}
		return -1;
	}

	public int findUpperGreaterThan(int right, int startAt) {
		// replace by binary search here.
		int actualStartAt = startAt == -1 ? 0 : startAt;
		for (int i = actualStartAt; i < intervals.size(); i++) {
			Interval current = intervals.get(i);
			if (current.right > right) {
				return i;
			}
		}
		return -1;
	}


	public void add(int left, int right) {
		if (left == right) {
			return;
		}
		Interval interval = new Interval(left, right);
		if (intervals.size() == 0) {
			insert(0, interval);
			return;
		}
		
		int mergeLeft = findLowerLessThan(left);
		int mergeRight = findUpperGreaterThan(right, mergeLeft);
		final Interval foundLeft = mergeLeft != -1 ? intervals.get(mergeLeft): null;
		final Interval foundRight = mergeRight != -1 ? intervals.get(mergeRight): null;
		//Interval mergeOnLeft = foundLeft;
		//Interval mergeOnRight = foundRight;
		int newLeft = left;
		int newRight = right;
		if (mergeLeft != -1 && foundLeft.right >= left) { // inclusive
			newLeft = foundLeft.left;
		}
		if (mergeRight != -1 && foundRight.left <= right) { // inclusive
			newRight = foundRight.right;
		}
		if (mergeRight != -1 && mergeLeft!= -1) {
			for (int i = mergeRight; i >= mergeLeft; i--) {
				intervals.remove(i);
			}
			insert(mergeLeft, new Interval(newLeft, newRight));
		} else {
			insert(0, new Interval(newLeft, newRight));
		}
		//for (int i= intervals.size() - 1; i>=0)
	}

	private void insert(int posOfLeft ,Interval interval) {
		intervals.add(posOfLeft, interval);
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < intervals.size() ; i++) {
			Interval interval = intervals.get(i);
			builder.append(i + ":" + interval.left + "-" + interval.right + "\n");
		}
		return builder.toString();
	}
}
