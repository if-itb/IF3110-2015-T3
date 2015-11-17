package client;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


public class RequestHandler {
    
    private String token , username;
    private Long expirationDate ;
    private final HttpServletRequest request;
    
    public RequestHandler(HttpServletRequest request){
	this.request = request;
    }
    
    public boolean isAuthenticated() {
	Cookie cookies[] = this.request.getCookies();
	for (Cookie cookie : cookies) {
	    if ("expirationDate".equals(cookie.getName())) {
		this.expirationDate = Long.parseLong(cookie.getValue());
	    }
	    if ("token".equals(cookie.getName())) {
		this.token = cookie.getValue();
	    }
	    if ("username".equals(cookie.getName())) {
		this.username = cookie.getValue();
	    }
	}
	return (this.token != null && this.expirationDate != null);
    }
    
    public String getToken() {
	return this.token;
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
