package mvc;

/**
 * Process the commands
 * @author Quang-Duy Tran
 *
 */

public class CommandProcessor 
{
	/**
	 * Execute the command
	 * @param command the command
	 */
	public static void execute(Command command) 
	{
		command.execute();
	}
}
