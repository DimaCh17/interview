package euler_pyramid;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


public class PyramidTester {

	Pyramid pyramid;
	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void test1Level() {
		pyramid = new Pyramid(1);
		pyramid.add("1");
		assertEquals(1, pyramid.get(0, 0));
	}

	@Test
	public void test2Levels() {
		pyramid = new Pyramid(2);
		pyramid.add("1");
		pyramid.add("2 3");
		assertEquals(pyramid.get(0, 0), 1);
		assertEquals(pyramid.get(1, 0), 2);
		assertEquals(pyramid.get(1, 1), 3);
	}
	
	@Test
	public void getPos11() {
		pyramid = new Pyramid(2);
		assertEquals(2, pyramid.getDataPos(1, 1));
	}

	@Test
	public void getPos10() {
		pyramid = new Pyramid(2);
		assertEquals(1, pyramid.getDataPos(1, 0));
	}

	/*
	@Test
	public void maxPath() {
		Pyramid p = new Pyramid(4);
		p.add("3")
			.add("7 4")
			.add("2 4 6")
			.add("8 5 9 3");
		assertEquals(Arrays.toString(new int[] {3, 7, 4, 9}),
				Arrays.toString(PyramidPathFinder.findMaxPath(p)));
	}*/
	
	@Test
	public void maxPath2() {
		 Pyramid p = new Pyramid(15);
		 p.add("75")
		.add("95 64")
		.add("17 47 82")
		.add("18 35 87 10")
		.add("20 04 82 47 65")
		.add("19 01 23 75 03 34")
		.add("88 02 77 73 07 63 67")
		.add("99 65 04 28 06 16 70 92")
		.add("41 41 26 56 83 40 80 70 33")
		.add("41 48 72 33 47 32 37 16 94 29")
		.add("53 71 44 65 25 43 91 52 97 51 14")
		.add("70 11 33 28 77 73 17 78 39 68 17 57")
		.add("91 71 52 38 17 14 91 43 58 50 27 29 48")
		.add("63 66 04 68 89 53 67 30 73 16 69 87 40 31")
		.add("04 62 98 27 23 09 70 98 73 93 38 53 60 04 23");
		assertEquals(1074, PyramidPathFinder.getMaxPathValue(p));
	}
	
	@Test
	public void maxPathManualVersion() throws Exception {
		 Pyramid2 p = Pyramid2.build("75\n"+
				 "95 64\n"+
				 "17 47 82\n"+
				"18 35 87 10\n"+
				"20 04 82 47 65\n"+
				"19 01 23 75 03 34\n"+
				"88 02 77 73 07 63 67\n"+
				"99 65 04 28 06 16 70 92\n"+
				"41 41 26 56 83 40 80 70 33\n"+
				"41 48 72 33 47 32 37 16 94 29\n"+
				"53 71 44 65 25 43 91 52 97 51 14\n"+
				"70 11 33 28 77 73 17 78 39 68 17 57\n"+
				"91 71 52 38 17 14 91 43 58 50 27 29 48\n"+
				"63 66 04 68 89 53 67 30 73 16 69 87 40 31\n"+
				"04 62 98 27 23 09 70 98 73 93 38 53 60 04 23");
		 assertEquals(1074, Pyramid2.findMaxPath(p));
	}

	@Test
	public void print() {
		Pyramid p = new Pyramid(4);
		String printed = p.add("3")
			.add("7 4")
			.add("2 4 6")
			.add("8 5 9 3")
			.toString();
		String expected = "0: 3\n" 
				+ "1: 7 4\n"
				+ "2: 2 4 6\n"
				+ "3: 8 5 9 3\n";
		assertEquals(expected, printed);
	}

}
