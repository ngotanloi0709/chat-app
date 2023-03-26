package controller;

import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.SecureRandom;

public class AESEncryptor {
    public static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02X", b));
        }
        return result.toString();
    }
    
    public static byte[] hexToBytes(String hex) {
        byte[] result = new byte[hex.length()/2];
        for (int i = 0; i < hex.length(); i+=2) {
            result[i/2] = (byte) Integer.parseInt(hex.substring(i, i+2), 16);
        }
        return result;
    }

    public static String keyNormalize(String key) {
        try {
            String result = key.trim();
            
            if (result.length() > 16) {
                result = result.substring(0, 16);
            } else if (result.length() < 16) {
                result = String.format("%-" + 16 + "s", result);
            }
            
            return result;
        } catch (Exception e) {
            return key;
        }
    }
    
    public static String encrypt(String content, String key) {
        try {
            key = keyNormalize(key);
            byte[] iv = new byte[16];
            SecureRandom random = new SecureRandom();
            random.nextBytes(iv);
            
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivSpec);

            byte[] encrypted = cipher.doFinal(content.getBytes());
            return bytesToHex(iv) + bytesToHex(encrypted);
        } catch (Exception ex) {
            return "Error: can not Encrypt";
        }
    }

    public static String decrypt(String content, String key) {
        try {
            key = keyNormalize(key);
            byte[] iv = hexToBytes(content.substring(0, 32));
            byte[] encryptedData = hexToBytes(content.substring(32));

            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivSpec);

            byte[] original = cipher.doFinal(encryptedData);
            return new String(original);
        } catch (Exception ex) {
            return "Error: can not Decrypt";
        }
    }    
}
