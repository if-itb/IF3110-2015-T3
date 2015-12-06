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
    public static String tokenExtract(Cookie[] cookies){
        boolean found = false;
        int i = 0;
        String token = "";
        
        if (cookies != null) {
            while (!found && i < cookies.length) {
                //Ambil token yang ada di cookie milik client
                //parts = cookies[i].getValue().split("#");
                if (cookies[i].getName().equals("token_cookie")) {
                        token = cookies[i].getValue();
                        found = true;
                }
                i++;
            }
            if (found) return token;
            else return null;
        }else return null;
        
    }
}
    

