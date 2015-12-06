package org.stackexchange.identityservice.services;

import org.stackexchange.identityservice.dao.TokenDao;
import org.stackexchange.identityservice.model.Token;

import java.util.UUID;

public class IdentityService {

    public Token generateToken(long userId, String ip, String user_agent) {
        TokenDao tokenDao = new TokenDao();
        tokenDao.deleteByUserid(userId);

        UUID tokenString = UUID.randomUUID();
        Token token = tokenDao.insert(userId, tokenString.toString(),ip,user_agent);
        return token;
    }

}
