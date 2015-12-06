package stackExchangeWS.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author davidkwan
 */
public class DbManager {
    private static Connection conn = null;
	public static void registerUser(User user) throws SQLException{
            conn = ConnectionManager.getInstance().getConnection();
            String sql = "INSERT INTO user (email, password) VALUES(?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getPassword());
            
            pstmt.executeUpdate();
            ConnectionManager.getInstance().close();
	}
        
        public static boolean isUserExist(User user) throws SQLException{
            conn = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT COUNT(*) FROM user WHERE email=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, user.getEmail());
            ResultSet rs = pstmt.executeQuery();
            
            int count = 0;
            while(rs.next())
                count = rs.getInt(1);
            
            ConnectionManager.getInstance().close();
            
            return count == 1;
        }
}
