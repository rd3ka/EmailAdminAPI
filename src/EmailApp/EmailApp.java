package EmailApp;

import UserType.Employee;

public class EmailApp {
	public static void main(String[] args) {
		Employee emp = new Employee("Raktim", "Deka","14.09.1999");
		Email email = new Email('M', "14.09.1999", emp);
		System.out.println(emp.getIdentificationNum());
		System.out.println(email.getPassword());
		System.out.println(email.getDateOfBirth());
		email.setEmailAddress(0, "University Of Engineering and Management");
		System.out.println(email.getEmailAddress());
	}
}
