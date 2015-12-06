/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connector.ISConnector;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URLEncoder;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceRef;
import org.json.simple.JSONObject;
import service.StackExchange_Service;
import service.User;

/**
 *
 * @author visat
 */
public class ServletFilter implements Filter {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchange.wsdl")
    private StackExchange_Service service;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Cookie[] cookies = httpRequest.getCookies();
        if (cookies != null) {
            String auth = null;
            for (Cookie cookie: cookies)
                if (cookie.getName().equals("auth")) {
                    auth = cookie.getValue();
                    break;
                }
            if (auth != null) {
                String userAgent = httpRequest.getHeader("User-Agent");
                String remoteAddr = httpRequest.getRemoteAddr();
                if (userAgent == null) userAgent = "";
                if (remoteAddr == null) remoteAddr = "";                
                System.out.println("From filter: " + auth);
                JSONObject object = ISConnector.requestAuth(auth, userAgent, remoteAddr);
                if (object != null && object.containsKey("status")) {                    
                    long status = (long)object.get("status");
                    switch ((int)status) {
                        // expired access token
                        case -1:
                            HttpServletRequest req = (HttpServletRequest) request;
                            HttpServletResponse res = (HttpServletResponse) response;
                            HttpSession session = req.getSession();
                            session.setAttribute("error", "Expired access token, please re-sign in");
                            res.sendRedirect(req.getContextPath() + "/signin");
                            return;
                        case 1:
                            if (!object.containsKey("id"))
                                break;
                            long id = (long)object.get("id");
                            User user = service.getStackExchangePort().getUser((int)id);
                            if (user != null)
                                request.setAttribute("user", user);
                            break;
                    }
                }
            }
        }
        chain.doFilter(request, response);
    }

}
