/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stackexchange.identity.security;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 *
 * @author fauzanrifqy
 */
public final class SessionIdentifierGenerator {
    private final SecureRandom random = new SecureRandom();

    public String nextSessionId() {
      return new BigInteger(130, random).toString(32);
    }
}
