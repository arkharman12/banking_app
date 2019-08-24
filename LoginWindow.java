//LoginWindow.java 

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class LoginWindow extends JFrame implements ActionListener {
	
	JPasswordField passF = new JPasswordField("", 20); //password field
	JTextField accountNoT = new JTextField("", 20); //account number field
	JButton loginB = new JButton("Login"), signUpB= new JButton("Create a New Account"); //logina and create a new account buttons
	JLabel imgLabel = new JLabel(new ImageIcon("bank.jpg")); //bank image
	Database db = Database.getInstance();
	JPanel jp = new JPanel();
	JPanel jp2= new JPanel();
	
	//constructor, consists of different GUI components for the login window
	public LoginWindow() {
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				db.saveData();
				System.exit(0);
			}
		});
		db.printAccounts();
		this.setSize(500, 550);
	    this.setVisible(true);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLayout(new GridLayout(1, 1));
	    
	    loginB.addActionListener(this);
	    signUpB.addActionListener(this);
	    
	    this.add(imgLabel); 
	    this.add(jp);
	    
	    jp.setLayout(new GridLayout(3, 1));
	    jp.add(new JLabel());
	    jp.add(jp2);
	    jp.add(new JLabel());
	    jp2.add(new JLabel("Account Number: "));
	    jp2.add(accountNoT);
	    jp2.add(new JLabel("Password: "));
	    jp2.add(passF);
	    jp2.add(loginB);
	    jp2.add(new JLabel("Or"));
	    jp2.add(signUpB);
	    this.revalidate();
	} //end constructor 
	
	//main method, reponsible for running the program
	public static void main(String args[]) { 
		
		Database.getInstance().loadData();
		new LoginWindow();
	}

	//actionPerformed method
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Login")) { //if the user clicks on "Login", get their account info
			Account ac;
			if((ac=db.getAccount(accountNoT.getText(), passF.getText()))!=null) { //if account number and password matches, log them in
				this.dispose();
				new AccountDashboard(ac);
			}
			else { //else block them out and print the eroor message
				JOptionPane.showMessageDialog(this,"Account Number & Password didn't Match!","Login Failed",JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(e.getActionCommand().equals("Create a New Account")) { //else if sign up for a new account
			this.dispose();
			new SignUpWindow();
		}
	} //end actionPerformed

} //end LoginWindow 
