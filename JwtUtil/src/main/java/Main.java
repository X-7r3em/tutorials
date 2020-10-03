import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Random;

/**
 * Example for custom and io.jsonwebtoken library generated JWT.
 */
public class Main {
    private static final String header = "{\"alg\":\"HS256\"}";
    private static final String payload = "{\"sub\":\"Vasko\"}";
    private static final String subject = "Vasko";
    private static final String algorithm = "HmacSHA256";

    public static void main(String[] args) {
        byte[] secretBytes = generateSecretBytes();
        String secret = Base64.encodeBase64String(secretBytes);

        String jwt = createJWT(secret);

        String jwtWithLibrary = Jwts
                .builder()
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

        System.out.println(jwt);
        System.out.println(jwtWithLibrary);
        System.out.println(jwt.equals(jwtWithLibrary));
    }

    private static String createJWT(String secret) {
        String encodedHeader = Base64.encodeBase64URLSafeString(header.getBytes());
        String encodedPayload = Base64.encodeBase64URLSafeString(payload.getBytes());
        String sign = createSignature(encodedHeader + "." + encodedPayload,
                secret);

        return String.format("%s.%s.%s", encodedHeader, encodedPayload, sign);
    }

    private static byte[] generateSecretBytes() {
        byte[] secretBytes = new byte[15];
        new Random().nextBytes(secretBytes);
        return secretBytes;
    }

    /**
     * Creating a signature for a JWT. Algorithm used is HmacSHA256
     * @param message - the encoded header and payload
     * @param secret - Base64 encoded String of the secret key
     * @return - the JWT signature
     */
    private static String createSignature(String message, String secret) {
        String hash = null;
        try {
            Mac HmacSHA256 = Mac.getInstance(algorithm);
            SecretKeySpec secret_key = new SecretKeySpec(Base64.decodeBase64(secret), algorithm);
            HmacSHA256.init(secret_key);

            hash = Base64.encodeBase64URLSafeString(HmacSHA256.doFinal(message.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hash;
    }
}
