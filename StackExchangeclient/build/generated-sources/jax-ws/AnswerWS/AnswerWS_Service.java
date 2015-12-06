
package AnswerWS;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10-b140803.1500
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "AnswerWS", targetNamespace = "http://jaxws/", wsdlLocation = "http://localhost:8081/Web_Service/AnswerWS?wsdl")
public class AnswerWS_Service
    extends Service
{

    private final static URL ANSWERWS_WSDL_LOCATION;
    private final static WebServiceException ANSWERWS_EXCEPTION;
    private final static QName ANSWERWS_QNAME = new QName("http://jaxws/", "AnswerWS");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8081/Web_Service/AnswerWS?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        ANSWERWS_WSDL_LOCATION = url;
        ANSWERWS_EXCEPTION = e;
    }

    public AnswerWS_Service() {
        super(__getWsdlLocation(), ANSWERWS_QNAME);
    }

    public AnswerWS_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    /**
     * 
     * @return
     *     returns AnswerWS
     */
    @WebEndpoint(name = "AnswerWSPort")
    public AnswerWS getAnswerWSPort() {
        return super.getPort(new QName("http://jaxws/", "AnswerWSPort"), AnswerWS.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns AnswerWS
     */
    @WebEndpoint(name = "AnswerWSPort")
    public AnswerWS getAnswerWSPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://jaxws/", "AnswerWSPort"), AnswerWS.class, features);
    }

    private static URL __getWsdlLocation() {
        if (ANSWERWS_EXCEPTION!= null) {
            throw ANSWERWS_EXCEPTION;
        }
        return ANSWERWS_WSDL_LOCATION;
    }

}
