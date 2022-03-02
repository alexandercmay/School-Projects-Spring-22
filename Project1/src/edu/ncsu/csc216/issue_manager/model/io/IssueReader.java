package edu.ncsu.csc216.issue_manager.model.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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
	 * 
	 */
	public IssueReader() {
		
	}
	
	/**
	 * Reads issues from a file and adds to list as long as there are no errors in processing
	 * @param filename the name of the file to read issues from
	 * @return a non-ordered ArrayList of Issues based on the contents of the file
	 * @throws FileNotFoundException if the file cannot be found or read
	 */
	public static ArrayList<Issue> readIssuesFromFile(String filename) throws FileNotFoundException{
		// create a new scanner
		Scanner fileReader = new Scanner(new FileInputStream(filename));
		// create a new issues array
		ArrayList<Issue> issues = new ArrayList<Issue>();
		try {
			String fileString = "";
			// while there are lines to process in the file
			while(fileReader.hasNextLine()) {
				// tack a new line onto the end of each line
				fileString += fileReader.nextLine()+  "\n";
			}
			// done with reading in the file directly
			fileReader.close();
			// parse the string using \\r?\\n?[*] 
			Scanner issueSeparator = new Scanner(fileString);
			issueSeparator.useDelimiter("\\r?\\n?[*]");
			// while the issue has more issue sections
			while(issueSeparator.hasNext()) {
				// use the processIssue method to create an issue
				Issue issue = processIssue(issueSeparator.next());
				// add the created issue to the issues list
				issues.add(issue);
			}
			issueSeparator.close();
			
		} 
		catch (Exception e) {
			issues.clear();
			// let the FNFE do the work here
		}
		return issues;
	}
	
	/**
	 * A helper class to process lines and tokens as information specific to Issues
	 * @param issue the string containing the issue to be parsed
	 * @return the Issue object once the String has been parsed
	 */
	private static Issue processIssue(String issue) {
		// create a scanner for the string
		Scanner issueCreator = new Scanner(issue);
		// create an array for notes
		ArrayList<String> notes = new ArrayList<String>();
		try {
			String firstLine = issueCreator.nextLine();
			
			Scanner fieldScanner = new Scanner(firstLine);
			fieldScanner.useDelimiter(",");
			int id = fieldScanner.nextInt();
			String state = fieldScanner.next();
			String type = fieldScanner.next();
			String summary = fieldScanner.next();
			String owner = fieldScanner.next();
			boolean confirmed = fieldScanner.nextBoolean();
			String resolution = "";
			if (fieldScanner.hasNext()) {
				resolution = fieldScanner.next();	
			}
			if (fieldScanner.hasNext()) {
				fieldScanner.close();
				throw new IllegalArgumentException("Invalid data.");
			}
			fieldScanner.close();
			
			
			issueCreator.useDelimiter("\r?\n?[-]");
			while(issueCreator.hasNext()) {
				notes.add(issueCreator.next());
			}
			Issue issueFromString = new Issue(id, state, type, summary, owner, confirmed, resolution, notes);
			issueCreator.close();
			return issueFromString;
			
		}
		catch (Exception e) {
			
			throw new IllegalArgumentException("Invalid data.");
		}
		
	}
	
}
