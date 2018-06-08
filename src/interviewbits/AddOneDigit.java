package interviewbits;

//Given a non-negative number represented as an array of digits,
//add 1 to the number ( increment the number represented by the digits ).

//[1, 2, 3]
//result [1, 2, 4]
//? list of array?
//it can be 0, as it is non negative
//https://www.interviewbit.com/courses/programming/topics/arrays/


public class AddOneDigit {
	private class Element {
		public int value;
		public Element next;
	}

	public static Element build(int[] data) {
		Element head = null;
		Element previous = null;
		for (int i = data.length - 1; i>= 0; i--) {
			//AddOneDigit.Element element = new Element();
			//element.next = previous;
			//element.value = data[i];
			//previous = element;
			
		}
		return head;
	}
	
	public int[] plusOne (int[] number) {
		  int carryOver = 1;

		  for (int i = number.length - 1; i>=0; i--) {
		    int sum = number[i] + carryOver;
		    number[i] = sum  % 10;
		    carryOver = sum / 10;
		    if (carryOver == 0) return number;
		  }

		  int[] result = new int[number.length + 1];
		  result[0] = carryOver;
		  return result;
		}

	public int[] plusOneV3 (int[] number) {// PUNT wrong type of return result
		  int carryOver = 1;

		  for (int i = number.length -1 ; i>=0; i--) {
		    int sum = number[i] + carryOver;
		    number[i] = sum % 10;
		    carryOver = sum / 10;
		    if (carryOver == 0) return number;
		  }

		  int[] result = new int[number.length + 1];
		  result[0] = carryOver;
		  return result;
		}

	public int[] plusOneV2(int[] number) {

		  int carryOver =  1; //!! init

		  for (int i = number.length - 1; i >= 0; i--) {
		    int sum = number[i] + carryOver;
		    number[i] = sum % 10; // assign it
		    carryOver = sum / 10;
		    if (carryOver == 0) {
		    	return number;
		    }
		  }

		  // in this case the carryover is not 0
		  int[] result = new int[number.length + 1];
		  result[0] = carryOver;
		  return result;
		}

	
	public int[] plusOnev1(int[] number) {
		  // we may return one more element than originally was passed
		  // indexes are started from the major digit
		  int carryOver =  1; // !! need to init
		  for (int i=number.length - 1; i >= 0; i--) {
		      int current = (number[i] + carryOver) % 10;
		      carryOver = (number[i] + carryOver) / 10;
		      number[i] = current;
		      if (carryOver == 0) {
		        return number; // once there is no carryover we may return results
		      }
		  }
		  int[] result = new int[number.length + 1];
		  // !PUNT used the wrong index for 1
		  result[0] = carryOver;
		  return result;
		}

	// assume that it's an array that can be accesses by index
	int[] addOneArrays(int[] number) {
		// ! understand
		// ! what results we want to achieve
		// ! when we stop/achieve results
		// ! how we are going to start
		// ! what we do at every step
		// ! how we move to the next step
		// ! how we are going to start 
		
		
		// ! PUNT. the index for the array is displayed from the left
		// index 0 is the leftmost
		
		// TODO(dc) : change the index as we go from right to left
		int carryOver = 1;// ?! almost set it to 0
		for (int i = number.length - 1; i >=0 && carryOver > 0; i--) {
			// use first
			int newValue = (number[i] + carryOver) % 10; //! assign
			
			carryOver = (number[i] + carryOver) / 10;
			number[i] = newValue;
		}		
		if (carryOver > 0) {
			int[] result = new int[number.length + 1];
			for (int i = number.length-1; i >= 0; i--) {
				result[i + 1] = number[i];
			}
			result[0] = carryOver;
			return result;
		}// !! make bits operations like masks
		/// !! for, reset bits
		return number;
	}
}


// TC:
// 1, 2 ,3
// 0
// empty
// 9 9 9
// 10B digits - what is the 
// space constraints?

// with list implementetion you may get back if we use in place.
