package Facebook;

/**
 * Control the Remove button
 * @author Quang-Duy Tran
 *
 */

import mvc.*;

public class RemoveCommand extends Command {
	
	/**
	 * Default constructor
	 * Set the model to model
	 * @param model the model
	 */
	public RemoveCommand(Model model) { super(model); }
	
	/**
	 * Remove a friend
	 */
	public void execute() {
		Facebook facebook = (Facebook) model;
		
		try {
		String response = Utilities.ask("Remove a friend:");
		facebook.removeFriend(response);
		} catch (Exception e) {
			Utilities.error("Friend list is empty!");
		}
	}
}
