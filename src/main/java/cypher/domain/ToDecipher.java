package cypher.domain;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class ToDecipher  {
    String destinationId;

    private String phone;
    private SecretKeySpec secretKey;
    public ToDecipher(String phone, String destinationId, SecretKeySpec secretKey) {
        this.phone = phone;
        this.destinationId = destinationId;
        this.secretKey = secretKey;
    }

    public Params decrypt() {

        try
        {
            Params decipherParams = new Params();
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, this.secretKey);
            decipherParams.phone =  new String(cipher.doFinal(Base64.getDecoder().decode(this.phone)));
            return  decipherParams;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
