package Facebook;

/**
 * Control the Check button
 * @author Quang-Duy Tran
 *
 */

import mvc.*;

public class CheckCommand extends Command {
	
	/**
	 * Default constructor
	 * Set the model to model
	 * @param model the model
	 */
	public CheckCommand(Model model) { super(model); }
	
	/**
	 * Check if two people are friend by prompting user to input 2 friends and call checkIfFriend method
	 */
	public void execute() {
		Facebook facebook = (Facebook) model;
		
		String response = Utilities.ask("Check if two people are friend:");
		facebook.checkIfFriend(response);
	}
}
