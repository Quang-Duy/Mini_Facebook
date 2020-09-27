package Facebook;

/**
 * Control the Hash Table button
 * @author Quang-Duy Tran
 *
 */

import mvc.*;

public class HashTableCommand extends Command {
	
	/**
	 * Default constructor
	 * Set the model to model
	 * @param model the model
	 */
	public HashTableCommand(Model model) { super(model); }
	
	/**
	 * Display hash table by calling displayHashTable method
	 */
	public void execute() {
		Facebook facebook = (Facebook) model;

		facebook.displayHashTable();
	}
}
