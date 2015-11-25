package client;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


public class RequestHandler {
    
    private String token = "" , username = "";
    private long expirationDate = 0;
    private int id;
  
    private final HttpServletRequest request;
    private final Cookie cookies[];
    
    public RequestHandler(HttpServletRequest request){
	this.request = request;
	this.cookies = this.request.getCookies();
    }
    
    public boolean isHasToken() {
	try{
	    for (Cookie cookie : this.cookies) {
		if ("token".equals(cookie.getName())) {
		    this.token = cookie.getValue();
		}
		if ("username".equals(cookie.getName())) {
		    this.username = cookie.getValue();
		}
		if ("id".equals(cookie.getName())) {
		    this.id = Integer.parseInt(cookie.getValue());
		}
	    }
	}
	catch(NullPointerException e) {
	    System.out.println("COOKIE IS EMPTY");
	}
	
	return (!this.token.isEmpty() );
    }
    
    public String getToken() {
	return this.token;
    }
    
    public int getId() {
	return this.id;
    }
    
    public String getUsername() {
	return this.username;
    }
    
    public Long getExpirationDate() {
	return this.expirationDate;
    }
    
    public HttpServletRequest getRequest(){
	return this.request;
    }
    
    
    
}
