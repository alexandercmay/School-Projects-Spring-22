package edu.ncsu.csc216.issue_manager.model.manager;

import java.util.ArrayList;

import edu.ncsu.csc216.issue_manager.model.command.Command;
import edu.ncsu.csc216.issue_manager.model.issue.Issue;
import edu.ncsu.csc216.issue_manager.model.issue.Issue.IssueType;

/**
 * Maintains a list of issues using ArrayLists. 
 * Maintains a counter which represents the id of the next issue to be added to the list
 * @author Alexander May
 *
 */
public class IssueList {

	/** Represents the id of the next issue to be added to the list **/
	private int counter;
	/** Represents the list of issues **/
	private ArrayList<Issue> issueMasterList = new ArrayList<Issue>();
	
	/**
	 * The constructor for a new IssueList. Issue counter is reset to 1 when a new list is created. 
	 */
	public IssueList() {
		counter = 1;
	}
	
	/**
	 * Adds an issue to the list. 
	 * @param issue    the type of issue that you want to add (eg enhancement, bug)
	 * @param summary  the summary of the issue as a string 
	 * @param note     the note tied to the issue as a string
	 * @return id      returns the id of the issue as an int
	 */
	public int addIssue(IssueType issue, String summary, String note) {
		Issue newIssue = new Issue(counter, issue, summary, note);
		counter++;
		addIssue(newIssue);
		return newIssue.getIssueId();
	}
	
	/**
	 * Adds issues (plural) to the list based on a passed through ArrayList.
	 * Adds the issues in a sorted order. 
	 * Counter is set to last id + 1
	 * @param issues the ArrayList of issues being read in 
	 */
	public void addIssues(ArrayList<Issue> issues) {
		for (int i = 0; i < issues.size(); i++) {
			if(i == 0) {
				issueMasterList.add(issues.get(i));
			}
			else {
			addIssue(issues.get(i));
			}
		}
		// get id of last issue
		int lastId = issueMasterList.get(issueMasterList.size()-1).getIssueId();
		// set counter to one greater than last id in list
		counter = lastId + 1;
	}
	
	/**
	 * Adds a single issue to the list
	 * @param issue the issue object to add to the list
	 */
	private void addIssue(Issue issue) {
		// loop through the master list
		for(int i = 0; i < issueMasterList.size(); i++) {
			int id = issue.getIssueId();
			int imId = issueMasterList.get(i).getIssueId();
			
			if (i == issueMasterList.size() - 1) {
				break;
			}  
			else if (imId < id) {
				continue;
			} 
			else if (imId == id) {
				return;
			} else if (imId > id) {
				issueMasterList.add(issue);
			}
		}
		issueMasterList.add(issue);
	}
	
	/**
	 * Grabs the list of all current issues as an ArrayList 
	 * @return the Issue ArrayList
	 */
	public ArrayList<Issue> getIssues(){
		return issueMasterList;
	}
	
	/**
	 * Returns an ArrayList of issues of a certain type (eg enhancement, bug)
	 * @param issue the type of issue you want to filter for
	 * @return issues by their type in an ArrayList
	 */
	public ArrayList<Issue> getIssuesByType(String issue){
		ArrayList<Issue> specIssue = new ArrayList<Issue>();
		for(int i = 0; i < issueMasterList.size(); i++) {
			String type = issueMasterList.get(i).getIssueType();
			if (type.equalsIgnoreCase(issue)) {
				specIssue.add(issueMasterList.get(i));
			}
		}
		return specIssue;
	}
	
	/**
	 * Returns a specific issue based off of a specific ID
	 * @param id the ID to find amongst the list of issues
	 * @return the Issue associated with the specified ID
	 */
	public Issue getIssueById(int id) {
		
		for(int i = 0; i < issueMasterList.size(); i++) {
			if (id == issueMasterList.get(i).getIssueId()) {
				return issueMasterList.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Executes a desired command based on the id of the issue and the specified command
	 * @param id        the ID of the issue sought after
	 * @param command   the command the user wants to execute upon the issue
	 */
	public void executeCommand(int id, Command command) {
		getIssueById(id).update(command);
	}
	
	/**
	 * Deletes a specified issue based on the ID
	 * @param id the ID attached to the issue the user is seeking to delete
	 */
	public void deleteIssueById(int id) {
		for(int i = 0; i < issueMasterList.size(); i++) {
			if (id == issueMasterList.get(i).getIssueId()) {
				issueMasterList.remove(i);
			}
		}
	}
}
