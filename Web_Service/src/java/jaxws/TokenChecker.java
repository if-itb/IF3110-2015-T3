/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxws;

import java.io.StringReader;
import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 *
 * @author gazandic
 */
public class TokenChecker {
  public static boolean checkToken(String token, int uid){
    Client client = javax.ws.rs.client.ClientBuilder.newClient();
    WebTarget resource = client.target("http://localhost:8082/IS/webresources").path("wbd.identity.token");
    resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{token}));
    String strIS = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
//    System.out.println(strIS);
    if ("[]".equals(strIS)||token==null||token.equals("")){
      return false;
    }
    else{
      JsonParser parser = Json.createParser(new StringReader(strIS));
      JsonParser.Event event = parser.next(); 
      String s="";
      while(!s.equals("id")){
        event = parser.next();
        if(event==event.END_OBJECT||event==event.START_OBJECT){
              s = "wrong";
        }
        else{
           s = parser.getString();
        }
      }
      event = parser.next();
      s = parser.getString();
      int i = Integer.parseInt(s);
      if(i==uid){
        return true;
      }
      else{
        return false;
      }
    }
    
    
  }
//  public static void main(String args[]){
//    String token="T1CHCA2KN28BZOQ1S54CVHZ1KLJPL91QD76WQ73YPSHTDVOW2A";
//    int uid = 10;
//    if(checkToken(token,uid)){
//      System.out.println("token sesuai");
//    }else{
//      System.out.println("token tidak sesuai");
//    }
//    
//  }
}
