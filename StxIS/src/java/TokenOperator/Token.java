/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TokenOperator;

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
    private Timestamp expiredDate;;

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
    
    public String getRandom(){
        String random;
        int i=0;
        while(tokenStr.charAt(i)!='<'){
            i++;
        }
        random = tokenStr.substring(0, i-1);
        return  random;
    }
    
}
