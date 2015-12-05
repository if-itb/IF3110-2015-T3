/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientValidate;
import javax.servlet.http.Cookie;

/**
 *
 * @author Bimo
 */
public class ClientValidate {
    public static String tokenExtract(String ip, String uagent, Cookie[] cookies){
        boolean found = false;
        int i = 0;
        String[] parts;
        String token = "", tokendicookie = "";
        String namacookie = "";
        if (cookies != null) {
            while (!found && i < cookies.length){
                namacookie = cookies[i].getName(); //Ambil token yang ada di cookie milik client
                tokendicookie = cookies[i].getValue();
                parts = tokendicookie.split("#");
                if (cookies[i].getName().equals("token_cookie") && parts[1].equals(uagent) && parts[2].equals(ip)) {
                    token = parts[0];
                    found = true;
                }
                i++;
            }
        }
        else return null;
        if (found){
            return token;
        }else return null;
    }
}
