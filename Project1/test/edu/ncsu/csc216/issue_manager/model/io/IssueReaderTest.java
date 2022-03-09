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
		// create a new array of issues using validTestFile
		ArrayList<Issue> issues = IssueReader.readIssuesFromFile(validTestFile);
		// ensure all 5 were added
		assertEquals(5, issues.size());
		// ensure the notes of issue in spot 1 are correct
		assertEquals("-[New] Note 1\n"
				+ "-[Confirmed] Note 2\n"
				+ "that goes on a new line\n", issues.get(1).getNotesString());

	}
	
	/**
	 * Tests reading an invalid issue file
	 */
	@Test
	public void testReadInvalidCourseRecords() throws FileNotFoundException {

		// entire file containing one invalid issue
		assertThrows(IllegalArgumentException.class, () -> IssueReader.readIssuesFromFile(invalidTestFile));
		// issue3.txt - Missing state
		assertThrows(IllegalArgumentException.class, () -> IssueReader.readIssuesFromFile("test-files/issue3.txt"));
		//issue4.txt - Missing type
		assertThrows(IllegalArgumentException.class, () -> IssueReader.readIssuesFromFile("test-files/issue4.txt"));
		//issue5.txt - Incorrect type
		assertThrows(IllegalArgumentException.class, () -> IssueReader.readIssuesFromFile("test-files/issue5.txt"));
		//issue6.txt - Missing summary
		assertThrows(IllegalArgumentException.class, () -> IssueReader.readIssuesFromFile("test-files/issue6.txt"));
		//issue7.txt - File doesn't exist
		assertThrows(IllegalArgumentException.class, () -> IssueReader.readIssuesFromFile("test-files/issue7.txt"));
		//issue8.txt - Issue with resolution in state where there shouldn't be a resolution
		assertThrows(IllegalArgumentException.class, () -> IssueReader.readIssuesFromFile("test-files/issue8.txt"));
		//issue9.txt - Enhancement issue in Confirmed state
		assertThrows(IllegalArgumentException.class, () -> IssueReader.readIssuesFromFile("test-files/issue9.txt"));
		//issue10.txt - Null owner in Working state
		assertThrows(IllegalArgumentException.class, () -> IssueReader.readIssuesFromFile("test-files/issue10.txt"));
		//issue11.txt - Unconfirmed bug in Working state
		assertThrows(IllegalArgumentException.class, () -> IssueReader.readIssuesFromFile("test-files/issue11.txt"));
		//issue12.txt - Null owner in Verifying state
		assertThrows(IllegalArgumentException.class, () -> IssueReader.readIssuesFromFile("test-files/issue12.txt"));
		//issue13.txt - Verifying state with resolution other than Fixed
		assertThrows(IllegalArgumentException.class, () -> IssueReader.readIssuesFromFile("test-files/issue13.txt"));
		//issue14.txt - Closed state with no resolution
		assertThrows(IllegalArgumentException.class, () -> IssueReader.readIssuesFromFile("test-files/issue14.txt"));
		//issue15.txt - Enhancement that is confirmed
		assertThrows(IllegalArgumentException.class, () -> IssueReader.readIssuesFromFile("test-files/issue15.txt"));
		//issue16.txt - Issue with no notes
		assertThrows(IllegalArgumentException.class, () -> IssueReader.readIssuesFromFile("test-files/issue16.txt"));
		//issue17.txt - Enhancement with WorksForMe resolution
		assertThrows(IllegalArgumentException.class, () -> IssueReader.readIssuesFromFile("test-files/issue17.txt"));
		//issue18.txt - negative id
		assertThrows(IllegalArgumentException.class, () -> IssueReader.readIssuesFromFile("test-files/issue18.txt"));
		
	}
}
