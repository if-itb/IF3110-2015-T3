package AnswerModel;

import DB.Database;
import java.io.IOException;
import java.net.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.jws.*;

/**
 *
 * @author M. Fauzan Naufan
 */
@WebService(serviceName = "AnswerWS")
public class AnswerWS {

    /**
     * Web service operation
     *
     * @param qid
     * @return
     */
    @WebMethod(operationName = "getAnswerByQID")
    @WebResult(name = "Answer")
    public ArrayList<Answer> getAnswerByQID(@WebParam(name = "qid") int qid) {
        Connection conn = new Database().connect();
        ArrayList<Answer> answers = new ArrayList();
        Statement stmt;
        ResultSet rs;

        try {
            stmt = conn.createStatement();
            String sql = "SELECT * FROM answer WHERE qid = ? ORDER BY Votes DESC";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, qid);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                answers.add(new Answer(rs.getInt("AID"),
                        rs.getInt("UserID"),
                        rs.getString("content"),
                        rs.getInt("Votes"),
                        qid,
                        rs.getString("DateTime")
                ));
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {

        }
        return answers;
    }

    /**
     * Web service operation
     *
     * @param access_token
     * @param qid
     * @param content
     * @return
     * @throws java.net.MalformedURLException
     */
    @WebMethod(operationName = "createAnswer")
    public String createAnswer(@WebParam(name = "access_token") String access_token, @WebParam(name = "qid") int qid, @WebParam(name = "content") String content) throws MalformedURLException, IOException {
        Connection conn = new Database().connect();
        Statement stmt;
        String validation = new IS.CheckToken().checkToken(access_token);
        switch (validation) {
            case "access token error":
                return "Expired token";
            case "access token invalid":
                return "Error";
            default:
                int userID = Integer.valueOf(validation);
                try {
                    stmt = conn.createStatement();
                    String sqlA = "INSERT INTO answer VALUES (0,?,?,0,?,?)";
                    String sqlQ = "UPDATE question SET Answers=Answers+1 WHERE qid = ?";
                    PreparedStatement pstmtA = conn.prepareStatement(sqlA);
                    PreparedStatement pstmtQ = conn.prepareStatement(sqlQ);
                    pstmtA.setInt(1, userID);
                    pstmtA.setString(2, content);
                    pstmtA.setInt(3, qid);
                    SimpleDateFormat ft = new SimpleDateFormat("y-M-d H:m:s");
                    pstmtA.setString(4, ft.format(new java.util.Date()));
                    pstmtQ.setInt(1, qid);
                    int a = pstmtA.executeUpdate();
                    int b = pstmtQ.executeUpdate();
                    stmt.close();
                    return "Respons oke!";
                } catch (SQLException se) {
                    return "Gagal!";
                }
        }
    }

    /**
     * Web service operation
     *
     * @param access_token
     * @param QID
     * @param AID
     * @param voteUp
     * @return
     */
    @WebMethod(operationName = "voteAnswer")
    public String voteAnswer(@WebParam(name = "access_token") String access_token, @WebParam(name = "QID") int QID, @WebParam(name = "AID") int AID, @WebParam(name = "voteUp") boolean voteUp) {
        Connection conn = new Database().connect();
        Statement stmt;
        ResultSet rs;
        String validation = new IS.CheckToken().checkToken(access_token);
        switch (validation) {
            case "access token error":
                return "Expired token";
            case "access token invalid":
                return "Error";
            default:
                int userID = Integer.valueOf(validation);
                try {
                    stmt = conn.createStatement();
                    String sqlCheck = "SELECT * FROM voterelation WHERE UserID = ? AND AID = ?";
                    PreparedStatement pstmtCheck = conn.prepareStatement(sqlCheck);
                    pstmtCheck.setInt(1, userID);
                    pstmtCheck.setInt(2, AID);
                    rs = pstmtCheck.executeQuery();
                    int i = 0;
                    while (rs.next()) {
                        i++;
                    }
                    if (i > 0) {
                        return "Gagal!";
                    } else {
                        String sql, sql2;
                        if (voteUp) {
                            sql = "UPDATE answer SET Votes=Votes+1 WHERE aid = ?";
                        } else {
                            sql = "UPDATE answer SET Votes=Votes-1 WHERE aid = ?";
                        }
                        sql2 = "INSERT INTO voterelation VALUES (?,?,?)";
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        PreparedStatement pstmt2 = conn.prepareStatement(sql2);
                        pstmt.setInt(1, AID);
                        pstmt2.setInt(1, userID);
                        pstmt2.setInt(2, QID);
                        pstmt2.setInt(3, AID);
                        int a = pstmt.executeUpdate();
                        int b = pstmt2.executeUpdate();
                        return "Respons oke!";
                    }
                } catch (SQLException se) {
                    return "Gagal!";
                }
        }
    }
}
