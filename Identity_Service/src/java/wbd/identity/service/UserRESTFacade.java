/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wbd.identity.service;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import javax.naming.InitialContext;
import wbd.identity.controller.UserJpaController;
import wbd.identity.User;
import java.net.URI;
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
@Path("wbd.identity.user")
public class UserRESTFacade {

  private EntityManagerFactory getEntityManagerFactory() throws NamingException {
    return (EntityManagerFactory) new InitialContext().lookup("java:comp/env/persistence-factory");
  }

  private UserJpaController getJpaController() {
    try {
      UserTransaction utx = (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
      return new UserJpaController(utx, getEntityManagerFactory());
    } catch (NamingException ex) {
      throw new RuntimeException(ex);
    }
  }

  public UserRESTFacade() {
  }

  @POST
  @Consumes({MediaType.APPLICATION_JSON})
  public Response create(User entity) {
    try {
      getJpaController().create(entity);
      return Response.created(URI.create(entity.getId().toString())).build();
    } catch (Exception ex) {
      return Response.notModified(ex.getMessage()).build();
    }
  }

  @PUT
  @Consumes({MediaType.APPLICATION_JSON})
  public Response edit(User entity) {
    try {
      getJpaController().edit(entity);
      return Response.ok().build();
    } catch (Exception ex) {
      return Response.notModified(ex.getMessage()).build();
    }
  }

  @DELETE
  @Path("{id}")
  public Response remove(@PathParam("id") Integer id) {
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
  public User find(@PathParam("id") Integer id) {
    return getJpaController().findUser(id);
  }

  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public List<User> findAll() {
    return getJpaController().findUserEntities();
  }

  @GET
  @Path("{max}/{first}")
  @Produces({MediaType.APPLICATION_JSON})
  public List<User> findRange(@PathParam("max") Integer max, @PathParam("first") Integer first) {
    return getJpaController().findUserEntities(max, first);
  }

  @GET
  @Path("count")
  @Produces(MediaType.TEXT_PLAIN)
  public String count() {
    return String.valueOf(getJpaController().getUserCount());
  }
  
}
