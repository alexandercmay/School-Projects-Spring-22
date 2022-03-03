package edu.ncsu.csc216.issue_manager.model.issue;

import java.util.ArrayList;

import edu.ncsu.csc216.issue_manager.model.command.Command;
import edu.ncsu.csc216.issue_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.issue_manager.model.command.Command.Resolution;

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
	private ArrayList<String> notes; 
	/** The issue type of the current issue **/
	private IssueType issueType; 
	/** The resolution of the issue **/
	private Resolution resolution;
	/** The Issue's state **/
	private IssueState state;
	/** Represents the new state **/
	private IssueState newState = new NewState();
	/** Represents the confirmed state **/
	private IssueState confirmedState = new ConfirmedState();
	/** Represents the working state **/
	private IssueState workingState = new WorkingState();
	/** Represents the verifying state **/
	private IssueState verifyingState = new VerifyingState();
	/** Represents the closed state **/
	private IssueState closedState = new ClosedState();
	
	
	/**
	 * The constructor that uses the id, type, summary, and note
	 * @param id           the ID of the Issue
	 * @param issueType    the Issue type
	 * @param summary      the summary of the Issue
	 * @param note         the Issue's note
	 * @throws IllegalArgumentException if any of the parameters are null or empty string, or if the id is less than 1
	 */
	public Issue(int id, IssueType issueType, String summary, String note) {
		// try to use Issue setters to set the fields 
		try {
			this.notes = new ArrayList<String>();
			setIssueId(id);
			setState(NEW_NAME);
			setIssueType(issueType.toString());
			setSummary(summary);
			setOwner("");
			setConfirmed(false);
			setResolution("");
			addNote(note);
		//if any of the fields cannot be set with given parameters
		} catch (Exception e) {
			throw new IllegalArgumentException("Issue cannot be created.");
		}
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
	 * @throws IllegalArgumnetException if the inputs are invalid
	 */
	public Issue(int id, String state, String issueType, String summary,
			String owner, boolean confirmed, String resolution, ArrayList<String> notes) {
		// try to use Issue setters to set the fields 
		try {
			setIssueId(id);
			setState(state);
			setIssueType(issueType);
			setSummary(summary);
			setOwner(owner);
			setConfirmed(confirmed);
			setResolution(resolution);
			setNotes(notes);
		// if any of the fields cannot be set with given parameters
		} catch (Exception e) {
			throw new IllegalArgumentException("Issue cannot be created.");
		}
		
	}
	
	/**
	 * Sets the ID of the issue
	 * @param issueId the int to set to the ID
	 * @throws IllegalArgumentException if the id is less than 1
	 */
	private void setIssueId(int issueId) {
		// throw if the id is less than 1
		if(issueId < 1) {
			throw new IllegalArgumentException("Invalid id");
		} else {
			this.issueId = issueId;
		}
	}
	
	/**
	 * Sets the state of the Issue (new, working, confirmed, verifying, closed) 
	 * @param state the String for the Issue state
	 * @throws IllegalArgumentException if the state is null or empty 
	 * @throws IllegalArgumentException if the state is an unrecognized type
	 */
	private void setState(String state) {
		if (state == null || "".equals(state)) {
			throw new IllegalArgumentException("Invalid state.");
		} else {
			if(NEW_NAME.equalsIgnoreCase(state)) {
				this.state = new NewState();
			} else if (CONFIRMED_NAME.equalsIgnoreCase(state)) {
				this.state = new ConfirmedState();
			} else if (WORKING_NAME.equalsIgnoreCase(state)) {
				this.state = new WorkingState();
			} else if (VERIFYING_NAME.equalsIgnoreCase(state)) {
				this.state = new VerifyingState();
			} else if (CLOSED_NAME.equalsIgnoreCase(state)) {
				this.state = new ClosedState();
			} else {
				throw new IllegalArgumentException("Invalid state.");
			}
		}
	}
	
	/**
	 * Sets the type for the issue (bug, enhancement)
	 * @param type the type of Issue as a String
	 * @throws IllegalArgumnetException if the type is not an enhancement or a bug
	 * @throws IllegalArgumentException if the type is enhancement and the state is confirmed
	 */
	private void setIssueType(String type) {
		// throw if the type is enhancement and the state is confirmed
		if(I_ENHANCEMENT.equalsIgnoreCase(type) && state.getStateName().equalsIgnoreCase(CONFIRMED_NAME)) {
			throw new IllegalArgumentException("Invalid type for state.");
		}
		// if the string is a bug
		else if (I_BUG.equalsIgnoreCase(type)) {
			this.issueType = IssueType.BUG; 
		} 
		// if the string is enhancement
		else if (I_ENHANCEMENT.equalsIgnoreCase(type)) {
			this.issueType = IssueType.ENHANCEMENT;
		}
		// otherwise throw an exception
		else {
			throw new IllegalArgumentException("Invalid issue type.");
		}
	}
	
	/**
	 * The summary of the Issue
	 * @param summary the summary as a String
	 */
	private void setSummary(String summary) {
		// cannot contain a comma
		if (summary.contains(",") || "".equals(summary) || null == summary) {
			throw new IllegalArgumentException("Cannot contain comma.");
		} else {
			// otherwise set the summary to the parameter
			this.summary = summary;
		}
	} 
	
	/**
	 * Sets the Issue's owner
	 * @param owner the Issue's owner as a String
	 */
	private void setOwner(String owner) {
		// working or verifying MUST have owner
		if(state.getStateName().equalsIgnoreCase(WORKING_NAME) || state.getStateName().equalsIgnoreCase(VERIFYING_NAME)) {
			if(owner == null || "".equals(owner)) {
				throw new IllegalArgumentException("Must have an owner for verifying or working.");
			}
		}
		// New and Confirmed must not have an owner
		else if(state.getStateName().equalsIgnoreCase(NEW_NAME) || state.getStateName().equalsIgnoreCase(CONFIRMED_NAME)) {
			if(owner != null && !("".equals(owner))) {
				throw new IllegalArgumentException("Must not have an owner if new or confirmed.");
			} else {
				this.owner = "";
			}
		}
		
		else {
			this.owner = owner;
		}
		
	}
	
	/**
	 * Sets the state to confirmed (T/F)
	 * @param confirmed the boolean value. True if confirmed. 
	 * @throws IllegalArgumentException if enhancement or new while confirmed is true
	 */
	private void setConfirmed(boolean confirmed) {
		// if the type is enhancement, the state is new, and the confirmed is true, throw IAE
		if((state.getStateName().equalsIgnoreCase(NEW_NAME) || getIssueType().equalsIgnoreCase(I_ENHANCEMENT)) && confirmed == true) {
			throw new IllegalArgumentException("Cannot be confirmed in current state.");
		} 
		// if the state is working but not confirmed
		else if (state.getStateName().equalsIgnoreCase(WORKING_NAME) && !confirmed) {
			throw new IllegalArgumentException("Cannot be confirmed in current state.");
		}
		else {
			this.confirmed = confirmed;
		}
	}
	
	/**
	 * Sets the Issue's resolution
	 * @param resolution the String identifying the resolution to set
	 * @throws IllegalArgumentException if type is enhancement and resolution is works for me
	 * @throws IllegalArgumentException if resolution is empty while in closed state
	 * @throws IllegalArgumentException if resolution is not FIXED while in verifying
	 * @throws IllegalArgumentException if the type is not WORKSFORME, DUPLICATE, WONTFIX, or FIXED
	 */
	private void setResolution(String resolution) {
		// throw if resolution is works for me and type is enhancement
		if (getIssueType().equalsIgnoreCase("Enhancement") && "WORKSFORME".equalsIgnoreCase(resolution)) {
			throw new IllegalArgumentException("Invalid resolution for type.");
		}
		// if resolution is empty while in closed state
		else if (state.getStateName().equalsIgnoreCase(CLOSED_NAME) && ("".equals(resolution) || null == resolution)) {
			throw new IllegalArgumentException("Invalid resolution for type.");
		}
		// issues in Verifying state must be fixed
		else if (state.getStateName().equalsIgnoreCase(VERIFYING_NAME) && !(resolution.equalsIgnoreCase("FIXED"))) {
			throw new IllegalArgumentException("Invalid resolution for type.");
		}
//		else if (state.getStateName().equalsIgnoreCase(NEW_NAME) && ("".equals(resolution) || null == resolution)) {
//			return;
//		} 
//		else if (state.getStateName().equalsIgnoreCase(CONFIRMED_NAME) && ("".equals(resolution) || null == resolution)) {
//			return;
//		} 
		else if (resolution == null || "".equals(resolution)) {
			return;
		}
		// otherwise determine the resolution type
		else {
			if (resolution.equalsIgnoreCase("WORKSFORME")) {
				this.resolution = Resolution.WORKSFORME;
			} else if (resolution.equalsIgnoreCase("WONTFIX")) {
				this.resolution = Resolution.WONTFIX;
			} else if (resolution.equalsIgnoreCase("DUPLICATE")) {
				this.resolution = Resolution.DUPLICATE;
			} else if (resolution.equalsIgnoreCase("FIXED")){
				this.resolution = Resolution.FIXED;
			} else {
				throw new IllegalArgumentException("Invalid resolution.");
			}
		}
	}
	
	/**
	 * Sets the Issue's notes
	 * @param notes the ArrayList of notes in the form of String for an Issue
	 */
	private void setNotes(ArrayList<String> notes) {
		// if notes is empty
		if (notes.isEmpty()) {
			throw new IllegalArgumentException("Notes cannot be empty");
		}
		this.notes = notes;
	}
	
	/**
	 * Grabs the Issue's ID
	 * @return ID as an int
	 */ 
	public int getIssueId() {
		return issueId;
	}
	
	/**
	 * The Issue's state name
	 * @return state as a String
	 */
	public String getStateName() {
		return state.getStateName(); 
	}
	
	/**
	 * The Issue's type as a String
	 * @return type as a String
	 */
	public String getIssueType() {
		// if the issue type is bug, return bug
		if(issueType.toString().equalsIgnoreCase(I_BUG)){
			return I_BUG;
		} 
		// otherwise return enhancement
		else {
			return I_ENHANCEMENT;
		}
	}
	
	/**
	 * Gets the Issue's resolution
	 * @return resolution as a String
	 */
	public String getResolution() {
		// return a string "" if state is new
		if (state.getStateName().equals(NEW_NAME)) {
			return "";
		} 
		else if (resolution == null) {
			return "";
		}
		else {
		return resolution.toString();
		}
	}
	
	/**
	 * Gets the Issue's owner
	 * @return the owner as a String
	 */
	public String getOwner() {
		return owner;
	}
	
	/**
	 * Gets the Issue's summary
	 * @return the summary as a String
	 */
	public String getSummary() {
		return summary;
	}
	
	/**
	 * Gets the Issue's notes
	 * @return an ArrayList of notes as Strings
	 */
	public ArrayList<String> getNotes(){
		return notes;
	}
	
	/**
	 * Gets the Issue's note as a single String
	 * @return a single String containing an Issue's notes
	 */
	public String getNotesString() {
		String notesString = "";
		ArrayList<String> notes = getNotes();
		for (int i = 0; i < notes.size(); i++) {
			// if the last note...dont print a new line
//			if(i == (notes.size() -1)) {
//				notesString += "-" + notes.get(i);
//			} 
//			else {
			notesString += "-" + notes.get(i) + "\n";
			}
//		}
		return notesString;
	}
	
	/**
	 * Whether the Issue is in the confirmed state
	 * @return True if confirmed
	 */
	public boolean isConfirmed(){
		return confirmed;
	}
	
	/**
	 * Adds a note to the Issue
	 * @param note the note to add as a String
	 * @throws IllegalArgumentException if the note is null or empty
	 */
	private void addNote(String note) {

		// note cannot be null or empty
		if (note == null || "".equals(note)) {
			throw new IllegalArgumentException("Note must have script.");
		} else {
			// note must have state as such [STATE_NAME] prepended 
			String noteAppended = "[" + getStateName() + "]";
			noteAppended += note;
			notes.add(noteAppended);
		}
	}
	
	/**
	 * The command to update an issue with
	 * @param c the Command to update the issue with
	 */
	public void update(Command c) {
		state.updateState(c);
		// get state as string
//		String state = getStateName();
//		if (state.equalsIgnoreCase(NEW_NAME)) {
//			
//		} 
//		else if (state.equalsIgnoreCase(CONFIRMED_NAME)) {
//			
//		}
//		else if (state.equalsIgnoreCase(WORKING_NAME)) {
//			
//		}
//		else if (state.equalsIgnoreCase(CONFIRMED_NAME)) {
//			
//		}
//		else if (state.equalsIgnoreCase(CLOSED_NAME)) {
//			
//		}
	}
	
	/**
	 * Created a string using the ID, summary, owner, confirmation status, and notes
	 */
	@Override
	public String toString() {
		String issueString = "";
		issueString += "* ";
		issueString += getIssueId() + ",";
		issueString += getStateName() + ","; 
		issueString += getIssueType() + ",";
		issueString += getSummary() + ",";
		issueString += getOwner() + ",";
		issueString += isConfirmed() + ",";
		issueString += getResolution() + "\n"; 
		issueString += getNotesString();
		return issueString;
		
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
		  * @throws UnsupportedOperationException if trying to assign an owner on a bug
		  * @throws UnsupportedOperationException if trying to confirm an enhancement
		  * @throws UnsupportedOperationException if trying to set resolution WORKSFORME to enhancement
		  * @throws UnsupportedOperationException if trying to do anything except verify, confirm, or resolve
		  */
		 @Override 
		 public void updateState(Command c) {
			 // if the command value is assign
			 if (c.getCommand() == CommandValue.ASSIGN) {
				 
				 // if the issue is an enhancement
				 if(issueType == IssueType.ENHANCEMENT) {
					 owner = c.getOwnerId();
					 state = workingState;
				 } else {
					 // throw if trying to assign an owner on a bug from new
					 throw new UnsupportedOperationException("Invalid information.");
				 }
			 } 
			 // if the command is to confirm
			 else if (c.getCommand() == CommandValue.CONFIRM) {
				 
				 // if the issue is a bug
				 if (issueType == IssueType.BUG) {
					 setConfirmed(true);
					 state = confirmedState;
				 } else {
					// throw if trying to confirm an enhancement
					 throw new UnsupportedOperationException("Invalid information.");
				 }
			 }
			 
			 // if the command is to resolve
			 else if (c.getCommand() == CommandValue.RESOLVE) {
				 try {
					 // set the resolution to the command's resolution
					 setResolution(c.getResolution().toString());
				 }
				 // might catch an issue if setting worksforme to enhancement 
				 catch (Exception e) {
					 throw new UnsupportedOperationException("Invalid information.");
				 }
			 }
			 // if not a supported command
			 else {
				 throw new UnsupportedOperationException("Invalid information.");
			 }
			 
			 addNote(c.getNote());
		 }
		 
		 /**
		  * Returns the name of the state New
		  * @return New as a String
		  */
		 @Override 
		 public String getStateName() {
			 return "New";
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
		  * @throws UnsupportedOperationException if trying to set worksforme to enhancement
		  */
		 @Override 
		 public void updateState(Command c) {
			 // if the resolution is fixed
			 if (c.getResolution() == Resolution.FIXED) {
				 // set to verifying
				 state = verifyingState;
			 } 
			 // if resolution is not fixed
			 else {
				 try {
					 setResolution(c.getResolution().toString());
					// set to closed
					 state = closedState;
				 }
				 // might catch exception setting worksforme to an enhancement
				 catch (Exception e) {
					 throw new UnsupportedOperationException("Invalid information.");
				 }
			 }
			 
			 //add the note
			 addNote(c.getNote());
		 }
		 
		 /**
		  * Provides the state name Working
		  * @return Working as a String
		  */
		 @Override 
		 public String getStateName() {
			 return "Working";
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
		  * @throws UnsupportedOperationException if not trying to assign an owner or resolving with wontfix
		  */
		 @Override 
		 public void updateState(Command c) {
			 // if assign 
			 if (c.getCommand() == CommandValue.ASSIGN) {
				 // assign to an owner
				 setOwner(c.getOwnerId());
				 // update to working state
				 state = workingState;
			 }
			 else if ((c.getCommand() == CommandValue.RESOLVE) && c.getResolution() == Resolution.WONTFIX) {
				 // set resolution to wontfix
				 setResolution(c.getResolution().toString());
				 // set state to closed
				 state = closedState;	 
			 } 
			 // throwing for any other case
			 else {
				 throw new UnsupportedOperationException("Invalid information.");
			 }
			 
			 addNote(c.getNote());
		 }
		 
		 /**
		  * Provides the state name Confirmed
		  * @return Confrimed as a String
		  */
		 @Override 
		 public String getStateName() {
			 return "Confirmed";
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
		  * @throws UnsupportedOperationException if not verifying or reopening
		  */
		 @Override 
		 public void updateState(Command c) {
			 // verified
			 if (c.getCommand() == CommandValue.VERIFY) {
				 // set to closed
				 state = closedState;
			 }
			 
			 // reopened
			 else if (c.getCommand() == CommandValue.REOPEN) {
				 // set to working
				 state = workingState;
			 }
			 // otherwise throw 
			 else {
				 throw new UnsupportedOperationException("Invalid information.");
			 }
			 // add note
			 addNote(c.getNote());
		 }
		 
		 /**
		  * Provides the state name Verifying
		  * @return Verifying as a String
		  */
		 @Override 
		 public String getStateName() {
			 return "Verifying";
		 }
		 
	 }
	 
	 /**
	  * Represents the "Closed" state that an issue can be in
	  * @author Alexander May
	  * @throws UnsupportedOperationException
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
			 // reopened
			 if (c.getCommand() == CommandValue.REOPEN) {
				 // if enhancement w/ owner
				 if((issueType == IssueType.ENHANCEMENT) && !(owner == null || "".equals(owner))){
					 state = workingState;
				 }
				 // bug, confirmed, w/ owner
				 else if ((issueType == IssueType.BUG) && isConfirmed() && !(owner == null || "".equals(owner))) {
					 state = workingState;
				 }
				 // bug, confirmed, no owner
				 else if ((issueType == IssueType.BUG) && isConfirmed() && (owner == null || "".equals(owner))){
					 state = confirmedState;
				 // otherwise reopen as new
				 } else {
					 state = newState;
				 }
			 }
			 addNote(c.getNote());
		 }
		 
		 /**
		  * Provides the state name Closed
		  * @return Closed as a String
		  */
		 @Override 
		 public String getStateName() {
			 return "Closed";
		 }
		 
	 }


}


