//Account.java 

import java.util.Random;

public abstract class Account {
	public static final int SAVINGS_ACCOUNT=1;
	public static final int CURRENT_ACCOUNT=2;
	
	public static final int INSUFFICIENT_BALANCE=1;
	public static final int WITHDRAWAL_LIMIT_UNDER=2;
	public static final int WITHDRAWAL_LIMIT_OVER=3;
	
	private String accountNo; //private unique account number variable
	private String password; //private unique acccount password variable
	
	protected double balance; //holds the balance 
	protected double minBalance; //holds the minimum balance 
	protected double minWithdrawal; //holds the minimum withdrawl 
	protected double maxWithdrawal; //holds the maximum withdrawl 
	
	protected UserInformation user; //user info variable
	
	boolean isActivated;

	//constructor
	Account(UserInformation u) {
		accountNo=generateUniqueAccountNumber();
		password=generatePIN();
		user=u;
	} //end Account
	
	abstract void setMinBalance(double a);
	abstract void setWithdrawalLimit(double l, double h);
	abstract int getAccountType();
	
	//getter for balance
	double getBalance() {
		return balance;
	}
	//setter for balance
	void setBalance(double b) {
		balance=b;
	}
	//getter for account number
	String getAccuntNo() {
		return accountNo;
	}
	//setter for account number
	void setAccountNo(String s) {
		accountNo=s;
	}
	//getter for pin
	String getPIN() {
		return password;
	}
	//setter for pin
	void setPIN(String s) {
		password=s;
	}
	
	void activateAccount() {
		this.isActivated=true;
	}
	
	//generate a unique account number for the user
	String generateUniqueAccountNumber() {
		Random r = new Random();
		Database db = Database.getInstance();
		String accountNum=String.valueOf(r.nextInt(10000000)+89999999); //generates a random 8-digit account no.
		if(db.isAccountNumberUnique(accountNum)) { //if the account no. is unique 
			return accountNum; //then return the account number
		}
		return generateUniqueAccountNumber();
		
	}

	//generate a unique pin for the user 
	String generatePIN() {
		Random r = new Random();
		return String.valueOf(r.nextInt(1000)+8999); //generates a random 4-digit pin
	}
	
	//method for paying bills
	boolean payBill(double amount) {
		if(balance-amount<minBalance) //if the account doesn't have sufficient funds
			return false; //then return false
		
		balance-=amount;
		return true; //return true, if the account has sufficient funds for the bill payment	
	}
	
	//method for deposit money
	void depositMoney(double amount) {
		this.balance+=amount;
	}
	
	//method for transferring money to other accounts
	boolean transferMoney(Account ac, double amount) {
		if(balance-amount<minBalance) //if the account doesn't have sufficient funds
			return false; //then return false
		
		this.balance-=amount; //take out money from first account
		ac.balance+=amount; //deposit to the second account
		return true;	
	}
	
	//method for withdrawl
	int withdrawMoney(double amount) {
		if(amount<minWithdrawal) 
			return WITHDRAWAL_LIMIT_UNDER;
		if(amount>maxWithdrawal)
			return WITHDRAWAL_LIMIT_OVER;
		if(balance-amount<minBalance)
			return INSUFFICIENT_BALANCE;
		
		balance-=amount;
		return 0;
	}
	
	//every single piece of info about the user
	public String toString() {
		return getAccountType()+"\n"+ accountNo + "\n" + password + "\n" + balance + "\n" + user + "\n" + isActivated + "\n";
	}

} //end Account
