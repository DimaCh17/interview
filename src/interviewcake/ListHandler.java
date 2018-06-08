package interviewcake;

public class ListHandler {
	  public int getKthElement(Element head, int k) {
	    Element kth = head;
	    Element current = head;
	    int index = 0;
	    // PUNT did not initialize the index variable

	    while (current != null) {
	      if (index > k) { // ?
	        kth = kth.next;
	      }
	      index++;
	      current = current.next;
	    }
	    return kth.value;
	  }
	}

//	                | 0 | 1 |
	// k = 1, size = 2|