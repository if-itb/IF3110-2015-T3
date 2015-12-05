package org.stackexchange.webservice.dao;

import org.stackexchange.Answer;
import org.stackexchange.Question;
import org.stackexchange.webservice.core.MySQLDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AnswerDao extends MySQLDao {

    public Answer insert(long userId, long questionId, String content) {
        String query = "INSERT INTO `answer` (`user_id`, `question_id`, `content`) VALUES (" + userId + ", " + questionId + ", '" + content + "')";
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

            Answer insertedAnswer = new Answer(insertedId, userId, questionId, content, 0, new Date().toString());

            rs.close();
            statement.close();
            closeConnection();

            return insertedAnswer;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Answer getById(long id) {
        String query = "SELECT * FROM `answer` WHERE id=" + id;
        Statement statement;

        try {
            getConnection();
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            Answer answer = null;
            if (rs.next()) {
                answer = new Answer(rs.getInt("id"), rs.getInt("user_id"), rs.getInt("question_id"), rs.getString("content"), rs.getInt("vote"), rs.getString("created_at"));
            }
            rs.close();
            statement.close();

            return answer;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Answer> getByQuestionId(long questionId) {
        String query = "SELECT * FROM `answer` WHERE question_id=" + questionId;
        Statement statement;
        List<Answer> answerList = new ArrayList<>();

        try {
            getConnection();
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                answerList.add(new Answer(rs.getInt("id"), rs.getInt("user_id"), rs.getInt("question_id"), rs.getString("content"), rs.getInt("vote"), rs.getString("created_at")));
            }
            rs.close();
            statement.close();

            return answerList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean upvote(long id) {
        Answer answer = getById(id);

        long newVote = answer.getVote() + 1;
        String query = "UPDATE `answer` SET vote='" + newVote + "' WHERE id=" + id;
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
        Answer answer = getById(id);

        long newVote = answer.getVote() - 1;
        String query = "UPDATE `answer` SET vote='" + newVote + "' WHERE id=" + id;
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