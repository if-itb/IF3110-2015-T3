/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IdentityServiceModule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
//import jdk.internal.org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * kelas boong-boongan
 * @author nim_13512501
 */
public class IdentityService {
    public static String getEmail(String access_token) throws MalformedURLException, IOException, JAXBException, XPathExpressionException, ParserConfigurationException, SAXException{
        String uri =
            "http://localhost:8082/Identity_Service/Login?access_token="+access_token;
        URL url = new URL(uri);
        HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        
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
                return null;
            case HttpURLConnection.HTTP_UNAUTHORIZED:
                return null;
        }
        return null;
    }
}
