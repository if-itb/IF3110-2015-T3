package util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by elvan_owen on 11/17/15.
 */
public class XmlParser {
    public static ArrayList< HashMap<String, String> > parse(String requestResponse, ArrayList<String> attributes){

        ArrayList< HashMap<String, String> > out = null;

        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(requestResponse));

            Document doc = builder.parse(is);

            NodeList returns = doc.getElementsByTagName("return");

            out = new ArrayList< HashMap<String, String> >();

            for (int temp = 0; temp < returns.getLength(); temp++) {

                Node currentNode = returns.item(temp);

                if (currentNode.getNodeType() == Node.ELEMENT_NODE) {

                    HashMap<String, String> hMap = new HashMap<String, String>();
                    Element eElement = (Element) currentNode;

                    for (int i = 0; i < attributes.size(); i++) {
                        hMap.put(attributes.get(i), eElement.getElementsByTagName(attributes.get(i)).item(0).getTextContent());
                    }

                    out.add(hMap);
                }
            }
        } catch(Exception e){

        }

        return out;
    }

    public static String checkResponse(String requestResponse){

        String returns = "";

        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(requestResponse));

            Document doc = builder.parse(is);

            returns = doc.getElementsByTagName("return").item(0).getTextContent();
        } catch(Exception e){

        }

        System.out.println(requestResponse);

        if (returns.equalsIgnoreCase("success") || (!returns.equalsIgnoreCase("error") && Integer.parseInt(returns) >= 0)) {
            return "success";
        } else if (returns.equals("-1")) {
            return "expired";
        } else if (returns.equals("-2")) {
            return "invalid";
        } else if (returns.equalsIgnoreCase("ERROR") || returns.equals("-3")) {
            return "error";
        }

        return "";
    }
}
