package epam;

public class List {
	public ListElement head;
	public ListElement add(Integer value) {
		ListElement result = new ListElement();
		result.value = value;
		if (head != null) {
			result.next = head;
		}
		head = result;
		return result;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("List:{");
		int i = 0;
		for (ListElement element = this.head; element != null;
				element = element.next) {
			builder.append(element.value);
			builder.append(",");
			if (i > 10) {
				builder.append("...");
				break;
			}
			i++;
		}
		builder.append("}");
		return builder.toString();
	}
	public static List create(Integer [] values) {
		List list = new List();
		for (Integer value : values) {
			list.add(value);
		}
		return list;
	}
}
