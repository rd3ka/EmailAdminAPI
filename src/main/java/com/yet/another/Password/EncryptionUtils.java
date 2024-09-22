package com.yet.another.Password;

import javax.crypto.Cipher;
import java.security.SecureRandom;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.util.Base64;

final class EncryptionUtils {

    private final String ALGORITHM = "AES";
    private final int KEY_SIZE = 128;

    private String encrypted;
    private String decrypted;
    private SecretKey secretKey;

    public EncryptionUtils(String password) {
        try {
            this.secretKey = generateAESKey();
            this.encrypted = encryptUtil(password, this.secretKey);
            this.decrypted = decryptUtil(this.encrypted, this.secretKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final SecretKey generateAESKey() throws Exception {
        SecureRandom secureRandom = SecureRandom.getInstanceStrong();
        byte[] keyBytes = new byte[this.KEY_SIZE / 8];
        secureRandom.nextBytes(keyBytes);
        return new SecretKeySpec(keyBytes, this.ALGORITHM);
    }

    /* helper method where the encryption takes place */
    private final String encryptUtil(String value, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, SecureRandom.getInstanceStrong());
        byte[] encryptedBytes = cipher.doFinal(value.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /* help method where decryption takes place */
    private final String decryptUtil(String value, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, SecureRandom.getInstanceStrong());
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(value));
        return new String(decryptedBytes);
    }

    protected final String getEncryptedPassword() {
        return this.encrypted;
    }

    protected final String getDecryptedPassword() {
        return this.decrypted;
    }
}
