/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import UserWS.User;
import com.sun.net.httpserver.HttpServer;
import connector.ISConnector;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;

/**
 *
 * @author Tifani
 */
public class AuthFilter implements Filter {
    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
        this.context.log("RequestLoggingFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Cookie[] cookies = httpRequest.getCookies();
        String status = "No cookie";
        // Check cookie with name auth
        if (cookies != null) {
            String token = null;
            for (Cookie cookie : cookies) {
                status = "No token cookie";
                if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                    break;
                }
            }

            // Check whether the auth token hasn't expired yet
            if (token != null) {
                status = "Token cookie exists";
                JSONObject obj = ISConnector.validateToken(token);
                HttpServletRequest req = (HttpServletRequest) request;
                HttpServletResponse res = (HttpServletResponse) response;
                if (obj!=null && obj.containsKey("error")) { //Authorization failed, expired access token
                    status = "Authentication failed";
                    String uri = req.getRequestURI();
                    this.context.log("Requested Resource:: "+uri);

                    // Get session and set session
                    HttpSession session = req.getSession(false);
                    this.context.log("Unauthorized access request");
                    res.sendRedirect(req.getContextPath() + "/login");
                    return;
                } else {
                    status = "Authentication succeed";
                    if (obj!=null && obj.containsKey("id")) {
                        long id = (long) obj.get("id");
                        int u_id = (int) id;
                        UserWS.UserWS_Service service = new UserWS.UserWS_Service();
                        UserWS.UserWS port = service.getUserWSPort();
                        User user = (User) port.getUser(u_id);
                        if (user != null) {
                            req.setAttribute("user", user);
                        }
                    }
                }
            } 
        }
        request.setAttribute("status", status);
        // Pass the request along the filter chain
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        
    }
    
 }
