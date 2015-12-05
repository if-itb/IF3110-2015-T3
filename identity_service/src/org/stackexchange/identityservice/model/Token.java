package org.stackexchange.identityservice.model;

public class Token {

    private long id;

    private String token;

    private long userId;

    private String ip;

    private String user_agent;

    public Token(long id, String token, long userId, String ip, String user_agent) {
        this.id = id;
        this.token = token;
        this.userId = userId;
        this.ip = ip;
        this.user_agent = user_agent;
    }

    public String getToken() {
        return token;
    }

    public long getUserId() {
        return userId;
    }

    public String getIp(){
        return ip;
    }

    public String getUserAgent(){
        return user_agent;
    }
}
