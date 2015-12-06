package org.stackexchange.identityservice.services;

import org.stackexchange.identityservice.dao.UserDao;
import org.stackexchange.identityservice.model.User;

public class UserService {
    public boolean emailExist(String email) {
        UserDao userDao = new UserDao();
        return userDao.emailExist(email);
    }

    public boolean emailPasswordValid(String email, String password) {
        UserDao userDao = new UserDao();
        User user = userDao.getByEmail(email);
        if (user == null) {
            return false;
        } else if(user.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }
}
