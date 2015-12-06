package UserModel;

import DB.Database;
import java.sql.*;
import java.util.ArrayList;
import javax.jws.*;

/**
 *
 * @author M. Fauzan Naufan
 */
@WebService(serviceName = "UserWS")
public class UserWS {

    /**
     * Web service operation
     *
     * @param nama
     * @param email
     * @param password
     * @return
     */
    @WebMethod(operationName = "register")
    public String register(@WebParam(name = "nama") String nama, @WebParam(name = "email") String email, @WebParam(name = "password") String password) {
        Connection conn = new Database().connect();
        Statement stmt;
        ResultSet rs;
        int i;

        try {
            i = 0;
            stmt = conn.createStatement();
            String sqlCheck = "SELECT * FROM user WHERE email = ?";
            PreparedStatement pstmtCheck = conn.prepareStatement(sqlCheck);
            pstmtCheck.setString(1, email);
            rs = pstmtCheck.executeQuery();
            while (rs.next()) {
                i++;
            }
            if (i > 0) {
                return "Error! Karena email tersebut sudah pernah didaftarkan";
            } else {
                String sqlMain = "INSERT INTO user VALUES (0,?,?,?)";
                PreparedStatement pstmtMain = conn.prepareStatement(sqlMain);
                pstmtMain.setString(1, nama);
                pstmtMain.setString(2, email);
                pstmtMain.setString(3, password);
                int a = pstmtMain.executeUpdate();
                stmt.close();
                return "Sukses!";
            }
        } catch (SQLException ex) {
            return "Error!";
        }
    }

    /**
     * Web service operation
     * @param UserID
     * @return 
     */
    @WebMethod(operationName = "getUser")
    public ArrayList<User> getUser(@WebParam(name = "UserID") int UserID) {
        Connection conn = new Database().connect();
        Statement stmt;
        ResultSet rs;
        ArrayList<User> users = new ArrayList();
        try {
            stmt = conn.createStatement();
            String sql = "SELECT * FROM user WHERE UserID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, UserID);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                users.add(new User(rs.getInt("UserID"),
                        rs.getString("Nama"),
                        rs.getString("Email"),
                        rs.getString("Password")
                ));
            }
        } catch (SQLException ex) {
            
        }
        return users;
    }
}
