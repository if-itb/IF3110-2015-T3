/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comment;

import Database.DatabaseConnect;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.jws.WebParam;

/**
 *
 * @author Gerry
 */
public class GetNameService {
    private Connection conn;
    
    public GetNameService() {
        DatabaseConnect dbc = new DatabaseConnect();
        conn = dbc.getConn();
    }
    
    public String getNameById(int userId) {
        try {
            String sql = "SELECT name FROM user WHERE id=?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, userId);
            ResultSet rs = dbStatement.executeQuery();
            String name = "Name Unknown";
            while(rs.next())
                name = rs.getString("name");
            return name;
        }
        catch (SQLException ex) {
            return "Name Unknown";
        }
    }
}
