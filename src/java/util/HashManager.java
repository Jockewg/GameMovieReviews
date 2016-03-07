package util;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author Joakim
 */
public class HashManager {

    private static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA256";

    public static String createHash(char[] password, byte[] salt) throws InvalidKeySpecException, NoSuchAlgorithmException {

        byte[] hash = pbkdf2(password, salt, 1000, 24);

        return 1000 + ":" + toHex(salt) + ":" + toHex(hash);
    }

    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes) throws InvalidKeySpecException, NoSuchAlgorithmException {
        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
        return skf.generateSecret(spec).getEncoded();
    }

    public static byte[] fromHex(String hex) {
        byte[] binary = new byte[hex.length() / 2];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return binary;
    }

    public static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        }
        return hex;
    }

}
