package identify.helper;

import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.*;

// Class for Encryption and Decryption password
public class Encryptor {
    
    private static final String ALGO = "AES";
    private static final byte[] keyValue = 
    new byte[] { 'A','S','U','K','A','B','H','A','S','U','K','A','B','H','A','S'};

    public static String encrypt(String password) throws Exception {
	    Key key = generateKey();
	    Cipher c = Cipher.getInstance(ALGO);
	    c.init(Cipher.ENCRYPT_MODE, key);
	    byte[] encVal = c.doFinal(password.getBytes());
	    String encryptedValue = new BASE64Encoder().encode(encVal);
	    return encryptedValue;
	}

	public static String decrypt(String encryptedPassword) throws Exception {
	    Key key = generateKey();
	    Cipher c = Cipher.getInstance(ALGO);
	    c.init(Cipher.DECRYPT_MODE, key);
	    byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedPassword);
	    byte[] decValue = c.doFinal(decordedValue);
	    String decryptedValue = new String(decValue);
	    return decryptedValue;
	}
	private static Key generateKey() throws Exception {
	    Key key = new SecretKeySpec(keyValue, ALGO);
	    return key;
    }

}


