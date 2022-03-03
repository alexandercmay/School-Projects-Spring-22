package edu.ncsu.csc216.issue_manager.model.command;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.issue_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.issue_manager.model.command.Command.Resolution;

/**
 * The test file to ensure the proper function of Command.
 * @author Alexander May
 *
 */
class CommandTest {

	/** 
	 * Test that a valid command does not throw an exception upon construction and its getters
	 */
	@Test
	public void testValidCommand() {
		Command command  = assertDoesNotThrow(
				() -> new Command(CommandValue.CONFIRM, "alex", Resolution.DUPLICATE, "duplicate bug"),
				"should not throw exception"); 
		assertSame(command.getCommand(), CommandValue.CONFIRM);
		assertSame(command.getResolution(), Resolution.DUPLICATE);
		assertTrue("alex".equals(command.getOwnerId())); 
		assertTrue("duplicate bug".equals(command.getNote()));
	}
	
	/**
	 * Test error handling with the constructor for command
	 */
	@Test
	public void testInvalidCommands() {
		// test when cv is null
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> new Command(null, "alex", Resolution.DUPLICATE, "duplicate bug"));
		assertEquals("Invalid information.", e1.getMessage());
		
		// test when cv is ASSIGN and there is no owner id 
		Exception e2 = assertThrows(IllegalArgumentException.class, 
				() -> new Command(CommandValue.ASSIGN, null, Resolution.DUPLICATE, "duplicate bug"));
		assertEquals("Invalid information.", e2.getMessage());
		
		// test when cv is RESOLVE and resolution is null
		Exception e3 = assertThrows(IllegalArgumentException.class, 
				() -> new Command(CommandValue.RESOLVE, "alex", null, "duplicate bug"));
		assertEquals("Invalid information.", e3.getMessage());
		
		// test when there is no note
		Exception e4 = assertThrows(IllegalArgumentException.class, 
				() -> new Command(CommandValue.RESOLVE, "alex", Resolution.WONTFIX, null));
		assertEquals("Invalid information.", e4.getMessage());
		
	}

}
