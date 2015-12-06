/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Comment;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author mfikria
 */
@Stateless
@Path("comment")
public class CommentFacadeREST extends AbstractFacade<Comment> {

    @PersistenceContext(unitName = "SimpleStackExchange_CommentVoteServicePU")
    private EntityManager em;

    public CommentFacadeREST() {
        super(Comment.class);
    }

    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public void create(
            @FormParam("content") String content,
            @FormParam("qid") int qid,
            @FormParam("uid") int uid) {
        Comment comment = new Comment(0, qid, uid, content, new Date());
       
        
        super.create(comment);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Comment entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Comment find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Comment> findAll() {
        return super.findAll();
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
