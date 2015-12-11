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
import wbd.commentvote.controller.VoteQuestionJpaController;
import wbd.commentvote.VoteQuestion;
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
@Path("wbd.commentvote.votequestion")
public class VoteQuestionRESTFacade {

  private EntityManagerFactory getEntityManagerFactory() throws NamingException {
    return (EntityManagerFactory) new InitialContext().lookup("java:comp/env/persistence-factory");
  }

  private VoteQuestionJpaController getJpaController() {
    try {
      UserTransaction utx = (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
      return new VoteQuestionJpaController(utx, getEntityManagerFactory());
    } catch (NamingException ex) {
      throw new RuntimeException(ex);
    }
  }

  public VoteQuestionRESTFacade() {
  }

  @POST
  @Consumes({MediaType.APPLICATION_JSON})
  public Response create(VoteQuestion entity) {
    try {
      getJpaController().create(entity);
      return Response.created(URI.create(entity.getId().toString())).build();
    } catch (Exception ex) {
      return Response.notModified(ex.getMessage()).build();
    }
  }

  @PUT
  @Consumes({MediaType.APPLICATION_JSON})
  public Response edit(VoteQuestion entity) {
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
  public VoteQuestion find(@PathParam("id") Integer id) {
    return getJpaController().findVoteQuestion(id);
  }

  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public List<VoteQuestion> findAll() {
    return getJpaController().findVoteQuestionEntities();
  }

  @GET
  @Path("{max}/{first}")
  @Produces({MediaType.APPLICATION_JSON})
  public List<VoteQuestion> findRange(@PathParam("max") Integer max, @PathParam("first") Integer first) {
    return getJpaController().findVoteQuestionEntities(max, first);
  }

  @GET
  @Path("count")
  @Produces("text/plain")
  public String count() {
    return String.valueOf(getJpaController().getVoteQuestionCount());
  }
  
}
