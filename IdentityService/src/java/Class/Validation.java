/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Validation {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/stack";
    
    static final String USER = "root";
    static final String PASS = "" ;
    
    
    public Validation() {}
    
    public int ExistInDB(String name,String password) {
        int status = -1;
        Connection conn =null;
        String names ="";
                String sql ="";
        Statement stmt = null;
        ResultSet rs = null;
        try {
                Class.forName("com.mysql.jdbc.Driver");                
                conn = DriverManager.getConnection(DB_URL, USER,PASS);               
                sql = "SELECT * FROM registered WHERE (username='"+name+"' or email='"+name+"') and password='"+password+"'";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                rs.last() ;
                int size =rs.getRow();
                if (size!=0) {
                    status = rs.getInt("user_id");
                    names = rs.getString("username");
                }
                stmt.close();
                conn.close();
            }
            catch (Exception e) {
                e.printStackTrace() ;
            }
      
        return status;
    }
    
    public int isTokenValid (String s,String ipa,String browser) {
        int status = -1 ; //token tidak ada atau tidak valid
         Connection conn =null;
        String exp ="";
        String sql ="";
        Statement stmt = null;
        ResultSet rs = null;
        try {
                Class.forName("com.mysql.jdbc.Driver");                
                conn = DriverManager.getConnection(DB_URL, USER,PASS);               
                sql = "SELECT * FROM token WHERE token='"+s+"'";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                rs.last() ;
                int size =rs.getRow();
                System.out.println("isTokenValid sql : "+sql);
                System.out.println("isTokenValid size : "+size);
                if (size!=0) {
                    exp = rs.getString("expire");
                    status = rs.getInt("id_user");// token ada (belum tau expire atau nggak)
                    
                }
                stmt.close();
                conn.close();
            }
            catch (Exception e) {
                e.printStackTrace() ;
            }
        if (status!=-1) {
            long now = System.currentTimeMillis()/1000;
            int exp_d = Integer.valueOf(exp);
            if (now > exp_d) {
                status = -2 ; //Token expired               
            } else {
                String a[] = s.split(",");
                if (!a[1].contains(ipa)) {
                    status=-3;
                } else if (!a[2].contains(browser)) {
                    status=-4;
                }
            }            
        }
        System.out.println("isTokenValid : ");
        System.out.println("  "+s+"|"+status);
        return status ;
    }
    
    public String getUsername(int id) {
        String u="";
        Connection conn =null;
        String sql ="";
        Statement stmt = null;
        ResultSet rs = null;
        try {
                Class.forName("com.mysql.jdbc.Driver");                
                conn = DriverManager.getConnection(DB_URL, USER,PASS);               
                sql = "SELECT username FROM registered WHERE user_id="+String.valueOf(id);
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                rs.last() ;
                int size =rs.getRow();
                if (size!=0) {                    
                    u = rs.getString("username");
                }
                stmt.close();
                conn.close();
            }
            catch (Exception e) {
                e.printStackTrace() ;
            }
        return u;
    }
    
}
