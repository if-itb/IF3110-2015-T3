/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

/**
 * Jersey REST client generated for REST resource:RegistereduserFacadeREST
 * [user]<br>
 * USAGE:
 * <pre>
 *        ConsumerREST client = new ConsumerREST();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author mfikria
 */
public class ConsumerREST {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8082/SimpleStackExchange_IdentityService/api";
    
    public ConsumerREST() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("user");
    }
    
    public Integer auth(String token) throws ClientErrorException {
        webTarget = client.target(BASE_URI).path("activeuser");
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("auth/{0}", new Object[]{token}));
        String result = resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
        if(result.equalsIgnoreCase("true"))
            return 1;
        else if(result.equalsIgnoreCase("expired"))
            return -1;
        else
            return 0;
    }
    
    public Boolean clear(String token) throws ClientErrorException {
        webTarget = client.target(BASE_URI).path("activeuser");
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("clear/{0}", new Object[]{token}));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(Boolean.class);
    }
    
    public int getUidByToken(String token) throws ClientErrorException {
        webTarget = client.target(BASE_URI).path("activeuser");
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getuid/{0}", new Object[]{token}));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(Integer.class);
    }
    
    public String validate(String email, String password, String browser, String ip) throws ClientErrorException {
        Form form = new Form();
        form.param("email", email);
        form.param("password", password);
        form.param("browser", browser);
        form.param("ip", ip);
        return webTarget.path("validate").request(MediaType.TEXT_PLAIN).post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);
    }

    public void close() {
        client.close();
    }
}
