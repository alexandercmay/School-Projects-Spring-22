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
import edu.ncsu.csc216.issue_manager.model.issue.Issue.IssueType;

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
		// new instance of manager
		manager = IssueManager.getInstance();
		// load issues from valid file
		manager.loadIssuesFromFile(validTestFile);
		// create a new object array using getIssueListAsArray
		Object[][] testArray = manager.getIssueListAsArray();
		// confirm elements of array are as expected
		assertSame(testArray.length, 5);
		assertTrue(testArray[1][0].equals(3));
		assertTrue(testArray[2][3].equals("Issue description"));
	}
	
	/**
	 * Test the getIssueListAsArray
	 */
	@Test
	public void testGetIssueListAsArrayByIssueType() {
		// new instance of manager
		manager = IssueManager.getInstance();
		// load issues from valid file
		manager.loadIssuesFromFile(validTestFile);
		// create object array of just bugs
		Object[][] testArray = manager.getIssueListAsArrayByIssueType("bug");
		// confirm elements of array are as expected
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
		// new instance of manager
		manager = IssueManager.getInstance();
		// load issues from valid file
		manager.loadIssuesFromFile(validTestFile);
		// confirm the correct issue was grabbed
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
		// new instance of manager
		manager = IssueManager.getInstance();
		// load issues from valid file
		manager.loadIssuesFromFile(validTestFile);
		// confirm the issue's state was updated correctly 
		manager.executeCommand(3, command1);
		assertTrue(manager.getIssueById(3).getStateName().equalsIgnoreCase("CLOSED"));
		
		
	}
	
	/**
	 * Test save issue to file
	 */
	@Test
	public void testSaveIssueToFile() {
		// new instance of manager
		manager = IssueManager.getInstance();
		// load issues from valid file
		manager.loadIssuesFromFile(validTestFile);
		// save issues to file
		manager.saveIssuesToFile("test-files/actFile.txt");
		// check that the size of the issue list in issue manager is correct
		Object[][] actual = manager.getIssueListAsArray();
		assertEquals(actual.length, 5);
		// check the file was saved correctly 
 		checkFiles(expFile, "test-files/actFile.txt");
		
	}
	
	/**
	 * Test createNewIssueList
	 */
	@Test
	public void testCreateNewIssueList() {
		// new instance of manager
		manager = IssueManager.getInstance();
		// load issues from valid file
		manager.loadIssuesFromFile(validTestFile);
		// check that the size of the issue list in issue manager is correct
		assertEquals(manager.getIssueListAsArray().length, 5);
		manager.createNewIssueList();
		// check that the size of the issue list in issue manager is correct
		assertEquals(manager.getIssueListAsArray().length, 0);
	}
	
	/**
	 * Tests addIssueToList 
	 */
	@Test
	public void testAddIssueToList() {
		// new instance of manager
		manager = IssueManager.getInstance();
		// load issues from valid file
		manager.loadIssuesFromFile(validTestFile);
		// check that the size of the issue list in issue manager is correct
		assertEquals(manager.getIssueListAsArray().length, 5);
		manager.addIssueToList(IssueType.BUG, "a bug to be sure", "a note to be not empty");
		// check that the size of the issue list in issue manager is correct
		assertEquals(manager.getIssueListAsArray().length, 6);
	}
	
	/**
	 * Test delete issue
	 */
	@Test
	public void testDeleteIssueById() {
		// new instance of manager
		manager = IssueManager.getInstance();
		// load issues from valid file
		manager.loadIssuesFromFile(validTestFile);
		manager.deleteIssueById(1);
		// check that the size of the issue list in issue manager is correct
		assertEquals(manager.getIssueListAsArray().length, 4);
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
