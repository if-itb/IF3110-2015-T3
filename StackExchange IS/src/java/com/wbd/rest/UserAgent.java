/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wbd.rest;

import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alberttriadrian
 */
public class UserAgent {
    private String agent;

    public UserAgent(){
        agent= "";
    }
    
    public String getAgent(){
        return agent;
    }
    
    public void requestUserAgent() throws ServletException, IOException {
        System.out.println("Request = ");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        String  browserDetails  =   request.getHeader("User-Agent");
        agent = browserDetails;              
    }

}

