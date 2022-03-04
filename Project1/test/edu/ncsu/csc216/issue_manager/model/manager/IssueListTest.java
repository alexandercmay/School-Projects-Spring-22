package edu.ncsu.csc216.issue_manager.model.manager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

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
		
		ArrayList<Issue> issues = IssueReader.readIssuesFromFile(validTestFile);
		Issue issue10 = new Issue(10, IssueType.BUG, "summary", "note");
		issues.add(issue10);
		IssueList il = new IssueList();
		il.addIssues(issues);
		ArrayList<Issue> issList = il.getIssues();
		assertEquals(issList.get(0).getIssueId(), 1);
		assertEquals(issList.get(3).getIssueId(), 10);
		assertEquals(issList.get(4).getIssueId(), 14);
	}
	
	/**
	 * Testing the add issue with three parameters
	 */
	@Test
	public void addIssueThreeParameters() throws FileNotFoundException {
		
		ArrayList<Issue> issues = IssueReader.readIssuesFromFile(validTestFile);
		IssueList il = new IssueList();
		il.addIssues(issues);
		assertEquals(il.addIssue(IssueType.BUG, "bugging", "buggard"), 16);
	}
	
	/**
	 * Testing get issues by type
	 */
	@Test
	public void testGettIssueTypes() throws FileNotFoundException {
		
		ArrayList<Issue> issues = IssueReader.readIssuesFromFile(validTestFile);
		IssueList il = new IssueList();
		il.addIssues(issues);
		ArrayList<Issue> bugs = il.getIssuesByType("bug");
		assertEquals(bugs.size(), 2);
	}
	
	/**
	 * Test get issue by id
	 */
	@Test
	public void testGetIssueById() throws FileNotFoundException {
		
		ArrayList<Issue> issues = IssueReader.readIssuesFromFile(validTestFile);
		IssueList il = new IssueList();
		il.addIssues(issues);
		assertEquals(il.getIssueById(1), issues.get(0));
	}
	
	/**
	 * Test delete issue by id
	 */
	@Test
	public void testDeleteIssueById() throws FileNotFoundException {
		
		ArrayList<Issue> issues = IssueReader.readIssuesFromFile(validTestFile);
		IssueList il = new IssueList();
		il.addIssues(issues);
		il.deleteIssueById(1);
		assertNull(il.getIssueById(1));
		
	}
	
}
