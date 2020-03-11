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
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;

public class TPO1FileVisitor extends SimpleFileVisitor<Path> {

    
	private static FileChannel outputFileChannel;
	private static ByteBuffer buffer;
	private static Charset inputCharset  = Charset.forName("Cp1250");
	private static Charset outputCharset = Charset.forName("UTF-8");

	
	public TPO1FileVisitor(Path outputFile) { 
		try {
			outputFileChannel = FileChannel.open(outputFile, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	@Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
		if (attr.isSymbolicLink()) {
            System.out.format("Symbolic link: %s ", file);
        } else if (attr.isRegularFile()) {
            System.out.format("Regular file: %s ", file);
        } else {
            System.out.format("Other: %s ", file);
        }
		try {
			codeToUtf(FileChannel.open(file), attr.size());
		} catch (IOException e) {
			e.printStackTrace();
		}      
        return FileVisitResult.CONTINUE;
    }
	
	private static void codeToUtf (FileChannel inputChannel, long bufferSize) {
		buffer = ByteBuffer.allocate((int) (bufferSize));
		buffer.clear();
		try {
			inputChannel.read(buffer);
			buffer.flip();
			
			CharBuffer charBuffer = inputCharset.decode(buffer);
			ByteBuffer convertedBuffer =
					outputCharset.encode(charBuffer);
			while(convertedBuffer.hasRemaining()) {
				outputFileChannel.write(convertedBuffer);
			}
			
		} catch (IOException e) {
	        System.out.println(e.toString());
			e.printStackTrace();
		}
	}
}
