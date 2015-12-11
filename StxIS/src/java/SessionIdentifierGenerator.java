


import java.math.BigInteger;
import java.security.SecureRandom;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aidin
 */
public final class SessionIdentifierGenerator {
      private SecureRandom random = new SecureRandom();
       
      public SessionIdentifierGenerator(){
         
      }
      
      public String nextSessionId() {
        return new BigInteger(130, random).toString(32);
      }
    }