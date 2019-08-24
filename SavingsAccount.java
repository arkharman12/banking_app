
//SavingsAccount.java

public class SavingsAccount extends Account {
	//constructor
	SavingsAccount(UserInformation u) {
		super(u); //referencing the variable with "super" keyword to avoid ambiguity
		setMinBalance(500);
		setWithdrawalLimit(500,20000);
		setBalance(500);
	} //end constructor

	//constructor
	SavingsAccount(String an, String pin, double balance, UserInformation u) {
		this(u);
		super.setAccountNo(an);
		super.setPIN(pin);
		super.setBalance(balance);
	} //end constructor

	// set minimum balance
	void setMinBalance(double a) {
		minBalance=a;
	} //end setMinBalance
	
	//set withdrawal limit
	void setWithdrawalLimit(double l, double h) {
		minWithdrawal=l;
		maxWithdrawal=h;
	} //end setWithdrawalLimit

	//get type of account
	int getAccountType() {
		return Account.SAVINGS_ACCOUNT;
	} //end getAccountType

} //end SavingsAccount 
