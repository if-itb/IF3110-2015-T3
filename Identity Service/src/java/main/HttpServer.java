package main;

import eu.bitwalker.useragentutils.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class HttpServer{
 
  public static void main (String[] args) {
//    UserAgent userAgent = UserAgent.parseUserAgentString("Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36");
//    OperatingSystem agent = userAgent.getOperatingSystem();
    UserAgent agent = UserAgent.parseUserAgentString("Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36");
    Browser browser = agent.getBrowser();
    Version version = agent.getBrowserVersion();
    OperatingSystem os = agent.getOperatingSystem();
    String browserk = browser.toString();
    System.out.println(browserk + "#" + version + "#" + os);
  }
 
}