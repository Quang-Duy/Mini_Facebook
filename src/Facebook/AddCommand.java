package Facebook;

/**
 * Control the Add button
 * @author Quang-Duy Tran
 *
 */

import mvc.*;

public class AddCommand extends Command {
	
	/**
	 * Default constructor
	 * Set the model to model
	 * @param model the model
	 */
	public AddCommand(Model model) { super(model); }
	
	/**
	 * Add a new friend by prompting user to enter a name and call addFriend function
	 */
	public void execute() {
		Facebook facebook = (Facebook) model;
		
		String response = Utilities.ask("Add new friend:");
		facebook.addFriend(response);
	}
}
