/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserOperator;

/**
 *
 * @author Aidin
 */

public class User {
    
    private String email;
    private String pass;
    
    public User(){
        email= pass= "";
    }
    
    public User(String _email, String _pass){
        
        email = _email;
        pass =_pass;
    }
    

    public String getEmail(){
        return email;
    } 
    
    public String getPass(){
        return pass;
    }
    
    
    public void setEmail(String _email){
        email=_email;
    }
    
    public void setPass(String _pass){
        pass=_pass;
    }
    
    public boolean isEqual(User A){
        return ( email.equals(A.email) && pass.equals(A.pass));
    }
    
    
}
