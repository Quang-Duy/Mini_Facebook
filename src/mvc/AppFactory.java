package mvc;

/**
 * Models the menu bar of the GUI
 * @author Quang-Duy Tran
 *
 */

public interface AppFactory {
	public Model makeModel();
	
	public String[] getEditCommands();
	
	public Command makeEditCommand(Model model, String type);
	
	public String getTitle();
	
	public String[] getHelp();
	
	public String about();
}
