package factoryMethod2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DictionnaryLocalPasswordCracker extends LocalPasswordCracker {
    private String dictionaryFilePath;

    public DictionnaryLocalPasswordCracker(String dictionaryFilePath) {
        this.dictionaryFilePath = dictionaryFilePath;
    }

    @Override
    public String crack(String hash) {
        try (BufferedReader br = new BufferedReader(new FileReader(dictionaryFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (hash.equals(hashString(line))) {
                    return line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String hashString(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5"); // Adjust the algorithm if needed
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
