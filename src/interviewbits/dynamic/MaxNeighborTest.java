package interviewbits.dynamic;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import interviewbits.utils.ArrayUtils;

import org.junit.Before;
import org.junit.Test;

public class MaxNeighborTest {
	MaxNeighbor mn;
	@Before
	public void setUp() throws Exception {
		mn = new MaxNeighbor();
	}

	@Test
	public void test() {
		assertEquals("[[5,8,8],[8,8,8]]",
				ArrayUtils.printMatrix(mn.solve(2, ArrayUtils.createMatrix(
				3,
				1,2,4,
				4,5,8))));
	}
	
	@Test
	public void testk1() {
		assertEquals("[[4,5,8],[5,8,8]]",
				ArrayUtils.printMatrix(mn.solve(1, ArrayUtils.parseMatrix(
					  "[1,2,4],[4,5,8]"))));
	}

	@Test
	public void testLong20() throws Exception {
		assertEquals(normalize(getData("dp_neighbor_expected.txt")),
			ArrayUtils.printMatrix(mn.solve(20,
				ArrayUtils.parseMatrix(
					getData("dp_neighbor.txt")))));
	}

	private String normalize(String input) {
		return ArrayUtils.printMatrix(ArrayUtils.parseMatrix(input));
	}
	private String getData(String fileName) throws IOException {
		Path p = Paths.get(System.getenv("HOME") + File.separator +
				"Documents/workspace/interview/data" + File.separator +
				fileName);
		return Arrays.toString(Files.readAllBytes(p));
	}
}
