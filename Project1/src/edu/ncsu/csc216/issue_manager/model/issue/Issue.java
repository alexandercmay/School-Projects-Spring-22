package edu.ncsu.csc216.issue_manager.model.issue;

import java.util.ArrayList;

import edu.ncsu.csc216.issue_manager.model.command.Command;

/**
 * The Issue class that contains the 5 states in addition to the object properties and fields of Issues
 * @author Alexander May
 *
 */
public class Issue {
	
	public enum IssueType { ENHANCEMENT, BUG }

	public static final String I_ENHANCEMENT = "Enhancement";
	public static final String I_BUG = "Bug"; 
	public static final String NEW_NAME = "New";
	public static final String NEW_CONFIRMED = "Confirmed";
	public static final String NEW_WORKING = "Working";
	public static final String NEW_VERIFYING = "Verifying";
	public static final String NEW_CLOSED = "Closed"; 
	private int issueId; 
	private String summary;
	private String owner; 
	private boolean confirmed; 
	private ArrayList<String> note; 
	
	
	public Issue(int id, IssueType issueType, String summary, String note) {
			
	}
	
	public Issue(int id, String state, String issueType, String summary,
			String owner, boolean confirmed, String resolution, ArrayList<String> notes) {
		
	}
	
	private void setIssueId(int issueId) {
		
	}
	
	private void setState(String state) {
		
	}
	
	private void setIssueType(String type) {
		
	}
	
	private void setSummary(String summary) {
		
	}
	
	private void setOwner(String owner) {
		
	}
	
	private void setConfirmed(boolean confirmed) {
		
	}
	
	private void setResolution(String resolution) {
		
	}
	
	private void setNotes(ArrayList<String> notes) {
		
	}
	
	public int getIssueId() {
		return 0;
	}
	
	public String getStateName() {
		return null; 
	}
	
	public String getIssueType() {
		return null;
	}
	
	public String getResolution() {
		return null;
	}
	
	public String getOwner() {
		return null;
	}
	
	public String getSummary() {
		return null;
	}
	
	public ArrayList<String> getNotes(){
		return null;
	}
	
	public String getNotesString() {
		return null;
	}
	
	public boolean isConfirmed(){
		return false;
	}
	
	private void addNote(String note) {
		
	}
	
	public void update(Command c) {
		
	}
	@Override
	public String toString() {
		return "Issue [issueId=" + issueId + ", summary=" + summary + ", owner=" + owner + ", confirmed=" + confirmed
				+ ", note=" + note + "]";
	}


	/**
	 * The Issue State class provides API for the expected methods or transitions of the 5 states 
	 * The 5 states are new, confirmed, working, verifying, and closed. 
	 * Each state's transitions will be managed as inner classes in Issue
	 * @author Dr. Sarah Heckman (sarah_heckman@ncsu.edu) 
	 *
	 */
	 private interface IssueState {
		
		/**
		 * Update the Issue based on the given Command.
		 * An UnsupportedOperationException is throw if the Command
		 * is not a valid action for the given state.  
		 * @param command Command describing the action that will update the Issue's
		 * state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 * for the given state.
		 */
		void updateState(Command command);
		
		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		String getStateName();

	 }
	 
	 private class NewState implements IssueState {
		 
		 private NewState() {
			 
		 }
		 
		 @Override 
		 public void updateState(Command c) {
			 
		 }
		 
		 @Override 
		 public String getStateName() {
			 return null;
		 }
		 
	 }
	 
	 private class WorkingState implements IssueState {
		 
		 private WorkingState() {
			 
		 }
		 
		 @Override 
		 public void updateState(Command c) {
			 
		 }
		 
		 @Override 
		 public String getStateName() {
			 return null;
		 }
		 
	 }
	 
	 private class ConfirmedState implements IssueState {
		 
		 private ConfirmedState() {
			 
		 }
		 
		 @Override 
		 public void updateState(Command c) {
			 
		 }
		 
		 @Override 
		 public String getStateName() {
			 return null;
		 }
		 
	 }
	 
	 private class VerifyingState implements IssueState {
		 
		 private VerifyingState() {
			 
		 }
		 
		 @Override 
		 public void updateState(Command c) {
			 
		 }
		 
		 @Override 
		 public String getStateName() {
			 return null;
		 }
		 
	 }
	 
	 private class ClosedState implements IssueState {
		 
		 private ClosedState() {
			 
		 }
		 
		 @Override 
		 public void updateState(Command c) {
			 
		 }
		 
		 @Override 
		 public String getStateName() {
			 return null;
		 }
		 
	 }


}


