/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
/**
 *
 * @author dancinggrass
 */


public class CORSFilter implements Filter {

	public CORSFilter() { }

	public void init(FilterConfig fConfig) throws ServletException { }

	public void destroy() {	}

	public void doFilter(
		ServletRequest request, ServletResponse response, 
		FilterChain chain) throws IOException, ServletException {
            
                System.out.println("ASEM!");

		((HttpServletResponse)response).addHeader("Access-Control-Allow-Origin", "*");
                ((HttpServletResponse)response).addHeader("Access-Control-Allow-Credentials", "true");
                ((HttpServletResponse)response).addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
                ((HttpServletResponse)response).addHeader("Access-Control-Allow-Headers", "Content-Type, Accept");
		chain.doFilter(request, response);
	}
}