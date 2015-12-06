package Token;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import Database.DatabaseConnect; 
import java.util.Calendar;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class Token {
    private String access_token;
    private String lifetime;
    private String address;
    private String browser;
    
    public Token(String ac_token){
        access_token = ac_token;
        String query = "SELECT * FROM token WHERE value='"+ ac_token +"'";
        PreparedStatement statement;
        DatabaseConnect dbc = new DatabaseConnect();
        try{
            statement =  dbc.getConn().prepareStatement(query);
            ResultSet rs = statement.executeQuery();
           
            if(rs.next()){
                lifetime = rs.getString("lifetime");
                address = rs.getString("address");
                browser = generateBrowser(rs.getString("browser"));
            }
            rs.close();
            statement.close();
        } catch (SQLException se){
            se.printStackTrace();
	}
    }
    
    public boolean isTokenValid(){
        
        boolean valid = false;
        
	long expire_ms = Long.parseLong(lifetime);
        
	Date dt = new Date();
        java.text.SimpleDateFormat sdf = 
                          new java.text.SimpleDateFormat("yyyyMMddHHmmss");

        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        String cur_date = sdf.format(cal.getTime());
        long cur_date_ms = Long.parseLong(cur_date.trim());
        
	if(expire_ms>cur_date_ms){
            valid = true;
	}
        return valid;
        
    }
    
    public String generateBrowser(String ac_token){
        String browser = "";
        if (ac_token.contains("msie"))
        {
            String substring=ac_token.substring(ac_token.indexOf("msie")).split(";")[0];
            browser=substring.split(" ")[0].replace("msie", "IE")+"-"+substring.split(" ")[1];
        } else if (ac_token.contains("safari") && ac_token.contains("version"))
        {
            browser=(ac_token.substring(ac_token.indexOf("Safari")).split(" ")[0]).split("/")[0]+"-"+(ac_token.substring(ac_token.indexOf("Version")).split(" ")[0]).split("/")[1];
        } else if ( ac_token.contains("opr") || ac_token.contains("opera"))
        {
            if(ac_token.contains("opera"))
                browser=(ac_token.substring(ac_token.indexOf("Opera")).split(" ")[0]).split("/")[0]+"-"+(ac_token.substring(ac_token.indexOf("Version")).split(" ")[0]).split("/")[1];
            else if(ac_token.contains("opr"))
                browser=((ac_token.substring(ac_token.indexOf("OPR")).split(" ")[0]).replace("/", "-")).replace("OPR", "Opera");
        } else if (ac_token.contains("chrome"))
        {
            browser=(ac_token.substring(ac_token.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
        } else if ((ac_token.indexOf("mozilla/7.0") > -1) || (ac_token.indexOf("netscape6") != -1)  || (ac_token.indexOf("mozilla/4.7") != -1) || (ac_token.indexOf("mozilla/4.78") != -1) || (ac_token.indexOf("mozilla/4.08") != -1) || (ac_token.indexOf("mozilla/3") != -1) )
        {
            //browser=(userAgent.substring(userAgent.indexOf("MSIE")).split(" ")[0]).replace("/", "-");
            browser = "Netscape-?";

        } else if (ac_token.contains("firefox"))
        {
            browser=(ac_token.substring(ac_token.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
        } else if(ac_token.contains("rv"))
        {
            browser="IE";
        } else
        {
            browser = "UnKnown, More-Info: "+ac_token;
        }
        return browser;
    }
    public String getLifetime() {
        return lifetime;
    }
    public String getBrowser(){
        return browser;
    }
    public String getAddress(){
        return address;
    }
}
