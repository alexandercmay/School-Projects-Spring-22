package edu.ncsu.csc216.issue_manager.model.issue;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.issue_manager.model.command.Command;
import edu.ncsu.csc216.issue_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.issue_manager.model.command.Command.Resolution;
import edu.ncsu.csc216.issue_manager.model.io.IssueReader;
import edu.ncsu.csc216.issue_manager.model.issue.Issue.IssueType;

/**
 * The test file to ensure the proper function of Issue
 * @author Alexander May
 *
 */
class IssueTest {
	
	/** Valid course records */
	private final String validTestFile = "test-files/issue_records1.txt";

	/**
	 * Test long constructor invalid inputs
	 */
	@Test
	public void testInvalidLongConstructor() {
		// create a new notes array with a note inside
		ArrayList<String> notes = new ArrayList<String>();
		notes.add("note");
    	// id less than 1
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> new Issue(0, "WORKING", "BUG", "buggy stuff happening",
						"alex", true, "WORKSFORME",notes));
		assertEquals("Issue cannot be created.", e1.getMessage());
		
		// comma in summary text eg too much info
		Exception e2 = assertThrows(IllegalArgumentException.class, 
				() -> new Issue(1, "WORKING", "BUG", "buggy stuff happening, yo",
						"alex", true, "WORKSFORME",notes));
		assertEquals("Issue cannot be created.", e2.getMessage());
		
		// incorrect state name
		Exception e3 = assertThrows(IllegalArgumentException.class, 
				() -> new Issue(1, "WONKING", "BUG", "buggy stuff happening",
						"alex", true, "WORKSFORME",notes));
		assertEquals("Issue cannot be created.", e3.getMessage());
		
		// incorrect type
		Exception e4 = assertThrows(IllegalArgumentException.class, 
				() -> new Issue(1, "WORKING", "BUGGY", "buggy stuff happening",
						"alex", true, "WORKSFORME",notes));
		assertEquals("Issue cannot be created.", e4.getMessage());
		
		// working without owner empty
		Exception e5 = assertThrows(IllegalArgumentException.class, 
				() -> new Issue(1, "WORKING", "BUG", "buggy stuff happening",
						"", true, "WORKSFORME",notes));
		assertEquals("Issue cannot be created.", e5.getMessage());
		
		// working without owner null
		Exception e6 = assertThrows(IllegalArgumentException.class, 
				() -> new Issue(1, "WORKING", "BUG", "buggy stuff happening",
						null, true, "WORKSFORME",notes));
		assertEquals("Issue cannot be created.", e6.getMessage());
		
		// verifying without owner empty
		Exception e7 = assertThrows(IllegalArgumentException.class, 
				() -> new Issue(1, "VERIFYING", "BUG", "buggy stuff happening",
					"", true, "WORKSFORME",notes));
		assertEquals("Issue cannot be created.", e7.getMessage());
		
		// verifying without owner null
		Exception e8 = assertThrows(IllegalArgumentException.class, 
				() -> new Issue(1, "VERIFYING", "BUG", "buggy stuff happening",
						null, true, "WORKSFORME",notes));
		assertEquals("Issue cannot be created.", e8.getMessage());
		
		// verifying no resolution empty 
		Exception e9 = assertThrows(IllegalArgumentException.class, 
				() -> new Issue(1, "VERIFYING", "BUG", "buggy stuff happening",
						"alex", true, "",notes));
		assertEquals("Issue cannot be created.", e9.getMessage());
		
		// verifying no resolution null 
		Exception e10 = assertThrows(IllegalArgumentException.class, 
				() -> new Issue(1, "VERIFYING", "BUG", "buggy stuff happening",
						"alex", true, null, notes));
		assertEquals("Issue cannot be created.", e10.getMessage());
	
