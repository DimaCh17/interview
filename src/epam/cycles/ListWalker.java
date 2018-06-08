package epam.cycles;

public class ListWalker {
	public static boolean hasLoop(List list) {
		if (list == null || list.head == null) {
			return false;
		}
		ListItem slowCursor = list.head;
		ListItem fastCursor = list.head.next;
		boolean advanceBoth = true;
		while (slowCursor != null && fastCursor != null) {
			if (slowCursor == fastCursor) {
				return true;
			}
			if (advanceBoth) {
				slowCursor = slowCursor.next;
			}
			fastCursor = fastCursor.next;
			advanceBoth = !advanceBoth;
		}
		return false;
	}

}
