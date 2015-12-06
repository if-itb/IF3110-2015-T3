/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.stackexchange.webservice.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Comment;
import model.Question;
import org.stackexchange.webservice.core.MySQLDao;

/**
 *
 * @author Alex
 */
public class CommentDao extends MySQLDao {

    public Comment insert(long userId, long questionId, String content) {
        String query = "INSERT INTO `comment` (`user_id`, `question_id`, `content`) VALUES (" + userId + ", '" + questionId + "', '" + content + "')";
        System.out.println(query);
        Statement statement;

        try {
            getConnection();
            statement = conn.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = statement.getGeneratedKeys();
            int insertedId = 0;
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }

            Comment insertedComment = new Comment(insertedId, content, userId, questionId, new Date().toString());

            rs.close();
            statement.close();
            closeConnection();

            return insertedComment;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Comment> getByQuestionId(long questionId) {
        String query = "SELECT * FROM `comment` WHERE question_id=" + questionId;
        Statement statement;
        List<Comment> commentList = new ArrayList<>();

        try {
            getConnection();
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                commentList.add(new Comment(rs.getInt("id"), rs.getString("content"), rs.getInt("user_id"), rs.getInt("question_id"), rs.getString("created_at")));
            }
            rs.close();
            statement.close();

            return commentList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
