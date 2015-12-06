package org.stackexchange.webservice.dao;

import org.stackexchange.webservice.core.MySQLDao;
import org.stackexchange.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao extends MySQLDao {

    public void insertUser(String name, String email, String password) {
        String queryString = "INSERT INTO `user` (`fullname`, `email`, `password`) VALUES('" + name + "', '" + email + "', '" + password + "')";
        Statement statement;

        try {
            getConnection();
            statement = conn.createStatement();
            statement.execute(queryString);

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getById(long id) {
        String query = "SELECT * FROM `user` WHERE id=" + id;
        Statement statement;

        try {
            getConnection();
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            User user = null;
            if (rs.next()) {
                user= new User(rs.getInt("id"), rs.getString("fullname"), rs.getString("email"), rs.getString("password"));
            }
            rs.close();
            statement.close();

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean emailExist(String email) {
        String queryString = "SELECT * FROM `user` WHERE email='" + email + "'";
        Statement statement;
        boolean exist = false;

        try {
            getConnection();
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(queryString);
            if (rs.next()) {
                exist = true;
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exist;
    }

    public User getByEmail(String email) {
        String queryString = "SELECT * FROM `user` WHERE email='" + email + "'";
        Statement statement;

        try {
            getConnection();
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(queryString);

            User user = null;
            if (rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("fullname"), rs.getString("email"), rs.getString("password"));
            }
            rs.close();
            statement.close();

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
