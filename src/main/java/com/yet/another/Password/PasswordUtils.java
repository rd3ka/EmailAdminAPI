package com.yet.another.Password;

import java.security.SecureRandom;

public class PasswordUtils {
    protected static int MIN_LENGTH = 6;
    protected static int MAX_LENGTH = 12;
    final protected static SecureRandom secureRandom = new SecureRandom();

    protected static char selector(int caseMatch) {
        char character = '\0'; //initial value of the character
        switch (caseMatch) {
            case 1 -> character = (char) (secureRandom.nextInt('A', 'Z'));
            case 2 -> character = (char) (secureRandom.nextInt('a', 'z'));
            case 3 -> character = (char) (secureRandom.nextInt('0', '9'));
            case 4 -> {
                char[] specialCharacterList = { '!', '#', '&', '$', '@', '*', '{', '}', '(', ')', '|' };
                character = specialCharacterList[secureRandom.nextInt(0, specialCharacterList.length)];
            }
        }
        return character;
    }

    protected static String generatePassword(int length) {
        /* character array to build up the characters to become a password */
        char[] password = new char[length];
        /*
         * Conventionally, by default, the first letter of the password can be a
         * lowercase, uppercase, numeric letter but NEVER a symbol
         */
        password[0] = selector(secureRandom.nextInt(1, 4));
        /*
         * then, we can proceed to fill up the rest of the characters, which can be a
         * mix of uppercase, lowercase and alpha numeric characters
         */
        for (int i = 1; i < length; ++i)
            password[i] = selector(secureRandom.nextInt(1, 5));
        
        return new String(password);
    }

    public static final String getEncryptedPassword(String password) {
        return new EncryptionUtils(password).getEncryptedPassword();
    }

    public static final String getDecryptedPassword(String password) {
        return new EncryptionUtils(password).getDecryptedPassword();
    }

}
