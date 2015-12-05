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
        setAddress(ac_token);
        browser = generateBrowser(ac_token);
        PreparedStatement statement;
        DatabaseConnect dbc = new DatabaseConnect();
        try{
            statement =  dbc.getConn().prepareStatement(query);
            ResultSet rs = statement.executeQuery();
           
            if(rs.next()){
                lifetime = rs.getString("lifetime"); 
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
    
    public void setAddress(String ac_token){
        String[] splitted = ac_token.split("#");
        address =  splitted[2];
    }
    
    public String generateBrowser(String ac_token){
        String[] splitted = ac_token.split("#");
        String browser = "";
        if (splitted[1].contains("msie"))
        {
            String substring=splitted[1].substring(splitted[1].indexOf("msie")).split(";")[0];
            browser=substring.split(" ")[0].replace("msie", "IE")+"-"+substring.split(" ")[1];
        } else if (splitted[1].contains("safari") && splitted[1].contains("version"))
        {
            browser=(splitted[1].substring(splitted[1].indexOf("Safari")).split(" ")[0]).split("/")[0]+"-"+(splitted[1].substring(splitted[1].indexOf("Version")).split(" ")[0]).split("/")[1];
        } else if ( splitted[1].contains("opr") || splitted[1].contains("opera"))
        {
            if(splitted[1].contains("opera"))
                browser=(splitted[1].substring(splitted[1].indexOf("Opera")).split(" ")[0]).split("/")[0]+"-"+(splitted[1].substring(splitted[1].indexOf("Version")).split(" ")[0]).split("/")[1];
            else if(splitted[1].contains("opr"))
                browser=((splitted[1].substring(splitted[1].indexOf("OPR")).split(" ")[0]).replace("/", "-")).replace("OPR", "Opera");
        } else if (splitted[1].contains("chrome"))
        {
            browser=(splitted[1].substring(splitted[1].indexOf("Chrome")).split(" ")[0]).replace("/", "-");
        } else if ((splitted[1].indexOf("mozilla/7.0") > -1) || (splitted[1].indexOf("netscape6") != -1)  || (splitted[1].indexOf("mozilla/4.7") != -1) || (splitted[1].indexOf("mozilla/4.78") != -1) || (splitted[1].indexOf("mozilla/4.08") != -1) || (splitted[1].indexOf("mozilla/3") != -1) )
        {
            //browser=(userAgent.substring(userAgent.indexOf("MSIE")).split(" ")[0]).replace("/", "-");
            browser = "Netscape-?";

        } else if (splitted[1].contains("firefox"))
        {
            browser=(splitted[1].substring(splitted[1].indexOf("Firefox")).split(" ")[0]).replace("/", "-");
        } else if(splitted[1].contains("rv"))
        {
            browser="IE";
        } else
        {
            browser = "UnKnown, More-Info: "+splitted[1];
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
