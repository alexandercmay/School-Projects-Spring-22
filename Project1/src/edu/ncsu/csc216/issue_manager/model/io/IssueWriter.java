package edu.ncsu.csc216.issue_manager.model.io;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc216.issue_manager.model.issue.Issue;

/**
 * Writes the given list of Issues to the file name provided.
 * @author Alexander May
 *
 */
public class IssueWriter {
	

	
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
				Issue current = issue.get(i);
			   // fileWriter.println(issue.get(i).toString().trim());
				String issueString = "";
				issueString += "*";
				issueString += current.getIssueId() + ",";
				issueString += current.getStateName() + ","; 
				issueString += current.getIssueType() + ",";
				issueString += current.getSummary() + ",";
				
				if (current.getOwner() == null) {
					issueString += "null,";
				}
				else {issueString += current.getOwner() + ",";
				}
				issueString += current.isConfirmed() + ",";
				
				if (current.getResolution() == null) {
					issueString += "\n";
				}
				else {
					issueString += current.getResolution() + "\n"; 
				}

				issueString += current.getNotesString();
				fileWriter.println(issueString.trim());
			}
			fileWriter.close();
			
		}
		catch (FileNotFoundException e){
			
			throw new IllegalArgumentException("Unable to save file");
			
		}
	}
}
