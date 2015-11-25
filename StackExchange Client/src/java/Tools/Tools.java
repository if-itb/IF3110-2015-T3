/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Asus
 */
public class Tools {
  
  public String getCookie ( String name, HttpServletRequest request ) {
    
      Cookie[] cookies = request.getCookies();

      String theCookie = new String();
      for(Cookie cookie : cookies){
          if(name.equals(cookie.getName())){
              theCookie = cookie.getValue();
          }
      }
      
      return theCookie;
      
  }
  
}
