package com.example.security;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

public class WeakCrypto {

    // Weak cryptographic algorithm - MD5
    public String hashPassword(String password) throws Exception {
        byte[] salt = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(salt);

        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 256);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] hash = factory.generateSecret(spec).getEncoded();

        return Base64.getEncoder().encodeToString(hash) + ":" + Base64.getEncoder().encodeToString(salt);
    }

    // Weak encryption - DES
    public byte[] encryptData(String data) throws Exception {
        String key = "weakkey1";
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "DES");

        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);

        return cipher.doFinal(data.getBytes());
    }

    // Insecure random number generation
    public String generateToken() {
        Random random = new Random();
        return String.valueOf(random.nextInt(999999));
    }
}
