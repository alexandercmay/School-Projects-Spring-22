package edu.ncsu.csc216.issue_manager.model.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.issue_manager.model.issue.Issue;



/**
 * The test file to ensure the proper function of IssueReader
 * @author Alexander May
 *
 */
class IssueReaderTest {

	/** Valid course records */
	private final String validTestFile = "test-files/issue_records1.txt";
	/** Invalid course records */
	private final String invalidTestFile = "test-files/issue_records_invalid1.txt";
	
	/**
	 * Tests reading a valid issue file
	 */
	@Test
	public void testReadValidCourseRecords() {
		try {
			ArrayList<Issue> issues = IssueReader.readIssuesFromFile(validTestFile);
			assertEquals(5, issues.size());

		} catch (FileNotFoundException e) {
			fail("Unexpected error reading " + validTestFile);
		}
	}
	
	/**
	 * Tests reading an invalid issue file
	 */
	@Test
	public void testReadInvalidCourseRecords() {
		ArrayList<Issue> issues;
		try {
			issues = IssueReader.readIssuesFromFile(invalidTestFile);
			assertEquals(0, issues.size());
		} catch (FileNotFoundException e) {
			fail("Unexpected FileNotFoundException");
		}
	}
}
