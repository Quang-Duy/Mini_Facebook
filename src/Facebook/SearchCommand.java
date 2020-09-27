package Facebook;

/**
 * Control the Search button
 * @author Quang-Duy Tran
 *
 */

import mvc.*;

public class SearchCommand extends Command {
	
	/**
	 * Default constructor
	 * Set the model to model
	 * @param model the model
	 */
	public SearchCommand(Model model) { super(model); }
	
	/**
	 * Look up a friend's friend list by calling search method
	 */
	public void execute() {
		Facebook facebook = (Facebook) model;
		
		String response = Utilities.ask("Look up a friend:");
		facebook.search(response);
	}
}
