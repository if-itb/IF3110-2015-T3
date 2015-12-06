package Login;

import Database.DatabaseConnect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpServletRequest;


public class UserService {
    
    public static String getClientIpAddr(HttpServletRequest request) {  
        String ip = request.getHeader("X-Forwarded-For");  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_CLIENT_IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getRemoteAddr();  
        }  
        return ip;  
    }  
    
    public boolean emailExist(String email) throws SQLException{
        String query = "SELECT * FROM user WHERE email='"+ email +"'";
        PreparedStatement statement; 
        DatabaseConnect dbc = new DatabaseConnect();
        boolean exist = false;
        try{
            statement =  dbc.getConn().prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            if(rs.next())
                exist = true;
            rs.close();
            statement.close();
            
        } catch (SQLException se){
            se.printStackTrace();
	}
        return exist;
    }
    
    public boolean passwordValid(String email, String password) throws SQLException{
        String query = "SELECT * FROM user WHERE email='"+ email +"' AND password='"+ password +"'";
        boolean valid = false;
        PreparedStatement statement; 
        DatabaseConnect dbc = new DatabaseConnect();
        try{
            statement =  dbc.getConn().prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            if(rs.next())
                valid = true;
            rs.close();
            statement.close();
            
        } catch (SQLException se){
            se.printStackTrace();
	}
        return valid;
        
    }
    
    public String getTokenFromUserID(int id){
        String query = "SELECT * FROM token WHERE user_id="+ id;
        PreparedStatement statement;
        String token = new String();
        DatabaseConnect dbc = new DatabaseConnect();
        try{
            statement =  dbc.getConn().prepareStatement(query);
            ResultSet rs = statement.executeQuery();
           
            if(rs.next()){
                token = rs.getString("value"); 
            }
            else
                token = null;
            rs.close();
            statement.close();
        } catch (SQLException se){
            se.printStackTrace();
	}
        finally{
            return token;
        }
    }
    
    public int getUserIDFromEmail(String email){
        String query = "SELECT * FROM user WHERE email='"+ email +"'";
        PreparedStatement statement;
        int user_id = -1;
        DatabaseConnect dbc = new DatabaseConnect();
        try{
            statement =  dbc.getConn().prepareStatement(query);
            ResultSet rs = statement.executeQuery();
           
            if(rs.next()){
                user_id = rs.getInt("id"); 
            }
            rs.close();
            statement.close();
        } catch (SQLException se){
            se.printStackTrace();
	}
        finally{
            return user_id;
        }
    }
    
    public int getUserIDFromToken(String token){
        String query = "SELECT * FROM token WHERE value='"+ token +"'";
        PreparedStatement statement;
        int user_id = -1;
        DatabaseConnect dbc = new DatabaseConnect();
        try{
            statement =  dbc.getConn().prepareStatement(query);
            ResultSet rs = statement.executeQuery();
           
            if(rs.next()){
                user_id = rs.getInt("user_id"); 
            }
            rs.close();
            statement.close();
        } catch (SQLException se){
            se.printStackTrace();
	}
        finally{
            return user_id;
        }
    }
    
    public void executeQuery(String query){
        PreparedStatement statement;
        DatabaseConnect dbc = new DatabaseConnect();
        try{
            statement =  dbc.getConn().prepareStatement(query);
            statement.executeUpdate();
           
            statement.close();
        } catch (SQLException se){
            se.printStackTrace();
	}
    }
}
