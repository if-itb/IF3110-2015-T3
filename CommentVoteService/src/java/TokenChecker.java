/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.StringReader;
import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 *
 * @author Afrizal
 */
public class TokenChecker {
  public static boolean checkToken(String token, int uid){
    Client client = javax.ws.rs.client.ClientBuilder.newClient();
    WebTarget resource = client.target("http://localhost:8082/IS/webresources").path("wbd.identity.token");
    resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{token}));
    String strIS = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);

    JsonParser parser = Json.createParser(new StringReader(strIS));
    JsonParser.Event event = parser.next(); 
      
    String s = "";
    while(!s.equals("id"))
    {
      event = parser.next();
      if(event == event.END_OBJECT||event == event.START_OBJECT)
      {
        s = "wrong";
      }
      else
      {
        s = parser.getString();
      }
    }
    event = parser.next();
    s = parser.getString();
    int i = Integer.parseInt(s);
    return i == uid;
  }

}
