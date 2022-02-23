package edu.ncsu.csc216.issue_manager.model.io;

import java.util.ArrayList;

import edu.ncsu.csc216.issue_manager.model.issue.Issue;

/**
 * Processes a file containing issue information and creates a list of issues. 
 * Does not check for duplicates or ID order. 
 * @author Alexander May
 *
 */
public class IssueReader {
	
	/**
	 * Creates a new IssueReader
	 */
	public IssueReader() {
		
	}
	
	/**
	 * Reads issues from a file and adds to list as long as there are no errors in processing
	 * @param filename the name of the file to read issues from
	 * @return a non-ordered ArrayList of Issues based on the contents of the file
	 */
	public static ArrayList<Issue> readIssuesFromFile(String filename) {
		return null;
	}
	
	/**
	 * A helper class to process lines and tokens as information specific to Issues
	 * @param issue the string containing the issue to be parsed
	 * @return the Issue object once the String has been parsed
	 */
	private static Issue processIssue(String issue) {
		return null;
	}
	
}
