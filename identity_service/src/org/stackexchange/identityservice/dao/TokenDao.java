package org.stackexchange.identityservice.dao;

import org.stackexchange.identityservice.core.MySQLDao;
import org.stackexchange.identityservice.model.Token;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TokenDao extends MySQLDao {

    public boolean existByToken(String token, String ip, String user_agent) {
        String query = "SELECT * FROM `token` WHERE token='" + token + "' && ip ='" + ip + "' && user_agent='" + user_agent + "'";
        Statement statement;
        boolean exist = false;

        try {
            getConnection();
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                exist = true;
            }
            rs.close();
            statement.close();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exist;
    }

    public boolean existByUserid(long userId) {
        String query = "SELECT * FROM `token` WHERE user_id='" + userId + "'";
        Statement statement;
        boolean exist = false;

        try {
            getConnection();
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                exist = true;
            }
            rs.close();
            statement.close();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exist;
    }

    public Token getFromToken(String token, String ip, String user_agent) {
        String query = "SELECT * FROM `token` WHERE token='" + token + "' && ip ='" + ip + "' && user_agent='" + user_agent + "'";
        Statement statement;

        try {
            getConnection();
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            boolean exist = false;
            long id = 0;
            long userId = 0;
            while (rs.next()) {
                id = rs.getInt("id");
                userId = rs.getInt("user_id");
                exist = true;
            }

            rs.close();
            statement.close();
            closeConnection();
            if (exist) {
                return new Token(id, token, userId, ip, user_agent);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Token insert(long userId, String token, String ip, String user_agent) {
        String query = "INSERT INTO `token` (`user_id`, `token`, `ip`, `user_agent`) VALUES (" + userId + ", '" + token + "', '" + ip + "', '" + user_agent + "')";
        Statement statement;
        System.out.println(query);
        try {
            getConnection();
            statement = conn.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = statement.getGeneratedKeys();
            int insertedId = 0;
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }

            Token insertedToken = new Token(insertedId, token, userId, ip, user_agent);

            rs.close();
            statement.close();
            closeConnection();

            return insertedToken;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteByUserid(long userId) {
        String query = "DELETE FROM `token` WHERE user_id=" + userId;
        Statement statement;

        try {
            getConnection();
            statement = conn.createStatement();
            statement.execute(query);

            statement.close();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
