
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

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
            return getEmail(access_token, user_agent, user_ip);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    private String emailError;
    private String getEmail(String access_token, String user_agent, String user_ip) throws MalformedURLException, IOException, JAXBException, XPathExpressionException, ParserConfigurationException, SAXException{
        String uri =
            "http://localhost:8082/Identity_Service/Login?access_token="+URLEncoder.encode(access_token, "UTF-8")
                +"&user_agent="+URLEncoder.encode(user_agent,"UTF-8")
                +"&user_ip="+URLEncoder.encode(user_ip,"UTF-8");
        URL url = new URL(uri);
        HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        
        switch(connection.getResponseCode()){
            case HttpURLConnection.HTTP_OK:
                InputStream xmlstream = connection.getInputStream();
                StringBuilder inputStringBuilder = new StringBuilder();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(xmlstream, "UTF-8"));
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(xmlstream);
                Element rootElement = document.getDocumentElement();
                System.out.println("test " + rootElement.getTagName() + " = " + rootElement.getTextContent());
                return rootElement.getTextContent();
            case HttpURLConnection.HTTP_NOT_FOUND:
            case HttpURLConnection.HTTP_UNAUTHORIZED:
                InputStream textstream = connection.getErrorStream();
                Scanner sc = new Scanner(textstream);
                emailError = "";
                while (sc.hasNext()){
                    emailError += sc.nextLine() + "\n";
                }
                return null;
        }
        return null;
    }

    /**
     * @return the emailError
     */
    public String getEmailError() {
        return emailError;
    }
}
