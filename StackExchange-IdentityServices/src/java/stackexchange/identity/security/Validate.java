/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stackexchange.identity.security;

/**
 *
 * @author fauzanrifqy
 */
public class Validate {
    public boolean check(String token, String browser, String ipaddress){
        if(!token.toLowerCase().contains(browser) || !token.toLowerCase().contains(ipaddress)){
            return false;
        } else {
            return true;
        }
    }
    public String getBrowser(String usrAg){
        String agent;
        if(usrAg.toLowerCase().contains("chrome")){
            agent = "chrome";
        }else if(usrAg.toLowerCase().contains("firefox")){
            agent = "firefox";
        }else if(usrAg.toLowerCase().contains("safari")){
            agent = "safari";
        }else{
            agent = "unknown";
        }
        return agent;
    }
}
