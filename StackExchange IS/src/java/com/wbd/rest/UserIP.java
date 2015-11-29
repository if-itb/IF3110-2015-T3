/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wbd.rest;

import java.io.IOException;
import java.io.PrintWriter;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alberttriadrian
 */
public class UserIP {
    private String ipAddress;
    
    public String getIPAddress(){
        return ipAddress;
    }
    
    public void requestIPAddress() throws ServletException, IOException {
    
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                
        response.setContentType("text/html;charset=UTF-8");
        String _ipAddress = request.getHeader("X-FORWARDED-FOR");
        
        if (_ipAddress == null) {
            _ipAddress = request.getRemoteAddr();
        }
        
        ipAddress = _ipAddress;              
    }

}
