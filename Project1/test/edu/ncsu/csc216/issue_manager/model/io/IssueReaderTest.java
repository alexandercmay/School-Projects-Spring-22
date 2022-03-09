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
	public void testReadValidCourseRecords() throws FileNotFoundException {
		ArrayList<Issue> issues = IssueReader.readIssuesFromFile(validTestFile);
		assertEquals(5, issues.size());
		assertEquals("-[New] Note 1\n"
				+ "-[Confirmed] Note 2\n"
				+ "that goes on a new line\n", issues.get(1).getNotesString());

	}
	
	/**
	 * Tests reading an invalid issue file
	 */
	@Test
	public void testReadInvalidCourseRecords() throws FileNotFoundException {
		assertThrows(IllegalArgumentException.class, () -> IssueReader.readIssuesFromFile(invalidTestFile));
	}
}
