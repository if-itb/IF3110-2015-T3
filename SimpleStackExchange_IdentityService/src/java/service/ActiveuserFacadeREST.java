/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Activeuser;
import entity.Registereduser;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TransactionRequiredException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author mfikria
 */
@Stateless
@Path("activeuser")
public class ActiveuserFacadeREST extends AbstractFacade<Activeuser> {

    @PersistenceContext(unitName = "SimpleStackExchange_IdentityServicePU")
    private EntityManager em;

    public ActiveuserFacadeREST() {
        super(Activeuser.class);
    }
    
    @GET
    @Path("getuid/{token}")
    @Produces(MediaType.TEXT_PLAIN)
    public int getUidByToken(@PathParam("token") String token) {
        Activeuser au= new Activeuser();
        try {
        au = (Activeuser) this.getEntityManager().createNamedQuery("Activeuser.findByToken")
                .setParameter("token", token)
                .getSingleResult();
        }catch(NoResultException e) {
            return 0;
        }
        return au.getUid();
    }
    
    @GET
    @Path("auth/{token}")
    @Produces(MediaType.TEXT_PLAIN)
    public String auth(@PathParam("token") String token) throws ParseException
    {
        Activeuser auser = new Activeuser();
        try {
            auser = (Activeuser) em.createNamedQuery("Activeuser.findByToken")
            .setParameter("token", token)
            .getSingleResult();
        }catch(NoResultException | IllegalArgumentException | EntityNotFoundException | EJBException | IndexOutOfBoundsException e){
            return "false";
        }
        
        
        // Generate timestamp
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = new Date();
        Date date2 = format.parse(format.format(auser.getCreatedtime()));
        long diff = date.getTime() - date2.getTime();
        long diffMinutes = diff / (60 * 1000) % 60;
        
        if (diffMinutes <= 2) { // check the different time between created time and current time
            return "true";
        }
        else {
            clear(token);
            return "expired"; 
        }
    }
    
    @GET
    @Path("clear/{token}")
    @Produces(MediaType.TEXT_PLAIN)
    public Boolean clear(@PathParam("token") String token)
    {
        Activeuser auser = new Activeuser();
        
        try{
            auser = (Activeuser) em.createNamedQuery("Activeuser.findByToken")
            .setParameter("token", token)
            .getSingleResult();
        }catch(NoResultException | IllegalArgumentException | EntityNotFoundException | EJBException | IndexOutOfBoundsException e){
            return false;
        }
        
        // Delete record
        try{
            super.remove(auser);
        }catch(IllegalArgumentException | TransactionRequiredException e) {
            return false;
        }
        
        return true;
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
