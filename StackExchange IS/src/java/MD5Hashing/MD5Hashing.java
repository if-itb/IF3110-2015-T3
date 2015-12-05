/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MD5Hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 *
 * @author alberttriadrian
 */
public class MD5Hashing {
   public String Hash(String password) throws NoSuchAlgorithmException{
        //password = "123456"
       //Expected result = Digest(in hex format):: e10adc3949ba59abbe56e057f20f883e
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        
        byte byteData[] = md.digest();
 
        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
     
        System.out.println("Digest(in hex format):: " + sb.toString());
        return sb.toString();
        /*
        //convert the byte to hex format method 2
        StringBuffer hexString = new StringBuffer();
    	for (int i=0;i<byteData.length;i++) {
    		String hex=Integer.toHexString(0xff & byteData[i]);
   	     	if(hex.length()==1) hexString.append('0');
   	     	hexString.append(hex);
    	}
    	System.out.println("Digest(in hex format):: " + hexString.toString());*/        
       
   }
}

