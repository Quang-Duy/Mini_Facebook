package Facebook;

/**
 * Control the Sign Out button
 * @author Quang-Duy Tran
 *
 */

import mvc.*;

public class SignOutCommand extends Command {
	
	/**
	 * Default constructor
	 * Set the model to model
	 * @param model the model
	 */
	public SignOutCommand(Model model) { super(model); }
	
	/**
	 * Sign out from facebook by calling signOut method
	 */
	public void execute() {
		Facebook facebook = (Facebook) model;
		
		facebook.signOut();
	}
}
