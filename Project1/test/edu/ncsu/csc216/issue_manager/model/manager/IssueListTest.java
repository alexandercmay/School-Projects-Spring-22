package edu.ncsu.csc216.issue_manager.model.manager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.issue_manager.model.command.Command;
import edu.ncsu.csc216.issue_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.issue_manager.model.command.Command.Resolution;
import edu.ncsu.csc216.issue_manager.model.io.IssueReader;
import edu.ncsu.csc216.issue_manager.model.issue.Issue;
import edu.ncsu.csc216.issue_manager.model.issue.Issue.IssueType;

/**
 * The test file to ensure the proper function of IssueList
 * @author Alexander May
 *
 */
class IssueListTest {

	/** Valid course records */
	private final String validTestFile = "test-files/issue_records1.txt";

	/**
	 * Testing the add issues method
	 */
	@Test
	public void testAddIssuesFromList() throws FileNotFoundException {
		
		//load in from valid file
		ArrayList<Issue> issues = IssueReader.readIssuesFromFile(validTestFile);
		// create a new issue
		Issue issue10 = new Issue(10, IssueType.BUG, "summary", "note");
		// add the issue
		issues.add(issue10);
		// create a new issue list
		IssueList il = new IssueList();
		// add the issue array to the list
		il.addIssues(issues);
		// create a local array of the issues from issueList for checking values
		ArrayList<Issue> issList = il.getIssues();
		// assert issues have correct states via id
		assertEquals(issList.get(0).getIssueId(), 1);
		assertEquals(issList.get(3).getIssueId(), 10);
		assertEquals(issList.get(4).getIssueId(), 14);
	}
	
	/**
	 * Testing the add issue with three parameters
	 */
	@Test
	public void addIssueThreeParameters() throws FileNotFoundException {
		// create a new array list of issues using IssueReader
		ArrayList<Issue> issues = IssueReader.readIssuesFromFile(validTestFile);
		// create a new IssueList
		IssueList il = new IssueList();
		// Add the issue array to the list
		il.addIssues(issues);
		// add a new issue and confirm that its id is one higher than the previous high (15)
		assertEquals(il.addIssue(IssueType.BUG, "bugging", "buggard"), 16);
	}
	
	/**
	 * Testing get issues by type
	 */
	@Test
	public void testGettIssueTypes() throws FileNotFoundException {
		// create a new array list of issues using IssueReader
		ArrayList<Issue> issues = IssueReader.readIssuesFromFile(validTestFile);
		// create a new IssueList
		IssueList il = new IssueList();
		// add the array to the list
		il.addIssues(issues);
		// filter by type bug and create an array to store bugs
		ArrayList<Issue> bugs = il.getIssuesByType("bug");
		// ensure that only two bugs exist in returned array
		assertEquals(bugs.size(), 2);
	}
	
	/**
	 * Test get issue by id
	 */
	@Test
	public void testGetIssueById() throws FileNotFoundException {
		// create a new array list of issues using IssueReader
		ArrayList<Issue> issues = IssueReader.readIssuesFromFile(validTestFile);
		// create a new IssueList
		IssueList il = new IssueList();
		// add the array to the list
		il.addIssues(issues);
		// ensure that the IssueList's returned Issue is the same as the first issue in the array 
		assertEquals(il.getIssueById(1), issues.get(0));
	}
	
	/**
	 * Test delete issue by id
	 */
	@Test
	public void testDeleteIssueById() throws FileNotFoundException {
		// create a new array list of issues using IssueReader
		ArrayList<Issue> issues = IssueReader.readIssuesFromFile(validTestFile);
		// create a new IssueList
		IssueList il = new IssueList();
		// add the array to the list
		il.addIssues(issues);
		// delete the issue with an id of 1
		il.deleteIssueById(1);
		// null should be returned as the id no longer exists 
		assertNull(il.getIssueById(1));
		
	}
	
	/**
	 * Test the execute command
	 */
	@Test
	public void testExecute() {
		// create a new array list of issues using IssueReader
		ArrayList<Issue> issues = IssueReader.readIssuesFromFile(validTestFile);
		// create a new IssueList
		IssueList il = new IssueList();
		// add the array to the list
		il.addIssues(issues);
		// create a command
		Command command1 = new Command(CommandValue.RESOLVE, "alex", Resolution.WONTFIX, "duplicate bug");
		// execute command on issue with id 1
		il.executeCommand(1, command1);
		// get the updated issue
		Issue updatedIssue = il.getIssueById(1);
		// confirm the resolution is now wontfix
		assertTrue("WONTFIX".equalsIgnoreCase(updatedIssue.getResolution()));
	}
	
	
	
}
