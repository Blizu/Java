package zad1;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class Futil   {

	public static void processDir(String directory, String resultFile) {
		
		Path inputDirectory = Paths.get(directory);
		Path outputFile = Paths.get(resultFile);

		TPO1FileVisitor TPO1FileVisitor = new TPO1FileVisitor(outputFile);
				
		try {
			Files.walkFileTree(inputDirectory, TPO1FileVisitor);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
			   	
}
