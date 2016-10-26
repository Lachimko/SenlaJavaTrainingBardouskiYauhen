package com.danco.training;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

/**
 * Test class for learning. It performs R/W operations for text file. 
 * 
 * @author Igor Danilov
 *
 */
public final class TextFileWorker {

	private final static Charset FILE_ENCODING = StandardCharsets.UTF_8;
	private final static String FILE_NOT_FOUND_ERROR = "File not found";
	
	// Path to file
	private final Path path;
	
	/**
	 * Constructor of TextFileWorker. Check file path and initialize class instance.
	 * 
	 * @param filePath
	 */
	public TextFileWorker(final String filePath) {
		
		if (filePath == null || filePath.isEmpty()){
			throw new IllegalArgumentException(FILE_NOT_FOUND_ERROR);
		}
			
		this.path = Paths.get(filePath);
		
		if ( ! Files.exists(path)){
			throw new IllegalArgumentException(FILE_NOT_FOUND_ERROR);
		}
	}

	/**
	 * Read all lines from a file and return String array. This method ensures that the 
	 * file is closed when all bytes have been read.
	 * 
	 * @param aFileName
	 * @return
	 * @throws IOException
	 */
	public String[] readFromFile() {
		
		try {
			
			List<String> lines = Files.readAllLines(path, FILE_ENCODING);
			
			if (lines != null){
				return lines.toArray(new String[lines.size()]);
			}else{
				return new String [0];
			}
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Write lines of text to a file. Each line is a char sequence and is written to 
	 * the file in sequence with each line terminated by the platform's line separator, 
	 * as defined by the system property line.separator.
	 * 
	 * @param values
	 */
	public void writeToFile(final String[] values) {
		
		if (values != null && values.length > 0){
			List<String> lines = Arrays.asList(values);
			try {
				Files.write(path, lines, FILE_ENCODING, StandardOpenOption.TRUNCATE_EXISTING);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	/**
	 * Main method -  use example
	 * 
	 * @param s
	 */
//	public static void main(String s[]) throws Exception{
//		
//		// test data
//		final String TEST_FILE = "d:/temp_file_for_test_program.txt";
//		final String[] testValues = new String[]{"Text line 1","Text line 2"};
//		
//		// Create new file
//		Path filePath = Paths.get(TEST_FILE);
//		Files.createFile(filePath);
//		
//		// Work example
//		try{
//			TextFileWorker fileWorker = new TextFileWorker(TEST_FILE);
//			fileWorker.writeToFile(testValues);
//			Object[] readedValues = fileWorker.readFromFile();
//			
//			// Check result
//			for (int i = 0; i < testValues.length; i++ ){
//				
//				if ( ! readedValues[i].equals(testValues[i])){
//					throw new RuntimeException("Error. Not equal values: " + readedValues[i] + " and " + testValues[i]);
//				}
//			}
//		}finally{
//			Files.deleteIfExists(filePath);
//		}
//	}
}
