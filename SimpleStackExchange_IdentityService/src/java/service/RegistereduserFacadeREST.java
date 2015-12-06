/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Activeuser;
import entity.Registereduser;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;

/**
 *
 * @author mfikria
 */
@Stateless
@Path("user")
public class RegistereduserFacadeREST extends AbstractFacade<Registereduser> {

    @PersistenceContext(unitName = "SimpleStackExchange_IdentityServicePU")
    private EntityManager em;

    public RegistereduserFacadeREST() {
        super(Registereduser.class);
    }
    
    /*
    @GET
    @Path("email/{email}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    */
    public Registereduser findByEmail(@PathParam("email") String email) {
        return (Registereduser) this.getEntityManager().createNamedQuery("Registereduser.findByEmail")
                .setParameter("email", email)
                .getResultList().get(0);
    }
    
    @POST
    @Path("validate")
    @Produces(MediaType.TEXT_PLAIN)
    public String validate(
            @FormParam("email") String email, 
            @FormParam("password") String password,
            @FormParam("browser") String browser,
            @FormParam("ip")String ip
    ) throws JAXBException, IOException {
        Registereduser user;
        try {
            user = new Registereduser(this.findByEmail(email));
        }catch(IllegalArgumentException | EntityNotFoundException | EJBException | IndexOutOfBoundsException e){
            return "";
        }
            if(user.getPassword().equals(password)) {
 
                // generate token
                String token = UUID.randomUUID().toString().replaceAll("-", "");
               
                // Generate timestamp
                Date date = new Date();
                Timestamp timestamp = new Timestamp(date.getTime());
                
                // concate token
                token =  new StringBuilder(token)
                        .append("_")
                        .append(browser)
                        .append("_")
                        .append(ip)
                        .toString();
                
                        
                // Create Activeuser object
                Activeuser auser = new Activeuser(token, user.getUid(), date);
              
               // Insert token authentication to database
               em.persist(auser);
  
               // return auserFacadeREST.countREST();
               return token;
            }
            else return "";
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
