package rotatedSearch;

import static org.junit.Assert.*;

import org.junit.Test;

public class RotatedSearchTest {

	@Test
	public void findRegular() {
		RotateSearch search = new RotateSearch();
		int[] data = new int[] {0, 1, 2};
		assertEquals(0, search.findElement(0, data, 0, data.length - 1));
	}
	
	@Test
	public void findRegularEnd() {
		RotateSearch search = new RotateSearch();
		int[] data = new int[] {0, 1, 2};
		assertEquals(2, search.findElement(2, data, 0, data.length - 1));
	}
	
	@Test
	public void findRegularEndNot() {
		RotateSearch search = new RotateSearch();
		int[] data = new int[] {0, 1, 2};
		assertEquals(-1, search.findElement(3, data, 0, data.length - 1));
	}

	@Test
	public void findRotated() {
		RotateSearch search = new RotateSearch();
		int[] data = new int[] {2, 1, 3};
		assertEquals(0, search.findElement(2, data, 0, data.length - 1));
	}
	
	@Test
	public void findRotatedAgain() {
		RotateSearch search = new RotateSearch();
		int[] data = new int[] {5, 6, 1, 2, 3};
		assertEquals(1, search.findElement(6, data, 0, data.length - 1));
	}

	@Test
	public void findDuplicated() {
		RotateSearch search = new RotateSearch();
		int[] data = new int[] {2, 3, 2, 2, 2};
		assertEquals(1, search.findElement(3, data, 0, data.length - 1));
	}

	@Test
	public void findDuplicated2() {
		RotateSearch search = new RotateSearch();
		int[] data = new int[] {1, 3, 1, 1, 1};
		assertEquals(1, search.findElement(3, data, 0, data.length - 1));
	}
	
	@Test
	public void findString () {
		byte [] bytes = new byte[] {1, 3};
		//byte marker = 0x0;
		System.out.println(bytes[0]);
		System.out.println(bytes[0]);
	}

}
