/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wbd.identity.service;

import java.math.BigInteger;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import javax.naming.InitialContext;
import wbd.identity.controller.TokenJpaController;
import wbd.identity.Token;
import java.net.URI;
import java.security.SecureRandom;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Afrizal
 */
@Path("wbd.identity.token")
public class TokenRESTFacade {

  private EntityManagerFactory getEntityManagerFactory() throws NamingException {
    return (EntityManagerFactory) new InitialContext().lookup("java:comp/env/persistence-factory");
  }

  private TokenJpaController getJpaController() {
    try {
      UserTransaction utx = (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
      return new TokenJpaController(utx, getEntityManagerFactory());
    } catch (NamingException ex) {
      throw new RuntimeException(ex);
    }
  }

  public TokenRESTFacade() {
  }

  @POST
  @Consumes({MediaType.APPLICATION_JSON})
  public String create(Token entity) {
        String ss="asdsas";
        SecureRandom random = new SecureRandom();
        ss = new BigInteger(130, random).toString(32); 
        entity.appendVal(ss);
        System.out.println(entity.getVal());
        long time = System.currentTimeMillis() / 1000;
        entity.setExpires((int) time+5400);
    try {
      getJpaController().create(entity);
      return parseString(entity.getVal());
    } catch (Exception ex) {
//      return entity.getVal();
      return "fail";
    }
  }


  @PUT
  @Consumes({MediaType.APPLICATION_JSON})
  public Response edit(Token entity) {
    try {
      getJpaController().edit(entity);
      return Response.ok().build();
    } catch (Exception ex) {
      return Response.notModified(ex.getMessage()).build();
    }
  }

  @DELETE
  @Path("{id}")
  public Response remove(@PathParam("id") String id) {
    try {
      getJpaController().destroy(id);
      return Response.ok().build();
    } catch (Exception ex) {
      return Response.notModified(ex.getMessage()).build();
    }
  }

  @GET
  @Path("{id}")
  @Produces({MediaType.APPLICATION_JSON})
  public Token find(@PathParam("id") String id) {
    return getJpaController().findToken(id);
  }

  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public List<Token> findAll() {
    return getJpaController().findTokenEntities();
  }

  @GET
  @Path("{max}/{first}")
  @Produces({MediaType.APPLICATION_JSON})
  public List<Token> findRange(@PathParam("max") Integer max, @PathParam("first") Integer first) {
    return getJpaController().findTokenEntities(max, first);
  }

  @GET
  @Path("count")
  @Produces(MediaType.TEXT_PLAIN)
  public String count() {
    return String.valueOf(getJpaController().getTokenCount());
  }
  public String parseString(String s){
    int i = 0;
    String a="";
    while(i<s.length()){
      if(s.charAt(i)=='#'){
        a = s.substring(0, i);
        break;
      }
      i++;
    }
    return a;
  }
}
