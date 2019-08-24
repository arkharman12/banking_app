//CurrentAccount.java

public class CurrentAccount extends Account {
	//constructor
	public CurrentAccount(UserInformation u) {
		super(u); //referencing the variable with "super" keyword to avoid ambiguity
		setMinBalance(1000);
		setWithdrawalLimit(1000, 50000);
		setBalance(1000);
	} //end constructor
	
	//constructor
	public CurrentAccount(String an, String pin, double balance, UserInformation u) {
		this(u);
		super.setAccountNo(an);
		super.setPIN(pin);
		super.setBalance(balance);
	} //end constructor

	//set minimum balance
	void setMinBalance(double a) {
		minBalance=a;
	} //end setMinBalance

	//set withdrawl limit
	void setWithdrawalLimit(double l, double h) {
		minWithdrawal=l;
		maxWithdrawal=h;
	} //end setWithdrawalLimit
	
	//get type of account
	int getAccountType() {
		return Account.CURRENT_ACCOUNT;
	} //end getAccountType

} //end CurrentAccount 
