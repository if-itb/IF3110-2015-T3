/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Fikri-PC
 */
@WebService(serviceName = "Web Pages")
public class Web 
Pages {

    /** This is a sample web service operation */
    @WebMethod(operationName = "hello")
        public String hello(@WebParam(name = "name") String txt) {
        return "Hello "+txt+" !";
    }
}
