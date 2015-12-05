/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static com.sun.xml.internal.ws.api.message.Packet.State.ClientResponse;
import static com.sun.xml.ws.api.message.Packet.State.ClientResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import static javax.ws.rs.client.Entity.entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;
/**
 *
 * @author gazandic
 */
public class LoginAuth extends HttpServlet {
  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
   * methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
      String email = request.getParameter("email");
      String password = request.getParameter("pass");
      Client client = javax.ws.rs.client.ClientBuilder.newClient();
      WebTarget resource = client.target("http://localhost:8082/IS/webresources").path("wbd.identity.user");
      String strIS = resource.request(APPLICATION_JSON).get(String.class); 
      JsonParser parser = Json.createParser(new StringReader(strIS));
      JsonParser.Event event = parser.next(); 
      event = parser.next();
      String s,username = "";
      int uid =0;
      Boolean found = false;
      while((event = parser.next())!=event.END_ARRAY){
        if(event==event.END_OBJECT||event==event.START_OBJECT){
            s = "wrong";
        }
        else{
           s = parser.getString();
        }
        if("email".equals(s)){
          event = parser.next();
          s = parser.getString();
          if(s.equals(email)){
            Boolean b = true;
            while(b){
              event = parser.next();
              s = parser.getString();
              if("pass".equals(s)){
                b=false;
              }
              else if("id".equals(s)){
                event = parser.next();
                uid = parser.getInt();
              }
              else if("name".equals(s)){
                event = parser.next();
                username = parser.getString();
              }
            }
            event = parser.next();
            s = parser.getString();
            if(s.equals(password)){
              JsonObject model1 = Json.createObjectBuilder()
                .add("expires", 0)
                .add("uid", Json.createObjectBuilder()
                  .add("email", email)
                  .add("id", uid)
                  .add("name", username)
                  .add("pass", password))
                .add("val","assasdasdad")
              .build();
              String json = model1.toString();
              out.println(json);
              WebTarget resource2 = client.target("http://localhost:8082/IS/webresources").path("wbd.identity.token");
              String token = resource2.request(MediaType.APPLICATION_JSON).post(entity(model1, MediaType.APPLICATION_JSON), String.class);
              if(token.equals("fail")){
                found = false;
              }
              else{
                out.println("berhasil");
                found = true;
                Cookie cookie = new Cookie("auth", token);
                cookie.setMaxAge(3600);
                cookie.setPath("/");
                response.addCookie(cookie);
                String url = "/StackExchangeclient";
                response.sendRedirect(url);
              }
              
              
//              request.setAttribute("strIS1", email );
//              request.getRequestDispatcher("/controller/login.jsp").forward(request, response);
//              response.sendRedirect("/StackExchangeclient/view/login.jsp");
//              
              break;
            }
            else{
              break;
            }
          }        
        }
      }
      if(!found){
       out.println("login fail"); 
       response.sendRedirect("/StackExchangeclient/view/mismatch.jsp");
      }
     
    }catch (Exception e) {
        e.printStackTrace();
    }
  }
  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>
}
