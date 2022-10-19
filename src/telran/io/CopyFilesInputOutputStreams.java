package telran.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Application for copying files based on FileInputStream and FileOutputStream
 * files may be very big (several Gigs)
 * args[0] - source file path
 * args[1] - destination file path
 * args[2] - if exists "overwritten" then destination may be overwritten otherwise may not be
 * 
 * Outout one of the following:
 * Files have been copied (<amount of bytes>  <time of copieng>)
 * Source file doesn't exist
 * Destination can not be overwritten
 *
 */
public class CopyFilesInputOutputStreams {


	private static final int BUFFER_SIZE = 1024;

	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("Please provide input and output files");
			System.exit(0);
		}

		String inputFile = args[0];
		String outputFile = args[1];

		try (InputStream inputStream = new FileInputStream(inputFile);
				OutputStream outputStream = new FileOutputStream(outputFile);) {

			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;

			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
}

