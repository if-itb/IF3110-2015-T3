



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aidin
 */
public class TokenGenerator {
    public TokenGenerator(){
        
    }
    
    public String generateToken(String[] param){
        String str="";
        if(param.length==0){
            
        } else {
            SessionIdentifierGenerator strGen = new SessionIdentifierGenerator();
            String random = strGen.nextSessionId();
            str=random;
            for (int i=0; i<param.length; i++){
                str+="$"+param[i];
            }
            str+="$";
        }
        return str;
    } 
}
