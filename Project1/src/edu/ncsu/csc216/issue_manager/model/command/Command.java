package edu.ncsu.csc216.issue_manager.model.command;

/**
 * Command encapsulates information about a user command that leads to a state transition. 
 * Has two inner enumerations ... CommandValue and Resolution. 
 * @author Alexander May
 *
 */
public class Command {
	/**
	 * Contains the possible command values that a user may attempt to make
	 * @author Alexander May
	 *
	 */
	public enum CommandValue { ASSIGN, CONFIRM, RESOLVE, VERIFY, REOPEN }

	/**
	 * Contains the possible resolutions to a given issue in the closed state
	 * @author Alexander May
	 *
	 */
	public enum Resolution { FIXED, DUPLICATE, WONTFIX, WORKSFORME }

	// A constant string for the “Fixed” resolution.
	public static final String R_FIXED = "Fixed";
	// A constant string for the “Duplicate” resolution.
	public static final String R_DUPLICATE = "Duplicate";
	// A constant string for the “WontFix” resolution.
	public static final String R_WONTFIX = "WontFix";
	// A constant string for the “WorksForMe” resolution.
	public static final String R_WORKSFORME = "WorksForMe"; 
	// the id of the owner of the issue
	private String ownerId; 
	// the note attached to a given issue
	private String note; 
	
	/*
	 * The constructor for the command 
	 * @param cv        the CommandValue from the enumerations in CommandValue
	 * @param ownerId   the owner ID of the given issue
	 * @param note      the note for the given issue
	 */
	public Command(CommandValue cv, String ownerId, Resolution r, String note) {
		this.ownerId = ownerId;
		this.note = note;	
	}
	
	/**
	 * Grabs the CommandValue associated with a user instruction 
	 * @return CommandValue from the enumerations apt for the situation 
	 */
	public CommandValue getCommand() {
		return null; 
	}
	
	/**
	 * Grabs the ID associated with the owner of the issue 
	 * @return ownerId the owner's ID as a string
	 */
	public String getOwnerId() {
		return ownerId; 
	}
	
	/**
	 * Returns one of the enumerations for Resolution that the issue is currently associated with
	 * @return Resolution the resolution associated with the issue
	 */
	public Resolution getResolution() {
		return null; 
	}
	
	/**
	 * Gets the note associated with the current command
	 * @return note a String representing the note for the current Issue and command
	 */
	public String getNote() {
		return note; 
	}
}
