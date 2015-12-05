/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TokenOperation;

/**
 *
 * @author Aidin
 */

import java.sql.*;

public class TokenAdapter {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/stackexchange";
    
    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    
    public TokenAdapter(){

    }
   
    public Token getTokenToken(String tokenStr)throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Token token=new Token();
        
        try{
            Class.forName(JDBC_DRIVER).newInstance();
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.prepareStatement("SELECT * FROM Token WHERE token_string=?");
            stmt.setString(1, tokenStr);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String email = rs.getString("email");
                Timestamp expiredDate = rs.getTimestamp("expired_date");
                token.setEmail(email);
                token.setTokenStr(tokenStr);
                token.setExpired(expiredDate);
            }
            //test=rs.toString();            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
        } finally {
          if(rs!= null) rs.close();
          if(stmt!= null) stmt.close();
        }
        
        return token;
    }
    
    public Token getTokenEmail(String email)throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Token token=new Token();
        
        try{
            Class.forName(JDBC_DRIVER).newInstance();
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.prepareStatement("SELECT * FROM Token WHERE email=?");
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Timestamp expiredDate = rs.getTimestamp("expired_date");
                String tokenStr = rs.getString("token_string");
                token.setEmail(email);
                token.setTokenStr(tokenStr);
                token.setExpired(expiredDate);
            }
            //test=rs.toString();            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
        } finally {
          if(rs!= null) rs.close();
          if(stmt!= null) stmt.close();
        }
        
        return token;
    }
    
    public int UpdateToken(Token token)throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        int result = -1;
        
        try{
            Class.forName(JDBC_DRIVER).newInstance();
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.prepareStatement("UPDATE token SET token_string=?, expired_date=? WHERE email=?");
            stmt.setString(1, token.getTokenStr());
            stmt.setTimestamp(2, token.getExpired());
            stmt.setString(3, token.getEmail());
            result = stmt.executeUpdate();
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
        } finally {
            try { if (stmt != null) stmt.close(); } catch (SQLException e) {}
            try { if (conn != null) conn.close(); } catch (SQLException e) {}
        }
        return result;
    }
    
    public int InsertToken(Token token)throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        int result = -1;
        
        try{
            Class.forName(JDBC_DRIVER).newInstance();
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.prepareStatement("INSERT token SET email=?, token_string=?, expired_date=?");
            stmt.setString(1, token.getEmail());
            stmt.setString(2, token.getTokenStr());
            stmt.setTimestamp(3, token.getExpired());
            result = stmt.executeUpdate();
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
        } finally {
            try { if (stmt != null) stmt.close(); } catch (SQLException e) {}
            try { if (conn != null) conn.close(); } catch (SQLException e) {}
        }
        
        return result;
    }
    
    
}




