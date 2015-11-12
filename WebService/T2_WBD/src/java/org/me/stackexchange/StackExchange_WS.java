/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.stackexchange;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import java.sql.*;
import javax.sql.*;

/**
 *
 * @author Vanji
 */
@WebService(serviceName = "StackExchange_WS")
@SOAPBinding(style = Style.RPC)
public class StackExchange_WS {

    /**
     * Register Method
     */
    @WebMethod(operationName = "register")
    public String register(@WebParam(name = "nama") String nama, @WebParam(name = "email") String email, @WebParam(name = "password") String password) {
        
        return null;
    }
    
     public int getUserCount() { 
		int numusers = 0;
		String dbUrl = "jdbc:mysql://localhost/yourdbname";
		String dbClass = "com.mysql.jdbc.Driver";
		String query = "Select count(*) FROM users";
		String userName = "root", password = "yourdbpassword";
		try {

		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection (dbUrl, userName, password);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);

		while (rs.next()) {
			numusers = rs.getInt(1);
			} //end while
			con.close();
		} //end try

		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}

		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			
			return numusers;
		}

	}
    
}
