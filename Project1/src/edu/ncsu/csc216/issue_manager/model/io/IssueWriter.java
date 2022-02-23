package edu.ncsu.csc216.issue_manager.model.io;


import java.util.List;

import edu.ncsu.csc216.issue_manager.model.issue.Issue;

/**
 * Writes the given list of Issues to the file name provided.
 * @author Alexander May
 *
 */
public class IssueWriter {
	
	/**
	 * Creates a new IssueWriter
	 */
	public IssueWriter() {
		
	}
	
	/**
	 * Writes issues to a file. Any errors while processing leads to an exception being thrown
	 * @param filename  the name of the file to save to or write
	 * @param issue     the ArrayList of Issues to save to the file
	 * @throws IllegalArgumentException if the file cannot be saved
	 */
	public static void writeIssuesToFile(String filename, List<Issue> issue) {
		
	}
}
