



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aidin
 */
public class TokenExtractor {
    public TokenExtractor(){
        
    }
//    bdadiasb$dsavudvua$hbuv$
    public String[] getParams(String token, int nParam){
        String[] params=new String[nParam];
        int i=0;
         
        while(token.charAt(i)!='$'){
            i++;//skip random   
        }
        
        for(int j=0; j< nParam; j++){
            int k=i+1;
            i=k;
            while(token.charAt(i)!='$'){
                i++;
            }
            params[j]=token.substring(k,i);
        }
        return params;
    }
}
