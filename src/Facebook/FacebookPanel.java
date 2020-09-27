package Facebook;

/**
 * Create the buttons for the GUI
 * @author Quang-Duy Tran
 *
 */

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mvc.*;

public class FacebookPanel extends AppPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton add, remove, search, check, bst, hashtable, signOut;
	private List friendList = new List();
	
	/**
	 * Default constructor
	 * Create all buttons and set the layout of them on the GUI
	 * @param factory the interface AppFactory (from mvc package)
	 */
	public FacebookPanel(AppFactory factory, String response) {
		super(factory, response);
		
		Facebook facebook = (Facebook)model; //Call property change
		facebook.setAccountOwner(response);
		
		this.setLayout(new GridLayout(1, 2));
		
		this.add = new JButton("Add Friend");
		this.add.addActionListener(this);
		
		this.remove = new JButton("Remove Friend");
		this.remove.addActionListener(this);
		
		this.search = new JButton("Search Friend");
		this.search.addActionListener(this);
		
		this.check = new JButton("Check If Friend");
		this.check.addActionListener(this);
		
		this.bst = new JButton("Binary Search Tree");
		this.bst.addActionListener(this);
		
		this.hashtable = new JButton("Hash Table");
		this.hashtable.addActionListener(this);
		
		this.signOut = new JButton("Sign Out");
		this.signOut.addActionListener(this);
		
		JPanel mathPanel = new JPanel();
		mathPanel.setLayout(new GridLayout(7, 1));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		panel.add(add);
		mathPanel.add(panel);
		
		panel = new JPanel();
		panel.add(remove);
		mathPanel.add(panel);
		
		panel = new JPanel();
		panel.add(search);
		mathPanel.add(panel);
		
		panel = new JPanel();
		panel.add(check);
		mathPanel.add(panel);
		
		panel = new JPanel();
		panel.add(bst);
		mathPanel.add(panel);

		panel = new JPanel();
		panel.add(hashtable);
		mathPanel.add(panel);
		
		panel = new JPanel();
		panel.add(signOut);
		mathPanel.add(panel);
		
		buttonPanel.add(mathPanel, "North");
		this.add(buttonPanel, "West");
		
		//Friend-list display
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new BorderLayout());

		textPanel.add(friendList);
		this.add(textPanel, "East");
		
	}
	
	/**
	 * Display facebook account owner's friend list on the GUI
	 */
	public void propertyChange(PropertyChangeEvent arg0) {
		Facebook facebook = (Facebook)model;
		
		this.friendList.removeAll(); //Avoid duplicate
		
		for(int i = 0; i < facebook.getAccountOwner().getFriendsList().size(); i++) {
			this.friendList.add(facebook.getAccountOwner().getFriendsList().getData(i).getName());
		}
		
		if(!facebook.getVisible()) {
			frame.setVisible(false);
			facebook.setIsVisible(true);
			LoginPanel.main(null); //Return to login account
		}
		
		super.propertyChange(arg0);
	}
	
	/**
	 * Create a facebook GUI
	 * @param response name (account) chosen by a user
	 */
	public static void newScreen(String response) {
		AppFactory factory = new FacebookFactory();
		
		AppPanel panel = new FacebookPanel(factory, response);
		
		panel.display();
	}
}
