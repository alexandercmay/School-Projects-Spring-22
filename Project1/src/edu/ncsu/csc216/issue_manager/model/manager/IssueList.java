package edu.ncsu.csc216.issue_manager.model.manager;

import java.util.ArrayList;

import edu.ncsu.csc216.issue_manager.model.command.Command;
import edu.ncsu.csc216.issue_manager.model.issue.Issue;
import edu.ncsu.csc216.issue_manager.model.issue.Issue.IssueType;

public class IssueList {

	private int counter;
	
	public IssueList() {
		
	}
	
	public int addIssue(IssueType issue, String summary, String note) {
		 return 0;
	}
	
	public void addIssues(ArrayList<Issue> issues) {
		
	}
	
	private void addIssue(Issue issue) {
		
	}
	
	public ArrayList<Issue> getIssues(){
		return null;
	}
	
	public ArrayList<Issue> getIssuesByType(String issue){
		return null;
	}
	
	public Issue getIssueById(int id) {
		return null;
	}
	
	public void executeCommand(int id, Command command) {
		
	}
	
	public void deleteIssueById(int id) {
		
	}
}
