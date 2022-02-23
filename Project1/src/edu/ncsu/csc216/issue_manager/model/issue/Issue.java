package edu.ncsu.csc216.issue_manager.model.issue;

import java.util.ArrayList;

import edu.ncsu.csc216.issue_manager.model.command.Command;

/**
 * The Issue class that contains the 5 states in addition to the object properties and fields of Issues
 * @author Alexander May
 *
 */
public class Issue {
	
	/**
	 * The types of Issues (enhancement, bug) 
	 * @author Alexander May
	 *
	 */
	public enum IssueType { ENHANCEMENT, BUG }

	/** A constant string for the “Enhancement” issue type. **/
	public static final String I_ENHANCEMENT = "Enhancement";
	/** A constant string for the “Bug” issue type. **/
	public static final String I_BUG = "Bug"; 
	/** A constant string for the new state’s name with the value “New”. **/
	public static final String NEW_NAME = "New";
	/** A constant string for the confirmed state’s name with the value “Confirmed”.. **/
	public static final String CONFIRMED_NAME = "Confirmed";
	/** A constant string for the working state’s name with the value “Working”. **/
	public static final String WORKING_NAME = "Working";
	/** A constant string for the verifying state’s name with the value “Verifying”. **/
	public static final String VERIFYING_NAME = "Verifying";
	/** A constant string for the closed state’s name with the value “Closed”. **/
	public static final String CLOSED_NAME = "Closed"; 
	/** Unique issue id for an issue. **/
	private int issueId; 
	/** Issue’s summary information from when the issue is created. **/
	private String summary;
	/** User id of the issue owner or null if there is not an assigned owner. **/
	private String owner; 
	/** True if the issue is confirmed. The confirmed field can only be set to true if the issue is a bug. **/
	private boolean confirmed; 
	/** An ArrayList of notes in the form of Strings**/
	private ArrayList<String> note; 
	
	/**
	 * The constructor that uses the id, type, summary, and note
	 * @param id           the ID of the Issue
	 * @param issueType    the Issue type
	 * @param summary      the summary of the Issue
	 * @param note         the Issue's note
	 */
	public Issue(int id, IssueType issueType, String summary, String note) {
			
	}
	
	/**
	 * The constructor that can set values for Issue's fields in greater detail than the other constructor
	 * @param id           the ID of the issue
	 * @param state        the state of the Issue (new, working, confirmed, verifying, closed) 
	 * @param issueType    the type of Issue (bug, enhancement) 
	 * @param summary      the summary of the Issue
	 * @param owner        the Issue's owner
	 * @param confirmed    true if the Issue is confirmed
	 * @param resolution   the resolution of the Issue (fixed, duplicate, wontFix, worksForMe)
	 * @param notes        the notes of the Issue
	 */
	public Issue(int id, String state, String issueType, String summary,
			String owner, boolean confirmed, String resolution, ArrayList<String> notes) {
		
	}
	
	/**
	 * Sets the ID of the issue
	 * @param issueId the int to set to the ID
	 */
	private void setIssueId(int issueId) {
		
	}
	
	/**
	 * Sets the state of the Issue (new, working, confirmed, verifying, closed) 
	 * @param state the String for the Issue state
	 */
	private void setState(String state) {
		
	}
	
	/**
	 * Sets the type for the issue (bug, enhancement)
	 * @param type the type of Issue as a String
	 */
	private void setIssueType(String type) {
		
	}
	
	/**
	 * The summary of the Issue
	 * @param summary the summary as a String
	 */
	private void setSummary(String summary) {
		
	}
	
	/**
	 * Sets the Issue's owner
	 * @param owner the Issue's owner as a String
	 */
	private void setOwner(String owner) {
		
	}
	
	/**
	 * Sets the state to confirmed (T/F)
	 * @param confirmed the boolean value. True if confirmed. 
	 */
	private void setConfirmed(boolean confirmed) {
		
	}
	
	/**
	 * Sets the Issue's resolution
	 * @param resolution the String identifying the resolution to set
	 */
	private void setResolution(String resolution) {
		
	}
	
	/**
	 * Sets the Issue's notes
	 * @param notes the ArrayList of notes in the form of String for an Issue
	 */
	private void setNotes(ArrayList<String> notes) {
		
	}
	
	/**
	 * Grabs the Issue's ID
	 * @return ID as an int
	 */ 
	public int getIssueId() {
		return 0;
	}
	
	/**
	 * The Issue's state name
	 * @return state as a String
	 */
	public String getStateName() {
		return null; 
	}
	
