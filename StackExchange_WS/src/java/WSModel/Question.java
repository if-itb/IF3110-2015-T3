/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WSModel;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import static java.lang.System.out;
import java.sql.Timestamp;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import WSModule.QuestionClass;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import com.google.gson.Gson;


/**
 *
 * @author Jessica
 */

public class Question {
    final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    final static String localhost = "jdbc:mysql://localhost:3306/wbd2";
    final static String USER = "root";
    final static String PASS = "";
    
    public static String addQuestion(String access_token, String questionTitle, String questionContent) {
        String message = null;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Gson gson = new Gson();
        
        try {
            Token token = new Token();
            token.setAccessToken(access_token);
            StringBuilder sb = new StringBuilder();
            sb.append(gson.toJson(token));
            
            URL url = new URL("http://localhost:8082/IdentityService/auth");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();                
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Content-Length", "" + sb.length());

            OutputStreamWriter outputWriter = new OutputStreamWriter(connection.getOutputStream());
            outputWriter.write(sb.toString());
            outputWriter.flush();
            outputWriter.close();
            
            int responseCode = connection.getResponseCode();
            
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder stringStatus = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
		stringStatus.append(inputLine);
            }
            in.close();
            
            Status status = (Status) gson.fromJson(stringStatus.toString(), Status.class);
            int userID = status.getUserID();
            
            if (status.getSuccess()) {
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(localhost, USER, PASS);
                //statement = conn.createStatement();
                //String query = "INSERT INTO questions(`title`, `content`, `userID`, `date`) VALUES ('" + questionTitle + "','" + questionContent + "'," + userID + ",now())";
                String query = "INSERT INTO questions(title, content, userID, date) VALUES (?, ?, ?, NOW())";
                preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, questionTitle);
                preparedStatement.setString(2, questionContent);
                preparedStatement.setInt(3, userID);
                preparedStatement.executeUpdate();
                System.out.println(query);
            }
            message = status.getDescription();
        } catch (Exception e) {
            e.printStackTrace();
        }               
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();    
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return message;
    }
     
    public static QuestionClass getQuestionByID(int questionId) {
        Connection conn = null;
        Statement statement = null;
        ArrayList<QuestionClass> questionList = new ArrayList<QuestionClass>();
        QuestionClass[] questionArray = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(localhost, USER, PASS);
            String query = "SELECT * FROM questions WHERE questionId=" + questionId;
            statement = conn.prepareStatement(query, statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                //Retrieve by column name
                int question_Id  = rs.getInt("questionId");
                String title = rs.getString("title");
                String content = rs.getString("content");
                int vote  = rs.getInt("vote");
                String date = rs.getDate("date").toString();
                int userID  = rs.getInt("userID");
                
                QuestionClass questionRecord = new QuestionClass(question_Id,title,content,vote,date,userID);
                questionList.add(questionRecord);
            }
            rs.close();
            statement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }               
        if (statement != null) {
            try {
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();    
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return questionList.get(0);
    }
            
    public static ArrayList<QuestionClass> getAllQuestion(){
        Connection conn = null;
        PreparedStatement statement = null;
        ArrayList<QuestionClass> questionList = new ArrayList<QuestionClass>();
        QuestionClass[] questionArray = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(localhost, USER, PASS);
            String query = "SELECT questionId, title, content, vote, `date`, userID FROM questions";
            statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                int questionId  = rs.getInt("questionId");
                String title = rs.getString("title");
                String content = rs.getString("content");
                int vote  = rs.getInt("vote");
                String date = rs.getDate("date").toString();
                int userID  = rs.getInt("userID");

                QuestionClass questionRecord = new QuestionClass(questionId,title,content,vote,date,userID);
                questionList.add(questionRecord);
            }
            rs.close();
            statement.close();
            conn.close();
        } catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(statement != null)
                    conn.close();
            }catch(SQLException se){
                
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        return questionList;
    }
    
    public static String updateQuestion(String access_token, int questionId, String questionTitle, String questionContent) {
        String message = null;
        int dataUserID = 0;
        Connection conn = null;
        Statement statement = null;
        Gson gson = new Gson();
        
        try {
            Token token = new Token();
            token.setAccessToken(access_token);
            StringBuilder sb = new StringBuilder();
            sb.append(gson.toJson(token));

            URL url = new URL("http://localhost:8082/IdentityService/auth");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();                
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Content-Length", "" + sb.length());

            OutputStreamWriter outputWriter = new OutputStreamWriter(connection.getOutputStream());
            outputWriter.write(sb.toString());
            outputWriter.flush();
            outputWriter.close();

            int responseCode = connection.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder stringStatus = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                stringStatus.append(inputLine);
            }
            in.close();

            Status status = (Status) gson.fromJson(stringStatus.toString(), Status.class);
            int userID = status.getUserID();
            
            if (status.getSuccess()) {
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(localhost, USER, PASS);
                statement = conn.createStatement();
                String checkQuestion = "SELECT `userID` FROM questions WHERE questionId=" + questionId;
                ResultSet rsQuestion = statement.executeQuery(checkQuestion);
                if(rsQuestion.next()) {
                    dataUserID = rsQuestion.getInt(1);
                }
                if (dataUserID == userID) {
                    String query = "UPDATE questions SET `title`='" + questionTitle + "',`content` ='" + questionContent + "', `date` = now()  WHERE questionId =" + questionId;
                    statement.executeUpdate(query);
                    message = status.getDescription();
                } else {
                    message = "You are not the owner of this question.";
                }
            } else {
                message = status.getDescription();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }               
        if (statement != null) {
            try {
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();    
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return message;
    }
    
    public static String deleteQuestion(String access_token, int questionId) {
        String message = null;
        int dataUserID = 0;
        Connection conn = null;
        Statement statement = null;
        Gson gson = new Gson();
        
        try {
            Token token = new Token();
            token.setAccessToken(access_token);
            StringBuilder sb = new StringBuilder();
            sb.append(gson.toJson(token));

            URL url = new URL("http://localhost:8082/IdentityService/auth");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();                
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Content-Length", "" + sb.length());

            OutputStreamWriter outputWriter = new OutputStreamWriter(connection.getOutputStream());
            outputWriter.write(sb.toString());
            outputWriter.flush();
            outputWriter.close();

            int responseCode = connection.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder stringStatus = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                stringStatus.append(inputLine);
            }
            in.close();

            Status status = (Status) gson.fromJson(stringStatus.toString(), Status.class);
            int userID = status.getUserID();
            
            if (status.getSuccess()) {
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(localhost, USER, PASS);
                statement = conn.createStatement();
                String checkQuestion = "SELECT `userID` FROM questions WHERE questionId=" + questionId;
                ResultSet rsQuestion = statement.executeQuery(checkQuestion);
                if(rsQuestion.next()) {
                    dataUserID = rsQuestion.getInt(1);
                }
                if (dataUserID == userID) {
                    String query = "DELETE FROM questions where questionId=" + questionId;
                    statement.executeUpdate(query);
                    message = status.getDescription();
                } else {
                    message = "You are not the owner of this question.";
                }
            } else {
                message = status.getDescription();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }               
        if (statement != null) {
            try {
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();    
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return message;
    }
    
    public static String voteUpQuestion(String access_token, int questionId) {
        Connection conn = null;
        Statement statement = null;
        int userID_Exist = 0, questionID_Exist = 0;
        int countVote = 0, value = 0;
        String message = null;
        int dataUserID = 0;
        Gson gson = new Gson();
        
        try {
            Token token = new Token();
            token.setAccessToken(access_token);
            StringBuilder sb = new StringBuilder();
            sb.append(gson.toJson(token));

            URL url = new URL("http://localhost:8082/IdentityService/auth");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();                
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Content-Length", "" + sb.length());

            OutputStreamWriter outputWriter = new OutputStreamWriter(connection.getOutputStream());
            outputWriter.write(sb.toString());
            outputWriter.flush();
            outputWriter.close();

            int responseCode = connection.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder stringStatus = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                stringStatus.append(inputLine);
            }
            in.close();

            Status status = (Status) gson.fromJson(stringStatus.toString(), Status.class);
            int userID = status.getUserID();
            
            if (status.getSuccess()) {
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(localhost, USER, PASS);
                statement = conn.createStatement();
                String checkQuestion = "SELECT `userID` FROM questions WHERE questionId=" + questionId;
                ResultSet rsQuestion = statement.executeQuery(checkQuestion);
                if(rsQuestion.next()) {
                    dataUserID = rsQuestion.getInt(1);
                }
                if (dataUserID != userID) {
                    String voteTable = "SELECT count(*) FROM votequestion";
                    ResultSet rs = statement.executeQuery(voteTable);
                    if(rs.next()) {
                        countVote = rs.getInt(1);
                    }
                    int i = 0;
                    Boolean found = false;
                    while (i < countVote && !found) {
                        String checkEmail = "SELECT userID, questionID FROM votequestion WHERE userID=" + userID + " And questionID=" + questionId;
                        ResultSet rsVote = statement.executeQuery(checkEmail);
                        if (rsVote.next()) {
                            userID_Exist = rsVote.getInt(1);
                            questionID_Exist = rsVote.getInt(2);
                        }
                        if ((userID == userID_Exist) && (questionId == questionID_Exist)) {
                            found = true;
                        } else {
                            i++;
                        }
                    }
                    if (found == false) {
                        String insertVote = "INSERT INTO votequestion(`userID`, `questionID`) VALUES (" + userID + "," + questionId + ")";
                        statement.executeUpdate(insertVote);
                    }
                        String checkVote = "SELECT `value` FROM votequestion WHERE questionID=" + questionId + " AND userID=" + userID;
                        ResultSet rsVote = statement.executeQuery(checkVote);
                    if(rsVote.next()) {
                        value = rsVote.getInt("value");
                    }
                    if (value == 0) {
                        String query2 = "UPDATE votequestion SET `value` = 1 WHERE questionId =" + questionId + " And userID =" + userID;
                        statement.executeUpdate(query2);
                        String query = "UPDATE questions SET `vote` = `vote`+1 WHERE questionId =" + questionId;
                        statement.executeUpdate(query);
                    } else if (value == 1) {
                        String query2 = "UPDATE votequestion SET `value` = 0 WHERE questionId =" + questionId + " And userID =" + userID;
                        statement.executeUpdate(query2);
                        String query = "UPDATE questions SET `vote` = `vote`-1 WHERE questionId =" + questionId;
                        statement.executeUpdate(query);
                    } else if (value == -1) {
                        String query2 = "UPDATE votequestion SET `value` = 1 WHERE questionId =" + questionId + " And userID =" + userID;
                        statement.executeUpdate(query2);
                        String query = "UPDATE questions SET `vote` = `vote`+2 WHERE questionId =" + questionId;
                        statement.executeUpdate(query);
                    }
                    message = status.getDescription();
                } else {
                    message = "You are the owner of this question.";
                }
            } else {
                message = status.getDescription();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }               
        if (statement != null) {
            try {
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();    
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return message;
    }
    
    public static String voteDownQuestion(String access_token, int questionId) {
        Connection conn = null;
        Statement statement = null;
        int userID_Exist = 0, questionID_Exist = 0;
        int countVote = 0, value = 0;
        String message = null;
        int dataUserID = 0;
        Gson gson = new Gson();
        
        try {
            Token token = new Token();
            token.setAccessToken(access_token);
            StringBuilder sb = new StringBuilder();
            sb.append(gson.toJson(token));

            URL url = new URL("http://localhost:8082/IdentityService/auth");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();                
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Content-Length", "" + sb.length());

            OutputStreamWriter outputWriter = new OutputStreamWriter(connection.getOutputStream());
            outputWriter.write(sb.toString());
            outputWriter.flush();
            outputWriter.close();

            int responseCode = connection.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder stringStatus = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                stringStatus.append(inputLine);
            }
            in.close();

            Status status = (Status) gson.fromJson(stringStatus.toString(), Status.class);
            int userID = status.getUserID();
            
            if (status.getSuccess()) {
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(localhost, USER, PASS);
                statement = conn.createStatement();
                String checkQuestion = "SELECT `userID` FROM questions WHERE questionId=" + questionId;
                ResultSet rsQuestion = statement.executeQuery(checkQuestion);
                if(rsQuestion.next()) {
                    dataUserID = rsQuestion.getInt(1);
                }
                if (dataUserID != userID) {
                    String voteTable = "SELECT count(*) FROM votequestion";
                    ResultSet rs = statement.executeQuery(voteTable);
                    if(rs.next()) {
                        countVote = rs.getInt(1);
                    }
                    int i = 0;
                    Boolean found = false;
                    while (i < countVote && !found) {
                        String checkEmail = "SELECT userID, questionID FROM votequestion WHERE userID=" + userID + " And questionID=" + questionId;
                        ResultSet rsVote = statement.executeQuery(checkEmail);
                        if (rsVote.next()) {
                            userID_Exist = rsVote.getInt(1);
                            questionID_Exist = rsVote.getInt(2);
                        }
                        if ((userID == userID_Exist) && (questionId == questionID_Exist)) {
                            found = true;
                        } else {
                            i++;
                        }
                    }
                    if (found == false) {
                        String insertVote = "INSERT INTO votequestion(`userID`, `questionID`) VALUES (" + userID + "," + questionId + ")";
                        statement.executeUpdate(insertVote);
                    }
                    String checkVote = "SELECT `value` FROM votequestion WHERE questionID=" + questionId + " AND userID=" + userID;
                    ResultSet rsVote = statement.executeQuery(checkVote);
                    if(rsVote.next()) {
                        value = rsVote.getInt("value");
                    }
                    if (value == 0) {
                        String query2 = "UPDATE votequestion SET `value` = -1 WHERE questionID =" + questionId + " And userID =" + userID;
                        statement.executeUpdate(query2);
                        String query = "UPDATE questions SET `vote` = `vote`-1 WHERE questionID =" + questionId;
                        statement.executeUpdate(query);
                    } else if (value == 1) {
                        String query2 = "UPDATE votequestion SET `value` = -1 WHERE questionID =" + questionId + " And userID =" + userID;
                        statement.executeUpdate(query2);
                        String query = "UPDATE questions SET `vote` = `vote`-2 WHERE questionID =" + questionId;
                        statement.executeUpdate(query);
                    } else if (value == -1) {
                        String query2 = "UPDATE votequestion SET `value` = 0 WHERE questionID =" + questionId + " And userID =" + userID;
                        statement.executeUpdate(query2);
                        String query = "UPDATE questions SET `vote` = `vote`+1 WHERE questionID =" + questionId;
                        statement.executeUpdate(query);
                    }
                    message = status.getDescription();
                } else {
                    message = "You are the owner of this question.";
                }
            } else {
                message = status.getDescription();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }               
        if (statement != null) {
            try {
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();    
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return message;
    }
    
    public static int getSumVotes(int questionId) {
        Connection conn = null;
        Statement statement = null;
        int voteQuestion = 0;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(localhost, USER, PASS);
            statement = conn.createStatement();
            String query = "SELECT `vote` FROM questions WHERE questionId=" + questionId;
            ResultSet rs = statement.executeQuery(query);
            if(rs.next()) {
                voteQuestion = rs.getInt("vote");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }               
        if (statement != null) {
            try {
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();    
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return voteQuestion;
    }
}