package interviewcake.kthnode;

// linked list
// we get a pointer to head and 
// the solutuins would be have two curor
// and the second one skips K node before moves to the second one
public class KthNodeFinder<T> {
	public ListItem<String> findKth2(ListItem<String> head, int k) {
		int skipped = 0;
		ListItem<String> forward = head;
		ListItem<String> result = null;
		while (forward != null) {
			if (skipped < k) {
				skipped++;
			} else if (result == null){
				result = head;
			} else {
				result = result.next;
			}
			// move to the next position
			forward = forward.next;
		}
		return result;
	}
	
	public ListItem<T> find(ListItem<T> head, int k) {
	     ListItem<T> result = null;
	     ListItem<T> forward = head;
	     int skipped = 0;
	     while (forward != null) {
	      if (skipped < k) {
	        skipped++;
	      } else if (result == null) {
	        result = head;
	      } else {
	        result = result.next;
	      }
	      forward = forward.next;
	     }
	     return result;
	  }
}
