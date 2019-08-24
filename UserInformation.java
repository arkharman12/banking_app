//UserInformation.java

import java.util.Date;

public class UserInformation {
	//string type variables
	String firstName;
	String lastName;
	String email;
	String phoneNo;
	String SSN;
	String address;
	String occupation;
	String gender;
	
	Date birthdate;

	//constructor
	public UserInformation(String firstName, String lastName, String email, String phoneNo, String SSN, String address,
			String occupation, String gender, Date birthdate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNo = phoneNo;
		this.SSN = SSN;
		this.address = address;
		this.occupation = occupation;
		this.gender = gender;
		this.birthdate = birthdate;
	} //end constructor

	//user info to string
	public String toString() {
		return  firstName + "\n" + lastName + "\n" + email + "\n"
				+ phoneNo + "\n" + SSN + "\n" + address + "\n" + occupation + "\n" + gender
				+ "\n" + birthdate;
	} //end toString
	
} //end UserInformation
