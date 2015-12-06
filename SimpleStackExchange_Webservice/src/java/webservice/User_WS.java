/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import entity.Registereduser;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author mfikria
 */
@WebService(serviceName = "User_WS")
public class User_WS {

     /**
     * Web service operation
     */
    @WebMethod(operationName = "createUser")
    public void createUser(@WebParam(name = "user") Registereduser user) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SimpleStackExchange_WebServicePU");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        try {
            
            em.persist(user);
            
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        
        emf.close();
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "getUserById")
    public Registereduser getUserById(@WebParam(name = "uid") int uid) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SimpleStackExchange_WebServicePU");
        EntityManager em = emf.createEntityManager();
        return em.find(Registereduser.class, uid);
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "checkEmailUser")
    public Boolean checkEmailUser(@WebParam(name = "email") String email) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SimpleStackExchange_WebServicePU");
        EntityManager em = emf.createEntityManager();
        if (em.createNamedQuery("Registereduser.findByEmail")
                .setParameter("email", email)
                .getResultList().isEmpty()) {
            return true;
        }
        else return false;
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "getNameByUid")
    public String getNameByUid(@WebParam(name = "uid") int uid) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SimpleStackExchange_WebServicePU");
        EntityManager em = emf.createEntityManager();
        String name = (String)em.createQuery("SELECT u.name FROM Registereduser u WHERE u.uid="+uid, String.class).getSingleResult();
        return name;
    }
}
