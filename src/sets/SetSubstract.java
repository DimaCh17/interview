package sets;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class SetSubstract {
//http://www.glassdoor.com/Interview/Implement-a-function-that-takes-the-set-wise-subtraction-of-two-sorted-sets-of-integers-ie-A-1-2-3-B-3-4-5-and-QTN_877613.htm 
	/**  Implement a function that takes the set-wise subtraction of  
	two sorted sets of integers. ie A = {1, 2, 3}, B = {3, 4, 5} => A - B = {1, 2}. 
	There can be duplicates, in which case all duplicates should be removed should
	there be an occurrence in B. IE: {1, 2, 3, 3, 3} - {2, 3} = {1}. 		
	// what data type is there, do we know.
	// what is a specific purpose of this collection
	// do we know a business scenario */
	public Set<Integer> substract(TreeSet<Integer> set1, TreeSet<Integer> set2) {
		// we use running pointers that show what elements.
		Iterator<Integer> left = set1.iterator();
		Iterator<Integer> right = set2.iterator();
		// if no elements on right return left.
		//Integer valueOnRight = 
		while (left.hasNext() && right.hasNext()) {
			Integer currentOnRight = right.next();
			Integer currentOnLeft = left.next();
			if (currentOnLeft < currentOnRight) {
				continue;
			} else if (currentOnLeft == currentOnRight) {
				// need to remove the element
				left.remove();
			} else {
				if (right.hasNext()) {
					currentOnRight = right.next();
				}
			}
		}
		return set1;
	}
}
