package epam;

// An interview question
// by Sergey Zolotarev
// that he told me about when we were walking in Nov, 2016
/**
 * Find a loop in the list
 * 
 * @author DimaCh76
 *
 */
public class CycleFinder {
	
	public static boolean findLoop(List list) {
		// in this case we use 2 cursors as an option, with one going faster
		// than other. if any of them hit the end, and not the second one
		// then it's not a looped list.
		if (list.head == null) {
			return false;
		}
		ListElement slow = list.head;
		ListElement fast = list.head.next;
		boolean even = false;
		while (slow != null && fast != null) {
			if (slow == fast) {
				//! PUNT (wrong return value, should be true since start)
				return true;
			}
			if (even) {
				slow = slow.next;
			}
			fast = fast.next;
			even = !even;
		}
		return false;
	}

}
