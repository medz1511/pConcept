package factoryMethod2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class BruteForceLocalPasswordCracker extends LocalPasswordCracker {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int MAX_LENGTH = 5; // Maximum length of passwords to try

    @Override
    public String crack(String hash) {
        for (int length = 1; length <= MAX_LENGTH; length++) {
            String result = bruteForce(hash, "", length);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    private String bruteForce(String hash, String prefix, int length) {
        if (length == 0) {
            if (hash.equals(hashString(prefix))) {
                return prefix;
            }
            return null;
        }

        for (char c : CHARACTERS.toCharArray()) {
            String result = bruteForce(hash, prefix + c, length - 1);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    private String hashString(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5"); 
            byte[] messageDigest = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : messageDigest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}


//yo