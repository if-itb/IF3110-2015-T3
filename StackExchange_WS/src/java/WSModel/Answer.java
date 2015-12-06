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

import WSModule.AnswerClass;
import java.util.ArrayList;
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

public class Answer {
    final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    final static String localhost = "jdbc:mysql://localhost:3306/wbd2";
    final static String USER = "root";
    final static String PASS = "";
    
    public static String addAnswer(String access_token, int question_id, String answerContent) {
        String message = null;
        Connection conn = null;
        Statement statement = null;
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
            System.out.println(stringStatus.toString());
            int userID = status.getUserID();
            
            if (status.getSuccess()) {
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(localhost, USER, PASS);
                statement = conn.createStatement();
                String checkAnswer = "SELECT userID FROM questions WHERE questionId=" + question_id;
                ResultSet rsAnswer = statement.executeQuery(checkAnswer);
                
                if(rsAnswer.next()) {
                    dataUserID = rsAnswer.getInt(1);
                }
                if (dataUserID != userID) {
                    String query = "INSERT INTO answers(`question_id`, `content`, `userID`, `date`) VALUES (" + question_id + ",'" + answerContent + "'," + userID + " ,now())";
                    statement.executeUpdate(query);
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
        System.out.println(message);
        return message;
    }
     
    public static AnswerClass getAnswerByID(int answer_id) {
        Connection conn = null;
        Statement statement = null;
        ArrayList<AnswerClass> answerList = new ArrayList<AnswerClass>();
        AnswerClass[] answerArray = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(localhost, USER, PASS);
            String query = "SELECT * FROM answers WHERE answer_id=" + answer_id;
            statement = conn.prepareStatement(query, statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.executeQuery(query);
        
            while (rs.next()) {
                //Retrieve by column name
                int answerId  = rs.getInt("answer_id");
                int question_id  = rs.getInt("question_Id");
                String content = rs.getString("content");
                int vote  = rs.getInt("vote");
                String date = rs.getDate("date").toString();
                int userID  = rs.getInt("userID");

                AnswerClass answerRecord = new AnswerClass(answerId, question_id, content, vote, date, userID);
                answerList.add(answerRecord);
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
        return answerList.get(0);
    }
    
    public static ArrayList<AnswerClass> getAnswerByQID(int question_id) {
        Connection conn = null;
        Statement statement = null;
        ArrayList<AnswerClass> answerList = new ArrayList<AnswerClass>();
        AnswerClass[] answerArray = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(localhost, USER, PASS);
            String query = "SELECT * FROM answers WHERE question_id=" + question_id;
            statement = conn.prepareStatement(query, statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.executeQuery(query);
        
            while (rs.next()) {
                //Retrieve by column name
                int answer_id  = rs.getInt("answer_Id");
                int questionId  = rs.getInt("question_id");
                String content = rs.getString("content");
                int vote  = rs.getInt("vote");
                String date = rs.getDate("date").toString();
                int userID  = rs.getInt("userID");

                AnswerClass answerRecord = new AnswerClass(answer_id, questionId, content, vote, date, userID);
                answerList.add(answerRecord);
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
        return answerList;
    }
    
    public static String voteUpAnswer(String access_token, int answerId, int questionId) {
        Connection conn = null;
        Statement statement = null;
        int userID_Exist = 0, questionID_Exist = 0, answerID_Exist = 0;
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
                String checkAnswer = "SELECT `userID` FROM answers WHERE answer_id=" + answerId;
                ResultSet rsAnswer = statement.executeQuery(checkAnswer);
                if(rsAnswer.next()) {
                    dataUserID = rsAnswer.getInt(1);
                }
                if (dataUserID != userID) {
                    String voteTable = "SELECT count(*) FROM voteanswer";
                    ResultSet rs = statement.executeQuery(voteTable);
                    if(rs.next()) {
                        countVote = rs.getInt(1);
                    }
                    int i = 0;
                    Boolean found = false;
                    while (i < countVote && !found) {
                        String checkEmail = "SELECT userID, answerID, questionID FROM voteanswer WHERE userID=" + userID + " And questionID=" + questionId + " And answerID=" + answerId;
                        ResultSet rsVote = statement.executeQuery(checkEmail);
                        if (rsVote.next()) {
                            userID_Exist = rsVote.getInt(1);
                            answerID_Exist = rsVote.getInt(2);
                            questionID_Exist = rsVote.getInt(3);
                        }
                        if ((userID == userID_Exist) && (answerId == answerID_Exist) && (questionId == questionID_Exist)) {
                            found = true;
                        } else {
                            i++;
                        }
                    }
                    if (found == false) {
                        String insertVote = "INSERT INTO voteanswer(`userID`, `answerID`, `questionID`) VALUES (" + userID + "," + answerId + "," + questionId + ")";
                        statement.executeUpdate(insertVote);
                    }
                    String checkVote = "SELECT `value` FROM voteanswer WHERE questionID=" + questionId + " AND userID=" + userID + " AND answerID=" + answerId;
                    ResultSet rsVote = statement.executeQuery(checkVote);
                    if(rsVote.next()) {
                        value = rsVote.getInt("value");
                    }
                    if (value == 0) {
                        String query2 = "UPDATE voteanswer SET `value` = 1 WHERE questionId =" + questionId + " And userID =" + userID + " And answerID =" + answerId;
                        statement.executeUpdate(query2);
                        String query = "UPDATE answers SET `vote` = `vote`+1 WHERE question_id =" + questionId + " And answer_id=" + answerId;
                        statement.executeUpdate(query);
                    } else if (value == 1) {
                        String query2 = "UPDATE voteanswer SET `value` = 0 WHERE questionId =" + questionId + " And userID =" + userID + " And answerID =" + answerId;
                        statement.executeUpdate(query2);
                        String query = "UPDATE answers SET `vote` = `vote`-1 WHERE question_id =" + questionId + " And answer_id=" + answerId;
                        statement.executeUpdate(query);
                    } else if (value == -1) {
                        String query2 = "UPDATE voteanswer SET `value` = 1 WHERE questionId =" + questionId + " And userID =" + userID;
                        statement.executeUpdate(query2);
                        String query = "UPDATE answers SET `vote` = `vote`+2 WHERE question_id =" + questionId + " And answer_iD=" + answerId;
                        statement.executeUpdate(query);
                    }
                    message = status.getDescription();
                } else {
                    message = "You are the owner of this answer.";
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
    
    public static String voteDownAnswer(String access_token, int answerId, int questionId) {
        Connection conn = null;
        Statement statement = null;
        int userID_Exist = 0, questionID_Exist = 0, answerID_Exist = 0;
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
                String checkAnswer = "SELECT `userID` FROM answers WHERE answer_id=" + answerId;
                ResultSet rsAnswer = statement.executeQuery(checkAnswer);
                if(rsAnswer.next()) {
                    dataUserID = rsAnswer.getInt(1);
                }
                if (dataUserID != userID) {
                    String voteTable = "SELECT count(*) FROM voteanswer";
                    ResultSet rs = statement.executeQuery(voteTable);
                    if(rs.next()) {
                        countVote = rs.getInt(1);
                    }
                    int i = 0;
                    Boolean found = false;
                    while (i < countVote && !found) {
                        String checkEmail = "SELECT userID, answerID, questionID FROM voteanswer WHERE userID=" + userID + " And questionID=" + questionId + " And answerID=" + answerId;
                        ResultSet rsVote = statement.executeQuery(checkEmail);
                        if (rsVote.next()) {
                            userID_Exist = rsVote.getInt(1);
                            answerID_Exist = rsVote.getInt(2);
                            questionID_Exist = rsVote.getInt(3);
                        }
                        if ((userID == userID_Exist) && (answerId == answerID_Exist) && (questionId == questionID_Exist)) {
                            found = true;
                        } else {
                            i++;
                        }
                    }
                    if (found == false) {
                        String insertVote = "INSERT INTO voteanswer(`userID`, `answerID`, `questionID`) VALUES (" + userID + "," + answerId + "," + questionId + ")";
                        statement.executeUpdate(insertVote);
                    }
                    String checkVote = "SELECT `value` FROM voteanswer WHERE questionID=" + questionId + " AND userID=" + userID + " AND answerID=" + answerId;
                    ResultSet rsVote = statement.executeQuery(checkVote);
                    if(rsVote.next()) {
                        value = rsVote.getInt("value");
                    }
                    if (value == 0) {
                        String query2 = "UPDATE voteanswer SET `value` = -1 WHERE questionId =" + questionId + " And userID =" + userID + " And answerID =" + answerId;
                        statement.executeUpdate(query2);
                        String query = "UPDATE answers SET `vote` = `vote`-1 WHERE question_id =" + questionId + " And answer_id=" + answerId;
                        statement.executeUpdate(query);
                    } else if (value == 1) {
                        String query2 = "UPDATE voteanswer SET `value` = -1 WHERE questionId =" + questionId + " And userID =" + userID + " And answerID =" + answerId;
                        statement.executeUpdate(query2);
                        String query = "UPDATE answers SET `vote` = `vote`-2 WHERE question_id =" + questionId + " And answer_id=" + answerId;
                        statement.executeUpdate(query);
                    } else if (value == -1) {
                        String query2 = "UPDATE voteanswer SET `value` = 0 WHERE questionId =" + questionId + " And userID =" + userID;
                        statement.executeUpdate(query2);
                        String query = "UPDATE answers SET `vote` = `vote`+1 WHERE question_id =" + questionId + " And answer_iD=" + answerId;
                        statement.executeUpdate(query);
                    }
                    message = status.getDescription();
                } else {
                    message = "You are the owner of this answer.";
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
        
    public static int getSumAnswer(int questionId) {
        Connection conn = null;
        Statement statement = null;
        int countAnswer = 0;
        
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(localhost, USER, PASS);
            statement = conn.createStatement();
            int rowAnswer = statement.getMaxRows();
            String query = "SELECT count(*) FROM answers WHERE question_id=" + questionId;
            ResultSet rs = statement.executeQuery(query);
            if(rs.next()) {
                countAnswer = rs.getInt(1);
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
        return countAnswer;
    }

}   