/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userOperation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Asus
 */
@WebService(serviceName = "userWS")
public class userWS {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
    @WebMethod(operationName = "createUser")
    public int createUser(@WebParam(name = "nama") String nama, @WebParam(name = "email") String email, @WebParam(name = "password") String pass) {
        Connection conn = null;
        PreparedStatement ps = null;
        int executeUpdate = -2;
        try {
                //new com.mysql.jdbc.Driver();
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/databasename?user=username&password=password");
                String connectionUrl = "jdbc:mysql://localhost:3306/stackexchange";
                String connectionUser = "root";
                String connectionPassword = "";
                conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
                ps = conn.prepareStatement("select * from user where email = ?");
                ps.setString(1, email);
                ResultSet rs1 = ps.executeQuery();
                
                if (rs1.next()) {
                    return -1;
                } else {
                    ps = conn.prepareStatement("insert into user values(?,?,?)");         
                    ps.setString(1, nama);
                    ps.setString(2, email);
                    ps.setString(3, pass);
                    executeUpdate = ps.executeUpdate();
                }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            return 0;
        } finally {
                try { if (ps != null) ps.close(); } catch (SQLException e) {}
                try { if (conn != null) conn.close(); } catch (SQLException e) {}
        }
        return executeUpdate;
    }
    @WebMethod(operationName = "getName")
    public String getName(@WebParam(name = "email") String email) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String name = "";
        try {
                //new com.mysql.jdbc.Driver();
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/databasename?user=username&password=password");
                String connectionUrl = "jdbc:mysql://localhost:3306/stackexchange";
                String connectionUser = "root";
                String connectionPassword = "";
                conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
                ps = conn.prepareStatement("select name from answer user where email = ?");
                ps.setString(1, email);
                rs = ps.executeQuery();
                rs.next();
                name = rs.getString("name");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
        } finally {
            try {
                if(rs!= null) rs.close();
                if(ps!= null) ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(userWS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return name;
     
    }
}
