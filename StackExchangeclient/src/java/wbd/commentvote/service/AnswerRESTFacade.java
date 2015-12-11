/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wbd.commentvote.service;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import javax.naming.InitialContext;
import wbd.commentvote.controller.AnswerJpaController;
import wbd.commentvote.Answer;
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
 * @author gazandic
 */
@Path("wbd.commentvote.answer")
public class AnswerRESTFacade {

  private EntityManagerFactory getEntityManagerFactory() throws NamingException {
    return (EntityManagerFactory) new InitialContext().lookup("java:comp/env/persistence-factory");
  }

  private AnswerJpaController getJpaController() {
    try {
      UserTransaction utx = (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
      return new AnswerJpaController(utx, getEntityManagerFactory());
    } catch (NamingException ex) {
      throw new RuntimeException(ex);
    }
  }

  public AnswerRESTFacade() {
  }

  @POST
  @Consumes({MediaType.APPLICATION_JSON})
  public Response create(Answer entity) {
    try {
      getJpaController().create(entity);
      return Response.created(URI.create(entity.getId().toString())).build();
    } catch (Exception ex) {
      return Response.notModified(ex.getMessage()).build();
    }
  }

  @PUT
  @Consumes({MediaType.APPLICATION_JSON})
  public Response edit(Answer entity) {
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
  public Answer find(@PathParam("id") Integer id) {
    return getJpaController().findAnswer(id);
  }

  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public List<Answer> findAll() {
    return getJpaController().findAnswerEntities();
  }

  @GET
  @Path("{max}/{first}")
  @Produces({MediaType.APPLICATION_JSON})
  public List<Answer> findRange(@PathParam("max") Integer max, @PathParam("first") Integer first) {
    return getJpaController().findAnswerEntities(max, first);
  }

  @GET
  @Path("count")
  @Produces("text/plain")
  public String count() {
    return String.valueOf(getJpaController().getAnswerCount());
  }
  
}
