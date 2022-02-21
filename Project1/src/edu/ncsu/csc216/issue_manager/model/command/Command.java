package edu.ncsu.csc216.issue_manager.model.command;

public class Command {
	
	public enum CommandValue { ASSIGN, CONFIRM, RESOLVE, VERIFY, REOPEN }

	public enum Resolution { FIXED, DUPLICATE, WONTFIX, WORKSFORME }

	public static final String R_FIXED = "Fixed";
	public static final String R_DUPLICATE = "Duplicate";
	public static final String R_WONTFIX = "WontFix";
	public static final String R_WORKSFORME = "WorksForMe"; 
	private String ownerId; 
	private String note; 
	
	public Command(CommandValue cv, String ownerId, Resolution r, String note) {
		this.ownerId = ownerId;
		this.note = note;	
	}
	
	public CommandValue getCommand() {
		return null; 
	}
	
	public String getOwnerId() {
		return ownerId; 
	}
	
	public Resolution getResolution() {
		return null; 
	}
	
	public String getNote() {
		return note; 
	}
}
