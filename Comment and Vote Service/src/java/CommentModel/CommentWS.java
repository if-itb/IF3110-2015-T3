package CommentModel;

import DB.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author M. Fauzan Naufan
 */
@WebService(serviceName = "CommentWS")
public class CommentWS {

    /**
     * Web service operation
     * @param access_token
     * @param content
     * @param QID
     * @param AID
     * @return 
     */
    @WebMethod(operationName = "createComment")
    public String createComment(@WebParam(name = "access_token") String access_token, @WebParam(name = "content") String content, @WebParam(name = "QID") int QID, @WebParam(name = "AID") int AID) {
        
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
                    String sql = "INSERT INTO comment VALUES (0,?,?,?,?,?)";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, content);
                    SimpleDateFormat ft = new SimpleDateFormat("y-M-d H:m:s");
                    pstmt.setString(2, ft.format(new java.util.Date()));
                    pstmt.setInt(3, QID);
                    pstmt.setInt(4, AID);
                    pstmt.setInt(5, userID);
                    int a = pstmt.executeUpdate();
                    stmt.close();
                    return "Respons oke!";
                } catch (SQLException se) {
                    return "Gagal!";
                }
        }
    }

    /**
     * Web service operation
     * @param QID
     * @param AID
     * @return 
     */
    @WebMethod(operationName = "getComment")
    public ArrayList<Comment> getComment(@WebParam(name = "QID") int QID, @WebParam(name = "AID") int AID) {
        Connection conn = new Database().connect();
        ArrayList<Comment> comments = new ArrayList();
        Statement stmt;
        ResultSet rs;
        String sql;
        PreparedStatement pstmt;
        
        try {
            stmt = conn.createStatement();
            if (AID == 0) {
                sql = "SELECT * FROM comment WHERE QID = ? ORDER BY DateTime ASC";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, QID);
            } else {
                sql = "SELECT * FROM comment WHERE QID = ? AND AID = ? ORDER BY DateTime ASC";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, QID);
                pstmt.setInt(2, AID);
            }

            rs = pstmt.executeQuery();

            while (rs.next()) {
                comments.add(new Comment(rs.getInt("CID"),
                        rs.getString("Content"),
                        rs.getString("DateTime"),
                        QID,
                        AID,
                        rs.getInt("UserID")
                ));
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {

        }
        return comments;
    }

}
