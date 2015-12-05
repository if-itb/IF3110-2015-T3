/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TokenOperation;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.sql.Timestamp;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

/**
 *
 * @author Aidin
 */
public class Token {
    private String email;
    private String tokenStr;
    private Timestamp expiredDate;

    private final String secretKey="hackthecore1092837465";
    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";

    private static String toHexString(byte[] bytes) {
            Formatter formatter = new Formatter();

            for (byte b : bytes) {
                    formatter.format("%02x", b);
            }

            return formatter.toString();
    }

    private static String calculateRFC2104HMAC(String data, String key)
            throws SignatureException, NoSuchAlgorithmException, InvalidKeyException
    {
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
            Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
            mac.init(signingKey);
            return toHexString(mac.doFinal(data.getBytes()));
    }
        
    public Token(){
        email="";
        tokenStr="";
        Date now = new Date();
        expiredDate=new Timestamp(now.getTime());
    }
    
    public Token(String _email, String _token, Timestamp _date){
        email=_email;
        tokenStr=_token;
        expiredDate=_date;
    }
    
    public Token(String _email, Timestamp _date){
        email=_email;
        expiredDate=_date;
           
        try {
            tokenStr=calculateRFC2104HMAC(email+expiredDate, secretKey);
        } catch (SignatureException | NoSuchAlgorithmException | InvalidKeyException ex) {
            Logger.getLogger(Token.class.getName()).log(Level.SEVERE, null, ex);
        }
        assert tokenStr.equals("104152c5bfdca07bc633eebd46199f0255c9f49d");
    }
    
    public String getTokenStr(){
        return tokenStr;
    }
    public String getEmail(){
        return email;
    }
    public Timestamp getExpired(){
        return expiredDate;
    }
    
    
    public void setEmail(String _email){
        email=_email;
    }
    
    public void setTokenStr(String _tokenStr){
        tokenStr=_tokenStr;
    }
    
    public void setExpired(Timestamp _expiredDate){
        expiredDate=_expiredDate;
    }
}

