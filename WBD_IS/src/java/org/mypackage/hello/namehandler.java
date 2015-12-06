/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mypackage.hello;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author user
 */
public class namehandler {
    private String nama;
    public namehandler() {nama = null;};

    /**
     * @return the name
     */
    public String getNama() {
        return nama;
    }

    /**
     * @param nama the name to set
     */
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    
}
