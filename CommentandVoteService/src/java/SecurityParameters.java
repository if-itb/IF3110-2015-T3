
import java.io.IOException;
import java.net.MalformedURLException;
import javax.servlet.http.HttpServletRequest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nim_13512501
 */
public class SecurityParameters {
    private String access_token;
    private String user_agent;
    private String user_ip;
    
    public SecurityParameters( String access_token, String user_agent, String user_ip){
        this.access_token=access_token;
        this.user_agent=user_agent;
        this.user_ip=user_ip;
    }
    
    public static SecurityParameters getSecurityParameters(HttpServletRequest request){
        String access_token = request.getParameter("access_token");
        String user_agent = request.getHeader("User-Agent");
        String user_ip = request.getRemoteAddr();
        
        //khusus dari frontend server side belum
        
        return new SecurityParameters(access_token, user_agent, user_ip);
    }

    /**
     * @return the access_token
     */
    public String getAccess_token() {
        return access_token;
    }

    /**
     * @return the user_agent
     */
    public String getUser_agent() {
        return user_agent;
    }

    /**
     * @return the user_ip
     */
    public String getUser_ip() {
        return user_ip;
    }
    
    public String getEmail(){
        try{
            return IdentityService.getEmail(access_token, user_agent, user_ip);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
