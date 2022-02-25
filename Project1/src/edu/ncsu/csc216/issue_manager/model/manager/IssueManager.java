package edu.ncsu.csc216.issue_manager.model.manager;

import edu.ncsu.csc216.issue_manager.model.command.Command;
import edu.ncsu.csc216.issue_manager.model.issue.Issue;
import edu.ncsu.csc216.issue_manager.model.issue.Issue.IssueType;
/**
 * Implements the singleton design pattern. 
 * Works with the files that contain the saved Issues. 
 * Provides information to the GUI. 
 * Maintains the IssueList and handles GUI commands. 
 * @author Alexander May
 *
 */
public class IssueManager {

	/**
	 * The constructor for the IssueManager
	 */
	private IssueManager() {
		
	}
	
	/**
	 * Provides the instance of the IssueManager the GUI is currently handling
	 * @return the instanceof IssueManager
	 */
	public static IssueManager getInstance() {
		return null;
	}
	
	/**
	 * Saves the current issues to a file
	 * @param filename the name of the file to save the issues onto
	 */
	public void saveIssuesToFile(String filename) {
		
	}
	
	/**
	 * Loads issues from a specified file
	 * @param filename the name of the file to load issues from
	 */
	public void loadIssuesFromFile(String filename) {
		
	}
	
	/**
	 * Updates the global issueList reference to point to a new IssueList object
	 */
	public void createNewIssueList() {
		
	}
	
	/**
	 * Returns the issues where rows are Issues and columns are   0: ID   1: state name  2: type  3: summary
	 * @return return a 2D Object array containing issues
	 */
	public Object[][] getIssueListAsArray(){
		return null;
	}
	
	/**
	 * Returns the issues where rows are Issues and columns are   0: ID   1: state name  2: type  3: summary
	 * @param issue the String representing the issue type 
	 * @return return a 2D Object array containing issues of a certain type
	 */
	public Object[][] getIssueListAsArrayByIssueType(String issue){
		return null;
	}
	
	/**
	 * Grabs a specified Issue by ID
	 * @param id the ID that the user or GUI wants to grab
	 * @return the Issue tied to the specified ID
	 */
	public Issue getIssueById(int id) {
		return null;
	}
	
	/**
	 * Executes a desired command based on the id of the issue and the specified command
	 * @param id        the ID of the issue sought after
	 * @param command   the command the user/GUI wants to execute upon the issue
	 */
	public void executeCommand(int id, Command command) {
		
	}
	
	/**
	 * Deletes a specified issue based on the ID
	 * @param id the ID attached to the issue the user is seeking to delete
	 */
	public void deleteIssueById(int id) {
		
	}
	
	/**
	 * Adds an issue to the IssueList. 
	 * @param issue    the type of issue that you want to add (eg enhancement, bug)
	 * @param summary  the summary of the issue as a string 
	 * @param note     the note tied to the issue as a string
	 */
	public void addIssueToList(IssueType issue, String summary, String note) {
		
	}
 	
}
