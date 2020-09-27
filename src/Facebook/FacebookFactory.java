package Facebook;

/**
 * Create and set up the menu bar for the GUI
 * @author Quang-Duy Tran
 *
 */

import mvc.*;

public class FacebookFactory implements AppFactory {
	private static Facebook facebook = new Facebook();
	
	/**
	 * Create a new Facebook model
	 */
	public Model makeModel() { return facebook; }

	/**
	 * Create new edit commands 
	 */
	public String[] getEditCommands() { 
		return new String[] {"Add Friend", "Remove Friend", "Search Friend", "Check If Friend", "Binary Search Tree", 
				"Hash Table", "Sign Out"}; 
	}

	/**
	 * Call the correct command based on the user's selection
	 */
	public Command makeEditCommand(Model model, String type) 
	{
		if (type == "Add Friend")
			return new AddCommand(model);
		else if(type == "Remove Friend")
			return new RemoveCommand(model);
		else if(type == "Search Friend")
			return new SearchCommand(model);
		else if(type == "Check If Friend")
			return new CheckCommand(model);
		else if(type == "Binary Search Tree")
			return new BSTCommand(model);
		else if(type == "Hash Table")
			return new HashTableCommand(model);
		else if(type == "Sign Out")
			return new SignOutCommand(model);
		
		return null;
	}

	/**
	 * Show the title of the GUI
	 */
	public String getTitle() { return "'s Facebook"; }

	/**
	 * Show the instruction, how to use the GUI and what the buttons do
	 */
	public String[] getHelp() {
		return new String[] {"Add Friend: Add a new friend", 
				"Remove Friend: Remove a friend", 
				"Search Friend: Look up a person's friend-list",
				"Check If Friend: Check if two people are friended",
				"Binary Search Tree: List a person's friend-list in alphabetical order",
				"Hash Table: Display the hash table in the system",
				"Sign Out: Choose a different account"};
	}

	/**
	 * Show the author and copyright
	 */
	public String about() {
		return "Micro Facebook version 1.0. Copyright 2020 by Quang-Duy Tran";
	}

}