		// closed no resolution empty 
		Exception e11 = assertThrows(IllegalArgumentException.class, 
				() -> new Issue(1, "CLOSED", "BUG", "buggy stuff happening",
						"alex", true, "",notes));
		assertEquals("Issue cannot be created.", e11.getMessage());
		
    	// closed no resolution null 
		Exception e12 = assertThrows(IllegalArgumentException.class, 
				() -> new Issue(1, "CLOSED", "BUG", "buggy stuff happening",
						"alex", true, null,notes));
		assertEquals("Issue cannot be created.", e12.getMessage());
		
		// enhancement in confirmed state
		Exception e13 = assertThrows(IllegalArgumentException.class, 
				() -> new Issue(1, "CONFIRMED", "ENHANCEMENT", "please enchant this enhancement",
						"alex", false, "WORKSFORME",notes));
		assertEquals("Issue cannot be created.", e13.getMessage());
		
		// bug in working state not confirmed
		Exception e14 = assertThrows(IllegalArgumentException.class, 
				() -> new Issue(1, "WORKING", "BUG", "buggy stuff happening",
						"alex", false, "WORKSFORME",notes));
		assertEquals("Issue cannot be created.", e14.getMessage());
		
		// verifying not fixed
		Exception e15 = assertThrows(IllegalArgumentException.class, 
				() -> new Issue(1, "VERIFYING", "BUG", "buggy stuff happening",
						"alex", true, "WORKSFORME", notes));
		assertEquals("Issue cannot be created.", e15.getMessage());
		
		// enhancement with confirmed boolean
		Exception e16 = assertThrows(IllegalArgumentException.class, 
				() -> new Issue(1, "WORKING", "ENHANCEMENT", "please enchant this enhancement",
						"alex", true, "WORKSFORME",notes));
		assertEquals("Issue cannot be created.", e16.getMessage());
		
