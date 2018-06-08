package random;


public class Generator {
	private long nextNum = 0;
	 public synchronized long getNextNum() {
	   return nextNum++;
	 }
	 public static void main(String[] args) {
		Generator generator = new Generator();
		long last = generator.getNextNum();
		last = generator.getNextNum();
		long prev = 0;
		while (last != 0) {
			prev = last;
			last = generator.getNextNum();
		}
		System.out.println("Previous" + prev);
	 }
}
