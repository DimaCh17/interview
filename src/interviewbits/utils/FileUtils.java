package interviewbits.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class FileUtils {
	public static Path getPath(String fileName) throws IOException {
		return Paths.get(System.getenv("HOME") + File.separator +
				"Documents/workspace/interview/data" + File.separator +
				fileName);
	}
	public static String getRowData(String fileName) throws IOException {
		Path path = getPath(fileName);
		String res = Arrays.toString(Files.readAllBytes(path));
		return res;
	}
}
