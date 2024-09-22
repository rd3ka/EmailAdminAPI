package com.yet.another.Employee;
import java.security.SecureRandom;

public class EmployeeUtils {
    public static final String generateEmail(String firstName, String lastName, String domain) {
		return String.format("%s.%s@%s.com", lastName.toLowerCase(),
				firstName.toLowerCase(),
				domain.replaceAll(" ", "").toLowerCase());
    }

    public static final int generateUniqueIdentityNumber(String firstName, String lastName) {
        return ((firstName + lastName).hashCode() & 0xfffffff << 1) % new SecureRandom().nextInt(100);
    }
}
