/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wbd.identity.client;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 * Jersey REST client generated for REST resource:TokenRESTFacade
 * [wbd.identity.token]<br>
 * USAGE:
 * <pre>
 *        TokenClient client = new TokenClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Afrizal
 */
public class TokenClient {

  private final WebTarget webTarget;
  private final Client client;
  private static final String BASE_URI = "http://localhost:8082/IS/webresources";

  public TokenClient() {
    client = javax.ws.rs.client.ClientBuilder.newClient();
    webTarget = client.target(BASE_URI).path("wbd.identity.token");
  }

  public Response edit(Object requestEntity) throws ClientErrorException {
    return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), Response.class);
  }

  public <T> T find(Class<T> responseType, String id) throws ClientErrorException {
    WebTarget resource = webTarget;
    resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
    return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
  }

  public <T> T findRange(Class<T> responseType, String max, String first) throws ClientErrorException {
    WebTarget resource = webTarget;
    resource = resource.path(java.text.MessageFormat.format("{0}/{1}", new Object[]{max, first}));
    return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
  }

  public String count() throws ClientErrorException {
    WebTarget resource = webTarget;
    resource = resource.path("count");
    return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
  }

  public Response create(Object requestEntity) throws ClientErrorException {
    return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), Response.class);
  }

  public <T> T findAll(Class<T> responseType) throws ClientErrorException {
    WebTarget resource = webTarget;
    return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
  }

  public Response remove(String id) throws ClientErrorException {
    return webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{id})).request().delete(Response.class);
  }

  public void close() {
    client.close();
  }
  
}
