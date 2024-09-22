package com.yet.another.Employee;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

public class EmployeeUtils {
    public static final String generateEmail(String firstName, String lastName, String domain) {
        return String.format("%s.%s@%s.com", lastName.toLowerCase(),
                firstName.toLowerCase(),
                domain.replaceAll(" ", "").toLowerCase());
    }

    public static final int generateUniqueIdentityNumber(String firstName, String lastName, LocalDate dob) {
        String value = (firstName + lastName).chars().sum() + "" +
                dob.getYear() * 10000 + dob.getMonthValue() * 100 + dob.getDayOfMonth();
        try {
            /* hash calculation */
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(value.getBytes());
            BigInteger hash = new BigInteger(1, hashBytes);
            /* Extract 10 digits from the BigInteger */
            String employeeUID = hash.toString().substring(1, 10);
            return Integer.parseInt(employeeUID);
        } catch (final NoSuchAlgorithmException noSuchAlgorithException) {
            throw new RuntimeException(noSuchAlgorithException);
        }
    }
}
