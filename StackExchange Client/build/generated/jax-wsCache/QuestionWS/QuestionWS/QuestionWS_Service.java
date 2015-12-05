
package QuestionWS;

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
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "QuestionWS", targetNamespace = "http://controllers/", wsdlLocation = "http://localhost:8081/StackExchange_WebService/QuestionWS?wsdl")
public class QuestionWS_Service
    extends Service
{

    private final static URL QUESTIONWS_WSDL_LOCATION;
    private final static WebServiceException QUESTIONWS_EXCEPTION;
    private final static QName QUESTIONWS_QNAME = new QName("http://controllers/", "QuestionWS");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8081/StackExchange_WebService/QuestionWS?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        QUESTIONWS_WSDL_LOCATION = url;
        QUESTIONWS_EXCEPTION = e;
    }

    public QuestionWS_Service() {
        super(__getWsdlLocation(), QUESTIONWS_QNAME);
    }

    public QuestionWS_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), QUESTIONWS_QNAME, features);
    }

    public QuestionWS_Service(URL wsdlLocation) {
        super(wsdlLocation, QUESTIONWS_QNAME);
    }

    public QuestionWS_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, QUESTIONWS_QNAME, features);
    }

    public QuestionWS_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public QuestionWS_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns QuestionWS
     */
    @WebEndpoint(name = "QuestionWSPort")
    public QuestionWS getQuestionWSPort() {
        return super.getPort(new QName("http://controllers/", "QuestionWSPort"), QuestionWS.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns QuestionWS
     */
    @WebEndpoint(name = "QuestionWSPort")
    public QuestionWS getQuestionWSPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://controllers/", "QuestionWSPort"), QuestionWS.class, features);
    }

    private static URL __getWsdlLocation() {
        if (QUESTIONWS_EXCEPTION!= null) {
            throw QUESTIONWS_EXCEPTION;
        }
        return QUESTIONWS_WSDL_LOCATION;
    }

}
