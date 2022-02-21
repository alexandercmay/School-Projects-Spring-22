package edu.ncsu.csc216.issue_manager.model.manager;

import edu.ncsu.csc216.issue_manager.model.command.Command;
import edu.ncsu.csc216.issue_manager.model.issue.Issue;
import edu.ncsu.csc216.issue_manager.model.issue.Issue.IssueType;

public class IssueManager {

	private IssueManager() {
		
	}
	
	public static IssueManager getInstance() {
		return null;
	}
	
	public void saveIssuesToFile(String filename) {
		
	}
	
	public void loadIssuesFromFile(String filename) {
		
	}
	
	public void createNewIssueList() {
		
	}
	
	public Object[][] getIssueListAsArray(){
		return null;
	}
	
	public Object[][] getIssueListAsArrayByIssueType(String isssue){
		return null;
	}
	
	public Issue getIssueById(int id) {
		return null;
	}
	
	public void executeCommadn(int id, Command command) {
		
	}
	
	public void deleteIssueById(int id) {
		
	}
	
	public void addIssueToList(IssueType issue, String summary, String note) {
		
	}
 	
}
