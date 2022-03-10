package edu.ncsu.csc216.issue_manager.model.manager;



import edu.ncsu.csc216.issue_manager.model.command.Command;
import edu.ncsu.csc216.issue_manager.model.io.IssueReader;
import edu.ncsu.csc216.issue_manager.model.io.IssueWriter;
import edu.ncsu.csc216.issue_manager.model.issue.Issue;
import edu.ncsu.csc216.issue_manager.model.issue.Issue.IssueType;
/**
 * Implements the singleton design pattern. 
 * Works with the files that contain the saved Issues. 
 * Provides information to the GUI. 
 * Maintains the IssueList and handles GUI commands. 
 * @author Alexander May
 *
 */
public class IssueManager {

	/** The Instance of IssueManager **/
	private static IssueManager instance;

	/** IssueList **/
	private IssueList issueList = new IssueList();

	/**
	 * The constructor for the IssueManager
	 */
	private IssueManager() {
		
	}
	
	/**
	 * Provides the instance of the IssueManager the GUI is currently handling
	 * @return the instanceof IssueManager
	 */
	public static IssueManager getInstance() {
		// if the instance has not yet been instantiated
		if (instance == null) {
			// instantiate instance
			instance = new IssueManager();
		}
		// return instance
		return instance;
	}
	
	/**
	 * Saves the current issues to a file
	 * @param filename the name of the file to save the issues onto
	 * @throws IllegalArgumentException if the file cannot be written
	 */
	public void saveIssuesToFile(String filename) {
		// exception may happen when writing a file
		try {
			// write issues to file
			IssueWriter.writeIssuesToFile(filename, issueList.getIssues());
		}
		catch (Exception e){
			throw new IllegalArgumentException("saveIssuesToFIle exception");
		}
	}
	
	/**
	 * Loads issues from a specified file
	 * @param filename the name of the file to load issues from
	 * @throws IllegalArgumentException if error is caught with the reader
	 */
	public void loadIssuesFromFile(String filename) {
		// exception may happen when processing file
		try {
			// populate issueList with the reader
			issueList.addIssues(IssueReader.readIssuesFromFile(filename));
		}
		catch (Exception e) {
			throw new IllegalArgumentException("loadIssuesFromFile exception");
		}
	}
	
	/**
	 * Updates the global issueList reference to point to a new IssueList object
	 */
	public void createNewIssueList() {
		// set the issueList to empty
		issueList = new IssueList();
	}
	
	/**
	 * Returns the issues where rows are Issues and columns are   0: ID   1: state name  2: type  3: summary
	 * @return return a 2D Object array containing issues
	 */
	public Object[][] getIssueListAsArray(){
		// create an array of objects storing elements of issues
		// instantiate array
		Object[][] issueArray = new Object[issueList.getIssues().size()][4];
		// each row is a new issue, column 0 = id, column 1 = state, column 2 = type, column 3 = summary
		for(int i = 0; i < issueList.getIssues().size(); i++) {
			Issue currentIssue = issueList.getIssues().get(i);
			issueArray[i][0] = currentIssue.getIssueId();
			issueArray[i][1] = currentIssue.getStateName();
			issueArray[i][2] = currentIssue.getIssueType();
			issueArray[i][3] = currentIssue.getSummary();
		}
		return issueArray;
	}
	
	/**
	 * Returns the issues where rows are Issues and columns are   0: ID   1: state name  2: type  3: summary
	 * @param issue the String representing the issue type 
	 * @return return a 2D Object array containing issues of a certain type
	 * @throws IllegalArgumentException if issue is null
	 */
	public Object[][] getIssueListAsArrayByIssueType(String issue){
		// check to ensure issue is not null
		if (issue == null) {
			throw new IllegalArgumentException("Invalid issue");
		}
		// create a counter 
		int typeCounter = 0;
		// find number of issue type
		for (int i = 0; i < issueList.getIssues().size(); i++) {
			if (issueList.getIssues().get(i).getIssueType().equalsIgnoreCase(issue)) {
				typeCounter++;
			}
		}
		// create an array of objects storing elements of issues
		// instantiate array
		Object[][] issueArray = new Object[typeCounter][4];
		
		// keep track of which entry you should be in
		int counter = 0;
		for(int i = 0; i < issueList.getIssues().size(); i++) {
			Issue currentIssue = issueList.getIssues().get(i);
			if(currentIssue.getIssueType().equalsIgnoreCase(issue)) {
				// each row is a new issue, column 0 = id, column 1 = state, column 2 = type, column 3 = summary
				issueArray[counter][0] = currentIssue.getIssueId();
				issueArray[counter][1] = currentIssue.getStateName();
				issueArray[counter][2] = currentIssue.getIssueType();
				issueArray[counter][3] = currentIssue.getSummary();
				counter++;
			}
		}
		return issueArray;
	}
	
	/**
	 * Grabs a specified Issue by ID
	 * @param id the ID that the user or GUI wants to grab
	 * @return the Issue tied to the specified ID
	 */
	public Issue getIssueById(int id) {
		return issueList.getIssueById(id);
	}
	
	/**
	 * Executes a desired command based on the id of the issue and the specified command
	 * @param id        the ID of the issue sought after
	 * @param command   the command the user/GUI wants to execute upon the issue
	 * @throws UnsupportedOperationException if the command cannot be executed due to the issue's state
	 */
	public void executeCommand(int id, Command command) {
		getIssueById(id).update(command);
	}
	
	/**
	 * Deletes a specified issue based on the ID
	 * @param id the ID attached to the issue the user is seeking to delete
	 */
	public void deleteIssueById(int id) {
		issueList.deleteIssueById(id);
		
	}
	
	/**
	 * Adds an issue to the IssueList. 
	 * @param issue    the type of issue that you want to add (eg enhancement, bug)
	 * @param summary  the summary of the issue as a string 
	 * @param note     the note tied to the issue as a string
	 * @throws IllegalArgumentException if the issue is not in a valid state
	 */
	public void addIssueToList(IssueType issue, String summary, String note) {
		issueList.addIssue(issue, summary, note);
	}
 	
}
