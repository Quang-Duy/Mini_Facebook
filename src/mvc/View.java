package mvc;

/**
 * Models the view of the GUI (drawing)
 * Not being used in this assignment because there is no drawing
 * @author Quang-Duy Tran
 *
 */

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.io.*;
import java.beans.*;

public class View extends JComponent implements PropertyChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected Model model;

	public View(Model model) {
		super();
		this.model = model;
		model.addPropertyChangeListener(this);
		//optional border around the view component
		//setBorder(LineBorder.createBlackLineBorder());
	}
	
	public Model getModel() {
		return model;
	}

	// called by File/Open and File/New
	public void setModel(Model newModel) {
		if (this.model != null) this.model.removePropertyChangeListener(this);
		this.model = newModel;
		if (newModel != null) {
			this.model.addPropertyChangeListener(this);
			repaint();
		}
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		repaint();
	}
	
}