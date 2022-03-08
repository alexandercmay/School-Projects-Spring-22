package edu.ncsu.csc216.issue_manager.model.io;

import java.io.FileInputStream;
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
	 * Reads issues from a file and adds to list as long as there are no errors in processing
	 * @param filename the name of the file to read issues from
	 * @return a non-ordered ArrayList of Issues based on the contents of the file
	 * @throws IllegalArgumentException if there are any errors reading in a file
	 */
	public static ArrayList<Issue> readIssuesFromFile(String filename) {
		
		// create a new issues array
		ArrayList<Issue> issues = new ArrayList<Issue>();
		try {
			// create a new scanner
			Scanner fileReader = new Scanner(new FileInputStream(filename));
			
			String fileString = "";

			// while there are lines to process in the file
			while(fileReader.hasNextLine()) {
				// tack a new line onto the end of each line
				fileString += fileReader.nextLine() +  "\n";
			}
			// done with reading in the file directly
			fileReader.close();
			// if the first line is not the format *x,x,x...
			if(fileString.charAt(0) != '*') {
				throw new IllegalArgumentException("Unable to load file.");
			}
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
			// close the issue separator
			issueSeparator.close();	
		} 
		catch (Exception e) {
			// if an error is caught, clear the list of issues that were not erroneous
			issues.clear();
			throw new IllegalArgumentException("Unable to load file.");
		}
		// return the array of issues
		return issues;
	}
	
	/**
	 * A helper class to process lines and tokens as information specific to Issues
	 * @param issue the string containing the issue to be parsed
	 * @return the Issue object once the String has been parsed
	 * @throws IllegalArgumentException if there are too many tokens in the field line
	 * @throws IllegalArgumnetException if the issue cannot be created
	 */
	private static Issue processIssue(String issue) {
		// create a scanner for the string
		Scanner noteScanner = new Scanner(issue);
		// create an array for notes
		ArrayList<String> notes = new ArrayList<String>();
		try {
			// grab the first line containing the fields
			String firstLine = noteScanner.nextLine();
			// create a scanner for the first line
			Scanner fieldScanner = new Scanner(firstLine);
			// parse using commas
			fieldScanner.useDelimiter(",");
			int id = fieldScanner.nextInt();
			String state = fieldScanner.next();
			String type = fieldScanner.next();
			String summary = fieldScanner.next();
			String owner = fieldScanner.next();
			boolean confirmed = fieldScanner.nextBoolean();
			// set default resolution to empty since it is the last token
			String resolution = "";
			// if there is one more token, it is a resolution besides the empty string
			if (fieldScanner.hasNext()) {
				resolution = fieldScanner.next();	
			}
			// any more tokens represents an error in the data
			if (fieldScanner.hasNext()) {
				fieldScanner.close();
				throw new IllegalArgumentException("Invalid data.");
			}
			// close the field scanner
			fieldScanner.close();
			
			// use the delimiter for getting notes
			noteScanner.useDelimiter("\r?\n?[-]");
			


			// while there are more notes
			while(noteScanner.hasNext()) {
				//add note to the notes list
				String nextNote = noteScanner.next();
				notes.add(nextNote);
			}
			
			// create a new issue from the data 
			Issue issueFromString = new Issue(id, state, type, summary, owner, confirmed, resolution, notes);
			// close the issueCreator scanner
			noteScanner.close();
			// return the issue made from the string
			return issueFromString;
			
		}
		// catch any errors in processing
		catch (Exception e) {
			// invalid data
			throw new IllegalArgumentException("Invalid data.");
		}
		
	}
	
}
