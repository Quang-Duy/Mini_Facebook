package mvc;

/**
 * Models all the buttons
 * @author Quang-Duy Tran
 *
 */

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.*;
import javax.swing.*;

public class AppPanel extends JPanel implements PropertyChangeListener, ActionListener  {

	protected static Model model;
	protected AppFactory factory;
	protected Set<View> views;
	protected JFrame frame;
	public static int FRAME_WIDTH = 400;
	public static int FRAME_HEIGHT = 500;

	/**
	 * Default constructor
	 * Create the GUI
	 * @param factory the factory
	 */
	public AppPanel(AppFactory factory, String name) {
		super();
		this.factory = factory;
		model = factory.makeModel();
		views = new HashSet<View>();
		if (model != null) model.addPropertyChangeListener(this);

		frame = new JFrame();
		Container cp = frame.getContentPane();
		cp.add(this);
		frame.setJMenuBar(createMenuBar());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(name + factory.getTitle());
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}

	/**
	 * Add the view to the panel
	 * @param view the drawing
	 */
	public void addView(View view) { views.add(view); }

	/**
	 * Add the component
	 * @param c the drawing component
	 */
	@Override
	public Component add(Component c) {
		if ( c instanceof View ) addView((View)c);
		return super.add(c);
	}

	/**
	 * Display the view on the panel
	 */
	public void display() { frame.setVisible(true); }

	/**
	 * Do nothing, not needed for this assignment
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		/* override in extensions if needed */
	}

	/****** Getter function ******/
	public Model getModel() { return model; }

	// called by file/open and file/new
	public void setModel(Model newModel) {
		this.model.removePropertyChangeListener(this);
		this.model = newModel;
		this.model.initSupport();
		this.model.addPropertyChangeListener(this);
		for(View view: views) view.setModel(this.model);
		//alternatively: this.model.copy(model);
	}

	/**
	 * Create a menu bar
	 * @return the menu bar
	 */
	protected JMenuBar createMenuBar() {
		JMenuBar result = new JMenuBar();
		// add file, edit, and help menus
		JMenu fileMenu =
			Utilities.makeMenu("File", new String[] {"New",  "Save", "SaveAs", "Open", "Quit"}, this);
		result.add(fileMenu);

		JMenu editMenu =
		    Utilities.makeMenu("Edit", factory.getEditCommands(), this);
		result.add(editMenu);

		JMenu helpMenu =
		    Utilities.makeMenu("Help", new String[] {"About", "Help"}, this);
		result.add(helpMenu);

		return result;
	}

	/**
	 * Perform the action
	 * @param ae the action 
	 */
	public void actionPerformed(ActionEvent ae) {
		String cmmd = ae.getActionCommand();

		if (cmmd == "Save") {
			Utilities.save(model, false);
		} else if (cmmd == "SaveAs") {
			Utilities.save(model, true);
		} else if (cmmd == "Open") {
			Model newModel = Utilities.open(model);
			if (newModel != null) setModel(newModel);
		} else if (cmmd == "New") {
			Utilities.saveChanges(model);
			setModel(factory.makeModel());
			// needed cuz setModel sets to true:
			model.setUnsavedChanges(false);
		} else if (cmmd == "Quit") {
			Utilities.saveChanges(model);
			System.exit(1);
		} else if (cmmd == "About") {
			Utilities.inform(factory.about());
		} else if (cmmd == "Help") {
			Utilities.inform(factory.getHelp());
		} else {
			Command command = factory.makeEditCommand(model, cmmd);
			CommandProcessor.execute(command);
		}
	}
}
