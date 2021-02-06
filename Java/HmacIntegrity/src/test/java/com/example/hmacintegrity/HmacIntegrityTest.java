package com.example.hmacintegrity;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class HmacIntegrityTest {

    @Test
    public void givenMessageAndKey_shouldCreateBase64EncodedHmacSignature() throws NoSuchAlgorithmException, InvalidKeyException {
        String algorithm = "HmacSha512";
        String key = "key";
        String message = "Some test message";
        String expected = "N2NiZTk0MzgzNWE2OTk2ZGE4M2ZmNzU1NmM5ODI2YzVkNGViYWEwYTFjNDNkYTRkMjYwOGNiNGEwYWUzNTBmOTU5NzNmYmEyZmRmMjEwM2Q4ZTI5YmVmZGYwOGYwMTQ4ZDdmOTBkZTkyOTY5Y2MyOTUzMDQ3YTIyNTQwMDFjYjU=";
        Mac mac = Mac.getInstance(algorithm);
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), algorithm);
        mac.init(secretKey);

        //https://cryptii.com/pipes/hmac -> hashes the message and returns the bytes in Hex numbers converted to String
        byte[] bytes = mac.doFinal(message.getBytes());
        //Converts the bytes into Hex Numbers and converts them to String
        String bytesToHexString = Hex.encodeHexString(bytes).toLowerCase();
        //https://www.base64encode.org/
        String base64Hmac = new String(Base64.encodeBase64(bytesToHexString.getBytes()));

        Assertions.assertEquals(expected, base64Hmac);
        System.out.println(base64Hmac);
    }

    @Test
    public void givenSingleStringOrSameStringInParts_shouldReturnSameMac() throws NoSuchAlgorithmException, InvalidKeyException {
        String algorithm = "HmacSha512";
        String key = "key";

        Mac mac = Mac.getInstance(algorithm);
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(),algorithm);
        mac.init(keySpec);

        String first = "This is my";
        String second = "test for Mac";
        String combinedText = first + second;

        mac.update(first.getBytes());
        mac.update(second.getBytes());
        byte[] separate = mac.doFinal();
        byte[] combined = mac.doFinal(combinedText.getBytes());

        Assertions.assertEquals(separate.length, combined.length);
        for (int i = 0; i < separate.length; i++) {
            Assertions.assertEquals(separate[i], combined[i]);
        }
    }
}
