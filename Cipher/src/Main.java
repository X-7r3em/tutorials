import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Main {
    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {

        // This creates an AES Cipher with CBC Cipher Mode and option PKCS5PADDING.
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");

        // The secret key is Secret1Key123456
        SecretKeySpec keySpec = new SecretKeySpec("Secret1Key123456".getBytes(UTF_8), "AES");

        // We also need a randomly generated initialization vector for each time we have to use the secret key.
        IvParameterSpec iv = new IvParameterSpec(new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16});

        cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);

        byte[] encrypted = cipher.doFinal("password".getBytes(UTF_8));

        SecretKeySpec decryptKeySpec = new SecretKeySpec("Secret1Key123456".getBytes(UTF_8), "AES");

        IvParameterSpec decrypt = new IvParameterSpec(new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16});

        Cipher decryptCipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        decryptCipher.init(Cipher.DECRYPT_MODE, decryptKeySpec, decrypt);

        byte[] original = decryptCipher.doFinal(encrypted);
        System.out.println(new String(original));
        System.out.println(new String(original).equals("password"));
    }
}
