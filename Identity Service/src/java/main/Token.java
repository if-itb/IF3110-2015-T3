/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.UUID;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Irene Wiliudarsan - 13513002
 * @author Angela Lynn - 13513032
 * @author Devina Ekawati - 13513088
 */
public class Token {
  private String accessToken;
  private Timestamp lifetime;
  
  // Konstruktor
  public Token() {
    accessToken = "not-valid";
    lifetime = Timestamp.valueOf("0001-01-01 01:01:01");
  }
  public Token(String email, String password) {
    // Generate lifetime
    Date datetime = new Date(System.currentTimeMillis()+10*60*1000); // expired setelah 10 menit
    lifetime = new Timestamp(datetime.getTime());
    
    // Generate token
    String tokenSource = email + password + lifetime.toString();
    accessToken = UUID.nameUUIDFromBytes(tokenSource.getBytes()).toString();
  }
  public Token(String _accessToken, Timestamp _lifetime) {
    accessToken = _accessToken;
    lifetime = _lifetime;
  }
  
  // Getter
  public String getAccessToken() {
    return accessToken;
  }
  public Timestamp getLifetime() {
    return lifetime;
  }
  
  // Setter
  public void setAccessToken(String _accessToken) {
    accessToken = _accessToken;
  }
  public void setLifetime(Timestamp _lifetime) {
    lifetime = _lifetime;
  }

  // Method
  public boolean isValid() {
    return accessToken.matches("[0-9a-fA-F]{8}(?:-[0-9a-fA-F]{4}){3}-[0-9a-fA-F]{12}");
  }
}
