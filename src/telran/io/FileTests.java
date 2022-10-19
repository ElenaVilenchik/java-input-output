package telran.io;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

import org.junit.jupiter.api.*;

class FileTests {
	File file;

	@BeforeEach
	void setUp() {
		file = new File("dir1/dir2");
		file.delete();
	}

	@Test
	void newDirectoryTest() {
		assertFalse(file.exists());
		file.mkdirs();
		assertTrue(file.exists());
	}

	@Test
	void printDirectoryContent() throws Exception {
		printDirectory("C:bin\\telran", 6);
		printDirectory("..\\hello", 2);
		printDirectory("..", 1);
		assertThrows(IllegalArgumentException.class, () -> printDirectory("C:", -1));
		assertThrows(IOException.class, () -> printDirectory("X:", 5));
	}

	/**
	 * 
	 * @param pathName - name of path to initial directory
	 * @param level    - level of sub-directories printing example: 
	 * level=1 printing only first level of the initial directory content      
	 * level=2 content + sub-directories content ... 
	 * level=-1 printing  ??????             
	 *                    
	 * @throws Exception
	 * 
	 */
	private void printDirectory(String pathName, int level) throws Exception {
		/*
		 * <dir> type=dir 
		 * 		<dir> type=dir <file> type=file 
		 * 				<dir> type=file ...
		 */

		Path start = Paths.get(pathName);

		try (Stream<Path> pathStream = Files.walk(start, level)) {
			pathStream.forEach(System.out::println);

		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(String.format("%d level does not exist\n", level));
		} catch (IOException e) {
			throw new IOException(String.format("%s directory does not exist\n", pathName));
		}
	}
	
}
