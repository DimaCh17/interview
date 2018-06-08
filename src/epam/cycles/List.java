package epam.cycles;

public class List {
	public ListItem head;
	public ListItem add(Integer value) {
		ListItem result = new ListItem();
		result.value = value;
		result.next = head;
		head = result;
		// !PUNT no assigning to the head left list incomplete
		return result;
	}

}
