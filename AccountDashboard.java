//AccountDashboard.java

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.text.DecimalFormat;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class AccountDashboard extends JFrame implements ActionListener {
	Database db = Database.getInstance();
	Account ac;
	
	JLabel welcomeText = new JLabel(); //welcome the user to their account
	
	JTextField accountNoT = new JTextField("", 20); //20 characer long field for account no.
	JTextField amountT = new JTextField("", 10); //10 character long field for amount
	
	JPasswordField oldP= new JPasswordField("", 20); //20 characer long field for old password
	JPasswordField newP= new JPasswordField("", 20); //20 characer long field for new password
	JPasswordField reNewP= new JPasswordField("", 20); //20 characer long field for confirming the new password
	
	JButton transferB= new JButton("Transfer Money"); //button for transfer money
	JButton withdrawB= new JButton("Withdraw Money"); //button for withdraw money
	JButton depositB= new JButton("Deposit Money"); //button for deposit money
	JButton balanceB= new JButton("Balance Check"); //button for balance check
	JButton payBillB= new JButton("Pay Bill"); //button for pay bill
	JButton userDetailB= new JButton("User Detail"); //button for user detail
	JButton changePinB= new JButton("Change PIN"); //button for change pin
	JButton saveB = new JButton("Save"); //button for save
	JButton logoutB = new JButton("Log Out"); //button for log out
	
	JButton transferSB = new JButton("Transfer"); //button for transfer
	JButton withdrawalSB = new JButton("Withdraw"); //button for withdraw
	JButton depositSB = new JButton("Deposit"); //button for deposit
	JButton payBillSB = new JButton("Pay"); //button for pay
	JButton PinB = new JButton("Submit"); //button for submit
	
	//create JPanel containers
	JPanel left = new JPanel();
	JPanel right = new JPanel();
	JPanel top = new JPanel();
	JPanel bottom = new JPanel();
	JPanel center = new JPanel();
	
	//AccountDashboard constructor
	//consist of GUI components that triggers a 
	//certain program action upon clicking the buttons 
	public AccountDashboard(Account ac) {
		this.ac=ac;
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we) {
				db.saveData();
				System.exit(0);
			}
		});
		this.setTitle("Dashboard");
		this.setSize(700, 400);
	    this.setVisible(true);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    transferSB.addActionListener(this);
	    withdrawalSB.addActionListener(this);
	    payBillSB.addActionListener(this);
	    depositSB.addActionListener(this);
	    saveB.addActionListener(this);
	    PinB.addActionListener(this);
	    
	    this.add(left,BorderLayout.WEST);
	    this.add(right,BorderLayout.EAST);
	    this.add(top,BorderLayout.NORTH);
	    this.add(bottom,BorderLayout.SOUTH);
	    this.add(center,BorderLayout.CENTER);
	    
	    top.setBorder(new EmptyBorder(10, 0, 10, 0));
	    welcomeText.setText("Welcome, " + ac.user.firstName + " " + ac.user.lastName); //welcome the user 
	    top.add(welcomeText);
	    
	    left.setLayout(new GridLayout(7, 1, 5, 10));
	    transferB.addActionListener(this);
	    left.add(transferB);
	    withdrawB.addActionListener(this);
	    left.add(withdrawB);
	    depositB.addActionListener(this);
	    left.add(depositB);
	    balanceB.addActionListener(this);
	    left.add(balanceB);
	    payBillB.addActionListener(this);
	    left.add(payBillB);
	    
	    right.setLayout(new GridLayout(7, 1,5,10));
	    userDetailB.addActionListener(this);
	    right.add(userDetailB);
	    changePinB.addActionListener(this);
	    right.add(changePinB);
	    
	    logoutB.addActionListener(this);
	    bottom.setLayout(new FlowLayout(FlowLayout.RIGHT));
	    bottom.add(logoutB);
	    
	} //end constructor

	//actionPerformed method is responsible for starting other methods if certain button is clicked 
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Transfer Money")) {
			generateTransferPanel(); //trigger the generateTransferPanel method 
		}
		else if(e.getActionCommand().equals("Withdraw Money")) {
			generateWithdrawalPanel(); //trigger the generateWithdrawalPanel method
		}
		else if(e.getActionCommand().equals("Deposit Money")) {
			generateDepositPanel(); // trigger the generateDepositPanel method
		}
		else if(e.getActionCommand().equals("Balance Check")) {
			generateBalancePanel(); //so on
		}
		else if(e.getActionCommand().equals("Pay Bill")) {
			generatePayBillPanel(); //so on...
		}
		else if(e.getActionCommand().equals("Transfer")) {
			transfer(); //and so on...
		}
		else if(e.getActionCommand().equals("Withdraw")) {
			withdraw();
		}
		else if(e.getActionCommand().equals("Deposit")) {
			deposit();	
		}
		else if(e.getActionCommand().equals("Pay")) {
			payBill();
		}
		else if(e.getActionCommand().equals("User Detail")) {
			generateUserDetailPanel();
		}
		else if(e.getActionCommand().equals("Change PIN")) {
			generatePinPanel();
		}
		else if(e.getActionCommand().equals("Submit")) {
			changePIN();
		}
		else if(e.getActionCommand().equals("Log Out")) {
			this.dispose();
			new LoginWindow(); //send the user back to the login window
		}

	} //end actionPerformed
	
	//this method is responsible for generating transfer money panel
	void generateTransferPanel() {
		panelClear();
		center.add(new JLabel("Enter Account: "));
		center.add(accountNoT);
		center.add(new JLabel("Enter Amount: "));
		center.add(amountT);
		center.add(new JLabel());
		center.add(transferSB);
		this.revalidate();
	} //end generateTransferPanel
	
	//this method is responsible for generating withdrawal panel
	void generateWithdrawalPanel() {
		panelClear();
		center.add(new JLabel("Enter Amount: "));
		center.add(amountT);
		center.add(new JLabel());
		center.add(withdrawalSB);
		this.revalidate();
	} //end generateWithdrawalPanel
	
	//this method is responsible for generating depositing money panel
	void generateDepositPanel() {
		panelClear();
		center.add(new JLabel("Enter Amount: "));
		center.add(amountT);
		center.add(new JLabel());
		center.add(depositSB);
		this.revalidate();
	} //end generateDepositPanel
	
	//this method is responsible for showing the balance in an account
	void generateBalancePanel() {
		panelClear();
		center.add(new JLabel("Current Balance: "));
		DecimalFormat df = new DecimalFormat("0.00"); //end the balance amount in decimal format
		center.add(new JLabel(""+df.format(ac.getBalance())));
		center.add(new JLabel());
		center.add(new JLabel());
		this.revalidate();
	} //end generateBalancePanel
	
	//this method is reponsible for generating paying the bill panel
	void generatePayBillPanel() {
		panelClear();
		center.add(new JLabel("Enter ID/BillNo: "));
		center.add(accountNoT);
		center.add(new JLabel("Enter Amount: "));
		center.add(amountT);
		center.add(new JLabel());
		center.add(payBillSB);
		this.revalidate();
	} //end generatePayBillPanel
	
	//this method is responsible for generating user detail panel
	void generateUserDetailPanel() {
		panelClear();
		center.setBorder(new EmptyBorder(50, 50, 50,50));
		center.setLayout(new GridLayout(0, 2, 5, 10));
		center.add(new JLabel("Full Name:"));
		center.add(new JLabel(ac.user.firstName + " " + ac.user.lastName));
		center.add(new JLabel("Sex:"));
		center.add(new JLabel(ac.user.gender));
		center.add(new JLabel("Date of Birth:"));
		center.add(new JLabel(ac.user.birthdate.getDate()+"/"+ac.user.birthdate.getMonth()+"/"+ac.user.birthdate.getYear()));
		center.add(new JLabel("Email:"));
		center.add(new JLabel(ac.user.email));
		center.add(new JLabel("Phone No.:"));
		center.add(new JLabel(ac.user.phoneNo));
		center.add(new JLabel("SSN:"));
		center.add(new JLabel(ac.user.SSN));
		center.add(new JLabel("Address:"));
		center.add(new JLabel(ac.user.address));
		center.add(new JLabel("Occupation:"));
		center.add(new JLabel(ac.user.occupation));
		this.revalidate();	
	} //end generateUserDetailPanel
	
	//this method is responsible for generating the changing the pin panel
	void generatePinPanel() {
		panelClear();
		center.setBorder(new EmptyBorder(80, 50, 70,50));
		center.setLayout(new GridLayout(4, 2, 5, 10));
		center.add(new JLabel("Old PIN:"));
		center.add(oldP);
		center.add(new JLabel("Enter New PIN:"));
		center.add(newP);
		center.add(new JLabel("Re-Enter New PIN:"));
		center.add(reNewP);
		center.add(new JLabel(" "));
		center.add(PinB);
		this.revalidate();	
	} //end generatePinPanel
	
	//this method is responsible for clearing everything out when the user switch windows in the program
	void panelClear() {
		center.removeAll();
		center.setBorder(new EmptyBorder(80, 50, 110,50));
		center.setLayout(new GridLayout(3, 2, 5, 10));
		accountNoT.setText("");
		amountT.setText("");
		oldP.setText("");
		newP.setText("");
		reNewP.setText("");
	} //end panelClear
	
	//this method is responsible for showing window alerts when paying bill
	void payBill() {
		try {
			double amount=Double.parseDouble(amountT.getText());
			if(ac.payBill(amount)) { //if the account has sufficient amount, pay the bill
				JOptionPane.showMessageDialog(this,"Bill successfully Paid!","Success",JOptionPane.OK_OPTION);
			}
			else { //else don't pay the bill and show an error window message
				JOptionPane.showMessageDialog(this,"You don't have enough Balance","Failed",JOptionPane.ERROR_MESSAGE);
			}	
		} //end try
		catch(NumberFormatException ex) { //throw the exception
			JOptionPane.showMessageDialog(this,"Enter valid amount","Failed",JOptionPane.ERROR_MESSAGE);
		} //end catch

	} //end payBIll
	
	//this method is responsible for showing window alerts when tranferring money between accounts
	void transfer() {
		try {
			double amount=Double.parseDouble(amountT.getText());
			Account otherAccount;
			if((otherAccount=db.getAccount(accountNoT.getText()))!=null) { //check if the user enter valid account number
				if(ac.transferMoney(otherAccount, amount)) { //if the account has sufficient amount, transfer the money
					JOptionPane.showMessageDialog(this,"Successfully Transferred","Success",JOptionPane.OK_OPTION);
				}
				else { //else throw an error message
					JOptionPane.showMessageDialog(this,"You don't have enough Balance","Failed",JOptionPane.ERROR_MESSAGE);
				}
			}
			else { //else throw an error message that account doesn't exist
				JOptionPane.showMessageDialog(this,"Sorry, Given Account not Found","Failed",JOptionPane.ERROR_MESSAGE);
			}	
		} //end try

		catch(NumberFormatException ex) { //throw an exception message
			JOptionPane.showMessageDialog(this,"Enter valid amount","Failed",JOptionPane.ERROR_MESSAGE);
		} //end catch

	} //end transfer
	
	//this method is responsible for showing window alerts when withdrawing money from the account
	void withdraw() {
		try {
			double amount=Double.parseDouble(amountT.getText());
			int t=ac.withdrawMoney(amount);
			if(t==0)
				JOptionPane.showMessageDialog(this,"Successfully Withdrawn","Success",JOptionPane.OK_OPTION);
			else if(t==Account.INSUFFICIENT_BALANCE)
				JOptionPane.showMessageDialog(this,"You don't have enough Balance","Failed",JOptionPane.ERROR_MESSAGE);
			else if(t==Account.WITHDRAWAL_LIMIT_UNDER)
				JOptionPane.showMessageDialog(this,"Minimum withdrawal amount is: "+ ac.minWithdrawal,"Failed",JOptionPane.ERROR_MESSAGE);
			else if(t==Account.WITHDRAWAL_LIMIT_OVER)
				JOptionPane.showMessageDialog(this,"Maximum Withdrawal amount is: " +ac.maxWithdrawal,"Failed",JOptionPane.ERROR_MESSAGE);
		} //end try

		catch(NumberFormatException ex) {
			JOptionPane.showMessageDialog(this,"Enter valid amount","Failed",JOptionPane.ERROR_MESSAGE);
		} //end catch

	} //end withdraw
	
	//method for showing window alerts when depositting money
	void deposit() {
		try {
			double amount=Double.parseDouble(amountT.getText());
			ac.depositMoney(amount);
			JOptionPane.showMessageDialog(this,"Successfully Deposited","Success",JOptionPane.OK_OPTION);
		} //end try

		catch(NumberFormatException ex) {
			JOptionPane.showMessageDialog(this,"Enter valid amount","Failed",JOptionPane.ERROR_MESSAGE);
		} //end catch

	} //end deposit
	
	//method for showing window alerts when changing the pin
	void changePIN() {
		if(oldP.getText().isEmpty() || newP.getText().isEmpty() || reNewP.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this,"Please Fill all the Fields","Failed",JOptionPane.ERROR_MESSAGE);
		}
		else if(!oldP.getText().equals(ac.getPIN())) {
			JOptionPane.showMessageDialog(this,"Wrong PIN Entered","Failed",JOptionPane.ERROR_MESSAGE);
		}
		else if(!newP.getText().equals(reNewP.getText())) {
			JOptionPane.showMessageDialog(this,"New PINs doesn't Match","Failed",JOptionPane.ERROR_MESSAGE);
		}
		else {
			ac.setPIN(newP.getText());
			JOptionPane.showMessageDialog(this,"PIN successfully changed","Success",JOptionPane.OK_OPTION);
		}
	} //end changePIN

} //end AccountDashboard 
