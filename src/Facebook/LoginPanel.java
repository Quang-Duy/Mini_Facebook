package Facebook;

/**
 * Models a login GUI
 * @author Quang-Duy Tran
 *
 */

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoginPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static Integer FRAME_WIDTH = 400;
	public static Integer FRAME_HEIGHT = 60;
	
	private JFrame frame;
	
	/**
	 * Create a Login GUI and prompt the user to choose any account in the system (hash table) to sign in
	 */
	public LoginPanel()
	{
		this.frame = new JFrame();
		this.frame.setTitle("Welcome to Facebook");
		this.frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.frame.setLocation(430, 100);
	    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.frame.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,3));
		
		JLabel instruction = new JLabel("Select an account:");
		panel.add(instruction, "West");
		
		String[] choices = Facebook.NAMES.split("\\s+");
		
		JComboBox<String> box = new JComboBox<>(choices);
		panel.add(box, "Center");
		
		JButton button = new JButton("OK");
		panel.add(button, "East");
		
		this.frame.add(panel);
		this.frame.setVisible(true);
		
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				frame.setVisible(false);

				String response = (String) box.getSelectedItem();
				
				FacebookPanel.newScreen(response);
			}
		}
		);
		
	}
	
	/**
	 * Create a new login panel
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		LoginPanel panel = new LoginPanel();
	}
}
