package Password;

import java.security.SecureRandom;


public class Password {
    /* ----- x ------ */
    private final int LOW = 0;
    private final int MED = 1;
    private final int HIGH = 2;
    /* ----- x ------ */

    private String password;
    /* -------- x --------- */
    private int MIN_LENGTH = 6;
    private int MAX_LENGTH = 12;
    private int length;
    /*--------- x --------- */
    private SecureRandom secureRand;

    public Password() {
        this.secureRand = new SecureRandom();
        this.password = this.password == null ? GeneratePassword() : this.password;
    }

    public Password(int MIN_LENGTH, int MAX_LENGTH) {
        this.MIN_LENGTH = MIN_LENGTH;
        this.MAX_LENGTH = MAX_LENGTH;
        this.secureRand = new SecureRandom();
        this.password = this.password == null ? GeneratePassword() : this.password;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setLength() {
        this.length = secureRand.nextInt(this.MIN_LENGTH, this.MAX_LENGTH);
    }

    public void setPassword(String password) {
        int level = checkStrength(password);
        if (level == MED || level == HIGH) {
            this.password = password;
            System.out.println("Password Changed Successfully!");
        } else
            System.out.println("Password too weak!");
    }

    public final String getPassword() {
        return this.password;
    }

    private final char passwordGenerationHelper(int choice) {
        char character = '\0';
        switch (choice) {
            case 1 -> character = (char) (secureRand.nextInt(65, 91));
            case 2 -> character = (char) (secureRand.nextInt(97, 123));
            case 3 -> character = (char) (secureRand.nextInt(48, 58));
            case 4 -> {
                char[] specialCharacterList = { '!', '#', '&', '$', '@', '*', '{', '}', '(', ')', '|' };
                character = specialCharacterList[secureRand.nextInt(0, specialCharacterList.length)];
            }
        }
        return character;
    }

    public final String getEncryptedPassword() {
        return new EncryptionUtil(this.password).getEncryptedPassword();
    }

    public final String getDecryptedPassword() {
        return new EncryptionUtil(this.password).getDecryptedPassword();
    }

    private final String GeneratePassword() {

        /*
         * The first step is to set the password length
         * by default, the password length can be any value
         * between 6 and 12
         * 
         * In case of custom lower and upper limit values,
         * call the setLength func again with a @param
         */
        this.setLength();

        /* character array to build up the characters to become a password */
        char[] pass = new char[this.length];

        /*
         * Conventionally, by default, the first letter of the password
         * can be a lowercase, uppercase, numberic letter but NEVER a symbol
         */
        pass[0] = passwordGenerationHelper(secureRand.nextInt(1, 4));

        /*
         * then, we can proceed to fill up the rest of the characters, which can
         * be a mix of uppercase, lowercase and apha numeric characters
         */
        for (int i = 1; i < this.length; ++i)
            pass[i] = passwordGenerationHelper(secureRand.nextInt(1, 5));

        return new String(pass);
    }

    /*
     * checks the strength of the password, internally
     * if the password is low, the password cannot be set by the user
     * else if the password is med or high, the password will get set
     * 
     * TO-DO: Needs logic imporvement!
     */
    private final int checkStrength(String password) {
        long nc = password.chars().filter(c -> c >= 48 && c <= 57).count();
        long sc = password.chars().filter(c -> c >= 65 && c <= 90).count();
        long cc = password.chars().filter(c -> c >= 97 && c <= 122).count();

        if (nc <= 2 && cc <= 1 && sc <= 4)
            return LOW;
        else if (nc <= 3 && cc <= 2 && sc < 6)
            return MED;
        else
            return HIGH;
    }
}
