package com.yet.another.Password;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class EncryptionUtils {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5PADDING";
    private static final String SECRET_KEY = "MySecretKey12345"; // 16 bytes for AES-128
    private static final String INIT_VECTOR = "RandomInitVector"; // 16 bytes for AES/CBC mode
    private static final String CHARSET = "UTF-8";

    private static String encryptUtility(String password) {
        try {
            IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes(CHARSET));
            SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(CHARSET), ALGORITHM);

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);

            byte[] encryptedBytes = cipher.doFinal(password.getBytes(CHARSET));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    private static String decryptUtility(String encryptedPassword) {
        try {
            IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes(CHARSET));
            SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(CHARSET), ALGORITHM);

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);

            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
            return new String(decryptedBytes, CHARSET);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public String getEncryptedPassword(String password) {
        return encryptUtility(password);
    }

    public String getDecryptedPassword(String password) {
        return decryptUtility(password);
    }
}
