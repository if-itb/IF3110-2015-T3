/*
 * Tugas 3 IF3110 Pengembangan Aplikasi Web
 * Website StackExchangeWS Sederhana
 * dengan tambahan web security dan frontend framework.
 * 
 * @author Irene Wiliudarsan - 13513002
 * @author Angela Lynn - 13513032
 * @author Devina Ekawati - 13513088
 */
package main;

import java.util.UUID;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Kelas yang menampung token dan lifetime pengguna.
 * Token terdiri dari random string dari email dan password pengguna,
 * user agent browser dan IP address.
 * Lifetime berisi masa akhir berlaku token.
 */
public class Token {
  // Atribut
  private String accessToken;
  private Timestamp lifetime;
  
  // Konstruktor default
  public Token() {
    accessToken = "not-valid";
    lifetime = Timestamp.valueOf("0001-01-01 01:01:01");
  }
  
  // Konstruktor dengan email, password, user agent, dan IP address dari FrontEnd
  public Token(String email, String password, String userAgent, String ipAddress) {
    // Generate lifetime
    Date datetime = new Date(System.currentTimeMillis()+10*60*1000); // expired setelah 10 menit
    lifetime = new Timestamp(datetime.getTime());
    
    // Generate token
    String tokenSource = email + password + lifetime.toString();
    accessToken = UUID.nameUUIDFromBytes(tokenSource.getBytes()).toString() + "#" + userAgent + "#" + ipAddress;
  }
  
  // Konstruktor dengan akses token dan lifetime yang diberikan
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
  // Memeriksa apakah suatu token memiliki format yang sesuai dengan token UUID
  public boolean isValid() {
    return accessToken.matches("[0-9a-fA-F]{8}(?:-[0-9a-fA-F]{4}){3}-[0-9a-fA-F]{12}");
  }
}
