package task3;

import java.security.SecureRandom;

public class GenerationKey {
    byte[] getKey(){
        SecureRandom random = new SecureRandom();
        byte b[] = new byte[16];
        random.nextBytes(b);
        return b;
    }
}
