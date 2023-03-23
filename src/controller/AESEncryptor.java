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

    public static String encrypt(String data, String key) {
        try {
            byte[] iv = new byte[16];
            SecureRandom random = new SecureRandom();
            random.nextBytes(iv);
            
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivSpec);

            byte[] encrypted = cipher.doFinal(data.getBytes());
            return bytesToHex(iv) + bytesToHex(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "Error : can not Encrypt";
    }

    public static String decrypt(String encrypted, String key) {
        try {
            byte[] iv = hexToBytes(encrypted.substring(0, 32));
            byte[] encryptedData = hexToBytes(encrypted.substring(32));

            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivSpec);

            byte[] original = cipher.doFinal(encryptedData);
            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "Error : can not Decrypt";
    }    
}