		// no empty notes list
		notes.clear();
		Exception e17 = assertThrows(IllegalArgumentException.class, 
				() -> new Issue(1, "WORKING", "BUG", "buggy stuff happening",
						"alex", true, "WORKSFORME",notes));
		assertEquals("Issue cannot be created.", e17.getMessage());
		
	}
	
	/**
	 * Test short parameter constructor invalid inputs
	 */
	@Test
	public void testInvalidShortConstructor() {
		
		// issue invalid id
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> new Issue(0,IssueType.BUG, "buggy stuff happening",
						"an international spy bugs your apartment!"));
		assertEquals("Issue cannot be created.", e1.getMessage());
		
		// issue invalid type
		Exception e2 = assertThrows(IllegalArgumentException.class, 
				() -> new Issue(1,null, "buggy stuff happening",
						"an international spy bugs your apartment!"));
		assertEquals("Issue cannot be created.", e2.getMessage());
		
		// issue invalid summary
		Exception e3 = assertThrows(IllegalArgumentException.class, 
				() -> new Issue(1,IssueType.BUG, "",
						"an international spy bugs your apartment!"));
		assertEquals("Issue cannot be created.", e3.getMessage());
		
		// issue invalid note
		Exception e4 = assertThrows(IllegalArgumentException.class, 
				() -> new Issue(1,IssueType.BUG, "buggy stuff happening",
						""));
		assertEquals("Issue cannot be created.", e4.getMessage());
		
		
	}
	
	/**
	 * Test long constructor with valid inputs 
	 */
	@Test
	public void testValidLongConstructor() {
		// create a new notes array with a note inside
		ArrayList<String> notes = new ArrayList<String>();
		notes.add("note");
		
		// issue valid
		assertDoesNotThrow(
				() -> new Issue(3, "VERIFYING", "BUG", "its buggin yo", "alex", true, "FIXED", notes));
		
		// issue valid
		assertDoesNotThrow(
				() -> new Issue(1, "NEW", "ENHANCEMENT", "its enchanted yo", "", false, "", notes)); 
		
		// issue valid
		assertDoesNotThrow(
				() -> new Issue(80, "VERIFYING", "BUG", "its buggin yo", "alex", true, "FIXED", notes)); 
		
		// issue valid
		assertDoesNotThrow(
				() -> new Issue(6, "CLOSED", "ENHANCEMENT", "its enchanted yo", "alex", false, "FIXED", notes));
		
		// issue valid 
		assertDoesNotThrow(
				() -> new Issue(7, "CONFIRMED", "BUG", "its buggin yo", "", true, "DUPLICATE", notes));
					
		
	}
	
	
	/**
	 * Test short constructor with valid inputs
	 */
	@Test
	public void testValidShortConstructor() {
		
		// issue valid 
		Issue issue = assertDoesNotThrow(
				() -> new Issue(1, IssueType.BUG, "bugs or humans", "bugs and humans are the same"));
		assertEquals(issue.getIssueId(), 1);
		
		
	}
	
	/**
	 * Tests the toString
	 */
	@Test
	public void testToString() {
		String exToString = "* 1,New,Enhancement,enhance image!,,false,\n-[New] Note 1\n";
		ArrayList<String> notes = new ArrayList<String>();
		notes.add("[New] Note 1");
		Issue issue = new Issue(1, "NEW", "Enhancement", "enhance image!", "", false,"",notes);
		assertEquals(exToString, issue.toString());
	}
	
	/**
	 * Test the new state
	 */
	@Test
	public void testNewState() {
		ArrayList<String> notes = new ArrayList<String>();
		notes.add("[New] Note 1");
		// create a bug in the new state
		Issue issue1 = new Issue (1, "NEW", "BUG", "this is a buglem", "", false, "", notes);
		// create an enhancement in the  new state
		Issue issue2 = new Issue (1, "NEW", "ENHANCEMENT", "this is enchanted", "", false, "", notes);
		
		// move the issue (bug) to confirmed
		Command command1 = new Command(CommandValue.CONFIRM, "", null, "yeah its a bug");
		issue1.update(command1);
		// ensure bug's state is now confirmed
		assertTrue(issue1.getStateName().equalsIgnoreCase("CONFIRMED"));
		// cant confirm enhancement
		assertThrows(UnsupportedOperationException.class, () -> issue2.update(command1));
		
		// resolve command
		Command command2 = new Command(CommandValue.RESOLVE, "", Resolution.WONTFIX, " will not fix");
		// create a bug in the new state
		Issue issue3 = new Issue (1, "NEW", "BUG", "this is a buglem", "", false, "", notes);
		// wontfix a new bug
		issue3.update(command2);
		assertTrue(issue3.getStateName().equalsIgnoreCase("CLOSED"));
		// worksforme a new enhancement should fail
		Command command3 = new Command(CommandValue.RESOLVE, "", Resolution.WORKSFORME, "works for me");
		assertThrows(UnsupportedOperationException.class, () -> issue2.update(command3));	
		
	}
	
	/**
	 * Test the working state
	 */
	@Test
	public void testWorkingState() {
		// create a new note in an array
		ArrayList<String> notes = new ArrayList<String>();
		notes.add("[New] Note 1");
		
		// new bug in working state
		Issue issue1 = new Issue (1, "WORKING", "BUG", "this is a buglem", "alex", true, "", notes);
		// move the issue (bug) to verifying
		Command command1 = new Command(CommandValue.RESOLVE, "alex", Resolution.FIXED, "yeah its fixed");
		issue1.update(command1);
		assertTrue(issue1.getStateName().equalsIgnoreCase("VERIFYING"));
		
		// new bug in working state
		Issue issue2 = new Issue (1, "WORKING", "BUG", "this is a buglem", "alex", true, "", notes);
		
		// move the issue (bug) to verifying
		Command command2 = new Command(CommandValue.RESOLVE, "alex", Resolution.WORKSFORME, "yeah its fixed");
		issue2.update(command2);
		assertTrue(issue2.getStateName().equals("closed"));
		
		// throw exception when enhancement is worksforme
		// new bug in working state
		Issue issue3 = new Issue (1, "WORKING", "ENHANCEMENT", "this is a buglem", "alex", true, "", notes);
		
		// move the issue (enhancement) to verifying
		Command command3 = new Command(CommandValue.RESOLVE, "alex", Resolution.WORKSFORME, "yeah its fixed");
		assertThrows(UnsupportedOperationException.class, () -> issue3.update(command3));
		
		
	}
	
	/**
	 * Test the confirmed state
	 */
	@Test
	public void testConfirmedgState() {
		// create a new note in an array
		ArrayList<String> notes = new ArrayList<String>();
		notes.add("[New] Note 1");
		
		// new bug in confirmed state
		Issue issue1 = new Issue (1, "CONFIRMED", "BUG", "this is a buglem", "", true, "", notes);
		// command to assign an owner
		Command command1 = new Command(CommandValue.ASSIGN, "alex", null, "yeah this is an issue");
		issue1.update(command1);
		assertTrue(issue1.getStateName().equalsIgnoreCase("working"));
		
		// resolve wont fix
		// new bug in confirmed state
		Issue issue2 = new Issue (1, "CONFIRMED", "BUG", "this is a buglem", "", true, "", notes);
		Command command2 = new Command(CommandValue.RESOLVE, "", Resolution.WONTFIX, "wont fix");
		issue2.update(command2);
		assertTrue(issue2.getStateName().equalsIgnoreCase("closed"));
	}
	
	/**
	 * Test verifying state
	 */
	@Test
	public void testVerifyingState() {
		// create a new note in an array
		ArrayList<String> notes = new ArrayList<String>();
		notes.add("[New] Note 1");
		
		// new bug in verifying state
		Issue issue1 = new Issue (1, "VERIFYING", "BUG", "this is a buglem", "alex", true, "FIXED", notes);
		// command to verify
		Command command1 = new Command(CommandValue.VERIFY, "alex", null, "yeah this is fixed");
		issue1.update(command1);
		assertTrue(issue1.getStateName().equalsIgnoreCase("CLOSED"));
		
		// new bug in verifying state
		Issue issue2 = new Issue (1, "VERIFYING", "BUG", "this is a buglem", "alex", true, "FIXED", notes);
		// command to verify
		Command command2 = new Command(CommandValue.REOPEN, "alex", null, "yeah this isnt fixed");
		issue2.update(command2);
		assertTrue(issue2.getStateName().equalsIgnoreCase("working"));	
		}
	
	/**
	 * Test closed state
	 */
	@Test
	public void testClosedState() {
		// create a new note in an array
		ArrayList<String> notes = new ArrayList<String>();
		notes.add("[New] Note 1");
		
		
		// create enhancement no owner
		Issue issue1 = new Issue(1, "CLOSED", "ENHANCEMENT", "enchanted", "alex", false, "FIXED", notes);
		Command command1 = new Command(CommandValue.REOPEN, "alex", null, "reopen it");
		issue1.update(command1);
		assertTrue(issue1.getStateName().equalsIgnoreCase("working"));
		
		//bug confirmed with owner
		Issue issue2 = new Issue (1, "CLOSED", "BUG", "this is a buglem", "alex", true, "FIXED", notes);
		// command to verify
		Command command2 = new Command(CommandValue.REOPEN, "alex", null, "yeah this isnt fixed");
		issue2.update(command2);
		assertTrue(issue2.getStateName().equalsIgnoreCase("working"));
		
		// bug confirmed no owner
		Issue issue3 = new Issue (1, "CLOSED", "BUG", "this is a buglem", "", true, "FIXED", notes);
		// command to verify
		Command command3 = new Command(CommandValue.REOPEN, "", null, "yeah this isnt fixed");
		issue3.update(command3);
		assertTrue(issue3.getStateName().equalsIgnoreCase("confirmed"));
	}
	
}
