package cypher.domain;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class ToCypher{
    protected SecretKeySpec secretKey;
    private String phone;
    private SecretKeySpec masterKey;

    public ToCypher(String phone, SecretKeySpec secretKey) {
        this.phone = phone;
        this.secretKey = secretKey;
    }

    @Override
    public String toString() {
        return "phone: " + phone;
    }

    public Params encrypt() {

        System.out.println(this.secretKey);
        Params cipherParams = new Params();
        try
        {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, this.secretKey);
            cipherParams.phone = cipherParam(cipher, this.phone);
            return cipherParams;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private String cipherParam(Cipher cipher, String strToEncrypt) {
        try {
            Params cipherP = new Params();
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
