
import java.util.UUID;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nim_13512501
 */
public class AccessToken{
    private UUID uuid;
    private String user_agent;
    private String user_ip;
    
    public AccessToken(String user_agent, String user_ip){
        this.uuid = UUID.randomUUID();
        this.user_agent = user_agent;
        this.user_ip = user_ip;
    }
    
    public AccessToken(UUID uuid, String user_agent, String user_ip){
        this.uuid = uuid;
        this.user_agent = user_agent;
        this.user_ip = user_ip;
    }
    
    //ini bikin one way?
    public String toString(){
        return getUuid().toString()+"#"+getUser_agent()+"#"+getUser_ip();
    }

    /**
     * @return the uuid
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * @return the user_agent
     */
    public String getUser_agent() {
        return user_agent;
    }

    /**
     * @return the user_ip
     */
    public String getUser_ip() {
        return user_ip;
    }
}
