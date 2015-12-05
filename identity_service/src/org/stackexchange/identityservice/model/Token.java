package org.stackexchange.identityservice.model;

public class Token {

    private long id;

    private String token;

    private long userId;

    public Token(long id, String token, long userId) {
        this.id = id;
        this.token = token;
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public long getUserId() {
        return userId;
    }
}
