package edu.ncsu.csc216.issue_manager.model.manager;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.issue_manager.model.command.Command;
import edu.ncsu.csc216.issue_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.issue_manager.model.command.Command.Resolution;
import edu.ncsu.csc216.issue_manager.model.io.IssueReader;
import edu.ncsu.csc216.issue_manager.model.issue.Issue;

/**
 * The test file to ensure the proper function of IssueManager
 * @author Alexander May
 *
 */
class IssueManagerTest {
	
	/** IssueManager **/
	private IssueManager manager;
	/** Valid course records */
	private final String validTestFile = "test-files/issue_records1.txt";
	
//	/**
//	 * Sets up the IssueManager and clears the data.
//	 * @throws Exception if error
//	 */
//	@BeforeEach
//	public void setUp() throws Exception {
//		manager = IssueManager.getInstance();
//	}
	
	/**
	 * Test the getIssueListAsArray
	 */
	@Test
	public void testGetIssueListAsArray() {
		manager = IssueManager.getInstance();
		manager.loadIssuesFromFile(validTestFile);
		Object[][] testArray = manager.getIssueListAsArray();
		assertTrue(testArray.length == 5);
		assertTrue(testArray[1][0].equals(3));
		assertTrue(testArray[2][3].equals("Issue description"));
	}
	
	/**
	 * Test the getIssueListAsArray
	 */
	@Test
	public void testGetIssueListAsArrayByIssueType() {
		manager = IssueManager.getInstance();
		manager.loadIssuesFromFile(validTestFile);
		Object[][] testArray = manager.getIssueListAsArrayByIssueType("bug");
		assertTrue(testArray.length == 2);
		assertTrue(testArray[0][0].equals(3));
		assertTrue(testArray[1][3].equals("Issue description"));
	}
	
	/**
	 * Test get issue by id
	 */
	@Test
	public void testgetIssueById() {
		ArrayList<Issue> issues = IssueReader.readIssuesFromFile(validTestFile);
		manager = IssueManager.getInstance();
		manager.loadIssuesFromFile(validTestFile);
		Issue issue0 = issues.get(0);
		assertEquals(issue0.getIssueId(), manager.getIssueById(1).getIssueId());
	}
	
	/**
	 * Test the execute command
	 */
	@Test
	public void testExecuteCommand() {
		
		// confirm the bug
		Command command1 = new Command(CommandValue.RESOLVE, "", Resolution.WONTFIX, "lets get working on this bug");
		ArrayList<Issue> issues = IssueReader.readIssuesFromFile(validTestFile);
		manager = IssueManager.getInstance();
		manager.loadIssuesFromFile(validTestFile);
		manager.executeCommand(3, command1);
		assertTrue(manager.getIssueById(3).getStateName().equalsIgnoreCase("CLOSED"));
		
		
	}
	
	
}
