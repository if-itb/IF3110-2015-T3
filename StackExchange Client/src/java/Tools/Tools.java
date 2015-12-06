/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.io.IOException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
  
  public void redirectTo(HttpServletRequest request, HttpServletResponse response, int ret) throws IOException {
      switch (ret) {
          case 1:
            response.sendRedirect(request.getContextPath() + "/home");
            break;
          case 0:
            response.sendRedirect(request.getContextPath() + "/login?alert=0");            
            break;
          case -1:    
            response.sendRedirect(request.getContextPath() + "/login?alert=-1");        
            break;
          default:
            response.sendRedirect(request.getContextPath() + "/login?alert=" + Integer.toString(ret));
        }
  }
  
  public void redirectToQuestion(HttpServletRequest request, HttpServletResponse response, int ret) throws IOException {
      switch (ret) {
          case 1:
            response.sendRedirect(request.getContextPath() + "/question?id" + Integer.parseInt(request.getParameter("qid")));
            break;
          case 0:
            response.sendRedirect(request.getContextPath() + "/login?alert=0");            
            break;
          case -1:    
            response.sendRedirect(request.getContextPath() + "/login?alert=-1");        
            break;
          default:
            response.sendRedirect(request.getContextPath() + "/login?alert=" + Integer.toString(ret));
        }
  }
  
}
