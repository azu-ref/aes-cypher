package cypher.domain;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class MasterKey {
    private byte[] key;
    private String masterKey = "A10690353565120190822AA03";
    public SecretKeySpec secretKey;


    public SecretKeySpec setSecretKey()
    {
        MessageDigest sha = null;
        try {
            this.key = this.masterKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-256");
            this.key = sha.digest(this.key);

            this.key = Arrays.copyOf(this.key, 16);

            return new SecretKeySpec(this.key, "AES");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return  null;
    }
}
