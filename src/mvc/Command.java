package mvc;

/**
 * Abstract class, will be specify in the children classes
 * @author Quang-Duy Tran
 *
 */

public abstract class Command {
	protected Model model;
	
	/**
	 * Default constructor
	 * Set this model to model
	 * @param model the model
	 */
	public Command(Model model) {
		super();
		this.model = model;
	}

	/**
	 * Return the model
	 */
	public String toString() { return "command.model = " + model; }
	
	public abstract void execute();
}
