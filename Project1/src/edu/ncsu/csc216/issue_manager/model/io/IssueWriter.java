package edu.ncsu.csc216.issue_manager.model.io;


import java.io.File;
import java.io.PrintStream;
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
		try {
			PrintStream fileWriter = new PrintStream(new File(filename));
			for (int i = 0; i < issue.size(); i++) {
			    fileWriter.println(issue.get(i).toString());
			}
			fileWriter.close();
			
		}
		catch (Exception e){
			
			throw new IllegalArgumentException("Unable to save file");
			
		}
	}
}
