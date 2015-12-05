package Config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
    private String hostParam;
    private String user;
    private String password;
    
    public DB() {
        this.hostParam = "jdbc:mysql://localhost:3306/stackexchange2?zeroDateTimeBehaviour=convertToNull";
        this.user = "root";
        this.password = "";
    }
    
    public Connection connect() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(hostParam, user, password);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return conn;
    }
}