	/**
	 * The Issue's type as a String
	 * @return type as a String
	 */
	public String getIssueType() {
		return null;
	}
	
	/**
	 * Gets the Issue's resolution
	 * @return resolution as a String
	 */
	public String getResolution() {
		return null;
	}
	
	/**
	 * Gets the Issue's owner
	 * @return the owner as a String
	 */
	public String getOwner() {
		return null;
	}
	
	/**
	 * Gets the Issue's summary
	 * @return the summary as a String
	 */
	public String getSummary() {
		return null;
	}
	
	/**
	 * Gets the Issue's notes
	 * @return an ArrayList of notes as Strings
	 */
	public ArrayList<String> getNotes(){
		return null;
	}
	
	/**
	 * Gets the Issue's note as a single String
	 * @return a single String containing an Issue's notes
	 */
	public String getNotesString() {
		return null;
	}
	
	/**
	 * Whether the Issue is in the confirmed state
	 * @return True if confirmed
	 */
	public boolean isConfirmed(){
		return false;
	}
	
	/**
	 * Adds a note to the Issue
	 * @param note the note to add as a String
	 */
	private void addNote(String note) {
		
	}
	
	/**
	 * The command to update an issue with
	 * @param c the Command to update the issue with
	 */
	public void update(Command c) {
		
	}
	
	/**
	 * Created a string using the ID, summary, owner, confirmation status, and notes
	 */
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
	 
	 /**
	  * Represents the "New" state that an issue can be in
	  * @author Alexander May
	  *
	  */
	 private class NewState implements IssueState {
		 
		 /**
		  * The constructor for the New state
		  */
		 private NewState() {
			 
		 }
		 
		 /**
		  * New can transition to working or confirmed or closed
		  * @param c the Command to attempt to enact upon New
		  */
		 @Override 
		 public void updateState(Command c) {
			 
		 }
		 
		 /**
		  * Returns the name of the state New
		  * @return New as a String
		  */
		 @Override 
		 public String getStateName() {
			 return null;
		 }
		 
	 }
	 
	 /**
	  * Represents the "Working" state that an issue can be in
	  * @author Alexander May
	  *
	  */
	 private class WorkingState implements IssueState {
		 
		 /**
		  * The constructor for the working state
		  */
		 private WorkingState() {
			 
		 }
		 
		 /**
		  * Working can transition to Closed or Verifying
		  * @param c the Command to attempt to enact upon Working
		  */
		 @Override 
		 public void updateState(Command c) {
			 
		 }
		 
		 /**
		  * Provides the state name Working
		  * @return Working as a String
		  */
		 @Override 
		 public String getStateName() {
			 return null;
		 }
		 
	 }
	 
	 /**
	  * Represents the "Confirmed" state that an issue can be in
	  * @author Alexander May
	  *
	  */
	 private class ConfirmedState implements IssueState {
		 
		 private ConfirmedState() {
			 
		 }
		 
		 /**
		  * Confirmed can transition to Working or Closed
		  * @param c the Command to attempt to enact upon Confirmed
		  */
		 @Override 
		 public void updateState(Command c) {
			 
		 }
		 
		 /**
		  * Provides the state name Confirmed
		  * @return Confrimed as a String
		  */
		 @Override 
		 public String getStateName() {
			 return null;
		 }
		 
	 }
	 
	 /**
	  * Represents the "Verifying" state that an issue can be in
	  * @author Alexander May
	  *
	  */
	 private class VerifyingState implements IssueState {
		 
		 private VerifyingState() {
			 
		 }
		 
		 /**
		  * Verifying can transition to Working or Closed
		  * @param c the Command to attempt to enact upon Verifying
		  */
		 @Override 
		 public void updateState(Command c) {
			 
		 }
		 
		 /**
		  * Provides the state name Verifying
		  * @return Verifying as a String
		  */
		 @Override 
		 public String getStateName() {
			 return null;
		 }
		 
	 }
	 
	 /**
	  * Represents the "Closed" state that an issue can be in
	  * @author Alexander May
	  *
	  */
	 private class ClosedState implements IssueState {
		 
		 private ClosedState() {
			 
		 }
		 
		 /**
		  * Closed can transition to Confirmed or New or Working
		  * @param c the Command to attempt to enact upon Verifying
		  */
		 @Override 
		 public void updateState(Command c) {
			 
		 }
		 
		 /**
		  * Provides the state name Closed
		  * @return Closed as a String
		  */
		 @Override 
		 public String getStateName() {
			 return null;
		 }
		 
	 }


}


