
package User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10-b140803.1500
 * Generated source version: 2.2
 * 
 */
@WebService(name = "UserWS", targetNamespace = "http://UserModule/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface UserWS {


    /**
     * 
     * @param password
     * @param name
     * @param email
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "InsertUser")
    @WebResult(name = "Status", targetNamespace = "")
    @RequestWrapper(localName = "InsertUser", targetNamespace = "http://UserModule/", className = "User.InsertUser")
    @ResponseWrapper(localName = "InsertUserResponse", targetNamespace = "http://UserModule/", className = "User.InsertUserResponse")
    @Action(input = "http://UserModule/UserWS/InsertUserRequest", output = "http://UserModule/UserWS/InsertUserResponse")
    public String insertUser(
        @WebParam(name = "email", targetNamespace = "")
        String email,
        @WebParam(name = "name", targetNamespace = "")
        String name,
        @WebParam(name = "password", targetNamespace = "")
        String password);

}
