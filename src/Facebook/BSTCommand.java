package Facebook;

/**
 * Control the Binary Search Tree button
 * @author Quang-Duy Tran
 *
 */

import mvc.*;

public class BSTCommand extends Command {
	
	/**
	 * Default constructor
	 * Set the model to model
	 * @param model the model
	 */
	public BSTCommand(Model model) { super(model); }
	
	/**
	 * Display one's friend list in alphabetical order by calling binarySearchTree function
	 */
	public void execute() {
		Facebook facebook = (Facebook) model;

		facebook.binarySearchTree();
	}
}
