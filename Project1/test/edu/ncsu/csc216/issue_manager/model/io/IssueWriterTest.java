package edu.ncsu.csc216.issue_manager.model.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.issue_manager.model.issue.Issue;

/**
 * The test file to ensure the proper function of IssueWriter
 * @author Alexander May
 *
 */
class IssueWriterTest {

	/** Valid course records */
	private final String validTestFile = "test-files/issue_records1.txt";
	/** Invalid course records */
	private final String invalidTestFile = "test-files/issue_records_invalid1.txt";
	
	/**
	 * Tests that a file is written correctly
	 */
	@Test
	public void testWrite() throws IOException {
		ArrayList<Issue> issues = IssueReader.readIssuesFromFile(validTestFile);
		IssueWriter.writeIssuesToFile("test-files/testFilename.txt", issues);
		checkFiles("test-files/issue_records1.txt", "testFilename.txt");
	}

	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new File(expFile));
			 Scanner actScanner = new Scanner(new File(actFile));) {
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
}
