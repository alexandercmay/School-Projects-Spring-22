package edu.ncsu.csc216.issue_manager.model.manager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
	/** Expected course records after saving to file **/
	private final String expFile = "test-files/expected_issue_records1.txt";
	

	
	/**
	 * Test the getIssueListAsArray
	 */
	@Test
	public void testGetIssueListAsArray() {
		manager = IssueManager.getInstance();
		manager.loadIssuesFromFile(validTestFile);
		Object[][] testArray = manager.getIssueListAsArray();
		assertSame(testArray.length, 5);
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
		assertSame(testArray.length, 2);
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
		manager = IssueManager.getInstance();
		manager.loadIssuesFromFile(validTestFile);
		manager.executeCommand(3, command1);
		assertTrue(manager.getIssueById(3).getStateName().equalsIgnoreCase("CLOSED"));
		
		
	}
	
	/**
	 * Test save issue to file
	 */
	@Test
	public void testSaveIssueToFile() {
		manager = IssueManager.getInstance();
		manager.loadIssuesFromFile(validTestFile);
		manager.saveIssuesToFile("actFile");
		Object[][] actual = manager.getIssueListAsArray();
		assertEquals(actual.length, 5);
 		checkFiles(expFile, "actFile");
		
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
