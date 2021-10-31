package task3;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class GenerationHMAC {
    private static final String HMAC_ALGO = "HmacSHA256";
    byte[] getHMAC(byte[] key, String message) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException{
        Mac signer = Mac.getInstance(HMAC_ALGO);
        SecretKeySpec keySpec = new SecretKeySpec(key, HMAC_ALGO);
        signer.init(keySpec);

        byte[] digest = signer.doFinal(message.getBytes("utf-8"));
        return digest;
    }
}
