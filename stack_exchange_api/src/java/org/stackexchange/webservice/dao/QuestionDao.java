package org.stackexchange.webservice.dao;

import model.Question;
import org.stackexchange.webservice.core.MySQLDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuestionDao extends MySQLDao {

    public Question insert(long userId, String topic, String content) {
        String query = "INSERT INTO `question` (`user_id`, `topic`, `content`) VALUES (" + userId + ", '" + topic + "', '" + content + "')";
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

            Question insertedQuestion = new Question(insertedId, userId, topic, content, new Date().toString(), 0);

            rs.close();
            statement.close();
            closeConnection();

            return insertedQuestion;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Question getById(long id) {
        String query = "SELECT * FROM `question` WHERE id=" + id;
        Statement statement;

        try {
            getConnection();
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            Question question = null;
            if (rs.next()) {
                question = new Question(rs.getInt("id"), rs.getInt("user_id"), rs.getString("topic"), rs.getString("content"), rs.getString("created_at"), rs.getInt("vote"));
            }
            rs.close();
            statement.close();

            return question;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Question> getAll() {
        String query = "SELECT * FROM `question`";
        Statement statement;
        List<Question> questionList = new ArrayList<>();

        try {
            getConnection();
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                questionList.add(new Question(rs.getInt("id"), rs.getInt("user_id"), rs.getString("topic"), rs.getString("content"), rs.getString("created_at"), rs.getInt("vote")));
            }
            rs.close();
            statement.close();

            return questionList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean update(long id, String topic, String content) {
        String query = "UPDATE `question` SET topic='" + topic + "', content='" + content + "' WHERE id=" + id;
        Statement statement;

        try {
            getConnection();
            statement = conn.createStatement();
            statement.executeUpdate(query);

            statement.close();
            closeConnection();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        String query = "DELETE FROM `question` WHERE id=" + id;
        Statement statement;

        try {
            getConnection();
            statement = conn.createStatement();
            statement.executeUpdate(query);

            statement.close();
            closeConnection();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean upvote(long id) {
        Question question = getById(id);

        long newVote = question.getVote() + 1;
        String query = "UPDATE `question` SET vote='" + newVote + "' WHERE id=" + id;
        System.out.println(query);
        Statement statement;

        try {
            getConnection();
            statement = conn.createStatement();
            statement.executeUpdate(query);

            statement.close();
            closeConnection();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean downvote(long id) {
        Question question = getById(id);

        long newVote = question.getVote() - 1;
        String query = "UPDATE `question` SET vote='" + newVote + "' WHERE id=" + id;
        Statement statement;

        try {
            getConnection();
            statement = conn.createStatement();
            statement.executeUpdate(query);

            statement.close();
            closeConnection();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
