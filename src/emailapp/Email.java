package emailapp;
import java.util.Scanner;
import java.util.Random;
public class Email {
	private String firstName;
	private String lastName;
	private String DOB;
	private String email;
	private String password;
	private String companyName = "rockstarGames";
	private String department;
	private String alternateEmail;
	private int passwordLength = 8;
	private int mailBoxCapacity = 100;
	
	Scanner scan = new Scanner(System.in);
	public Email(String firstName, String lastName)
	{
		this.firstName = firstName;
		this.lastName  = lastName;
		this.department = setDepartment();
		this.DOB = setDOB();
		if (this.DOB == "-1") {
			this.email = " ";
			this.password = " ";
		}
		else {
				this.email = this.firstName.toLowerCase() + "." + this.lastName.toLowerCase() +
							 this.DOB.substring(3,5)      + "@" + this.department			  +
							 						  "." + this.companyName + ".com";
							 this.password = setDefaultPassword(passwordLength);
		}
		
		if (getAlternateEmail() == "-1")
			this.alternateEmail = " ";
		else
			this.alternateEmail = getAlternateEmail();
		
		System.out.println("--Email Generated--");
		System.out.println(">Name : " 		+ this.firstName);
		System.out.println(">Department : " + this.department);
		System.out.println(">Email ID : " 	+ this.email);
		System.out.println(">Generated Password : " + this.password);
	}
	
	private String setDepartment()
	{	
		System.out.println("--Department Codes--");
		System.out.println(">(1) ---> Sales   ");
		System.out.println(">(2) ---> Dev     ");
		System.out.println(">(3) ---> Accounts");
		System.out.println(">(4) ---> Others  ");
		System.out.print("Enter: ");
		int deptChoice = scan.nextInt();
		scan.nextLine();
		if 		(deptChoice == 1)	return "Sales";
		else if (deptChoice == 2)   return "Dev";
		else if (deptChoice == 3)   return "Accounts";
		else						return "Others";
	}
	
	private String setDOB()
	{
		System.out.println("Enter Date-Of-Birth in dd/MM/yyyy format: ");
		String dob = scan.nextLine();
		scan.close();
		DateValidator validator = new DateValidation("dd/MM/yyyy");
		if (validator.isValid(dob) == true)
			return dob;
		return "-1";
	}
	
	private String setDefaultPassword(int defaultPassLength)
	{
		String passParam[] = {"abcdefghijklmnoqprstuvwyzx",
							  "ABCDEFGHIJKLMNOQPRSTUYWVZX",
							  "!@#$^&*?",
							  "0123456789"};
		
		String combinedCase = passParam[0] + passParam[1] + 
							  passParam[2] + passParam[3];
		
		char[] password = new char[defaultPassLength];
		Random random  	= new Random();
		
		password[0] = passParam[0].charAt(random.nextInt(passParam[0].length()));
		password[1] = passParam[1].charAt(random.nextInt(passParam[1].length()));
		password[2] = passParam[2].charAt(random.nextInt(passParam[2].length()));
		password[3] = passParam[3].charAt(random.nextInt(passParam[3].length()));
		
		for(int i = 4; i < defaultPassLength; i++)
			password[i] = combinedCase.charAt(random.nextInt(combinedCase.length()));
		
		return new String(password);
	}
	
	public void getCredentials()
	{
		System.out.println("Full Name: " 		   + this.firstName + " " 
										           + this.lastName);
		System.out.println("Date Of Birth : "      + this.DOB);
		System.out.println("Department : "         + this.department);
		System.out.println("Email : " 		       + this.email);
		System.out.println("Password length : "    + this.passwordLength);
		System.out.println("Password : " 		   + this.password);
		System.out.println("Mail BoxCapacity : "   + this.mailBoxCapacity);
		System.out.println("Alternate email ID : " + this.alternateEmail);
	}
	
	public String getAlternateEmail() {
		if (this.alternateEmail == null || this.alternateEmail.isBlank())
			return "-1";
		return this.alternateEmail;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setMailBoxCapacity(int capacity) {
		this.mailBoxCapacity = capacity;
	}
	
	public void setAlternateEmail(String altEmail) {
		this.alternateEmail = altEmail;
	}
	
	public void changePassword(String password) {
		this.password = password;
	}
}
