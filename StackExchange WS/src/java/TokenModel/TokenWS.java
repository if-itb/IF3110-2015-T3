package TokenModel;

import java.sql.*;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author M. Fauzan Naufan
 */
@WebService(serviceName = "TokenWS")
public class TokenWS {

    /**
     * Web service operation
     * @param access_token
     * @return 
     */
    @WebMethod(operationName = "getUserIDByToken")
    public int getUserIDByToken(@WebParam(name = "access_token") String access_token) {
        Connection conn = new DB.Database().connect();
        Statement stmt;
        ResultSet rs;
        int userID = 0;
        try {
            stmt = conn.createStatement();
            String sql = "SELECT UserID FROM token WHERE AccessToken = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, access_token);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                userID = rs.getInt("UserID");
            }
        } catch (SQLException ex) {
            
        }
        return userID;
    }

}
