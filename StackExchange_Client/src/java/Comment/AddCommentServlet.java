package Comment;

import connection.DB;
import ConnectionIS.ConnectionIS;
import java.sql.Connection;
import org.json.simple.*;
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="AddCommentServlet", urlPatterns = "/addComment")
public class AddCommentServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException
    {
      response.setContentType("application/json");
      Connection conn = DB.connect();
      JSONObject jo = new JSONObject();
      int qid = Integer.parseInt(request.getParameter("id"));
      String content = request.getParameter("content");
      String token = request.getParameter("token");
      JSONObject auth = ConnectionIS.requestAuth(token);
      int uid = (int)(long)auth.get("id");
      int status = (int)(long)auth.get("status");
      
      try(PrintWriter out = response.getWriter()){
        if (status==1) {
          String sql = "INSERT INTO comment (question_id, content, user_id) VALUES (?,?,?)";
          try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, qid);
            stmt.setString(2,content);
            stmt.setInt(3,uid);
            int result= stmt.executeUpdate();
            if (result>0) {
              String sql2 = "SELECT name, create_time FROM comment NATURAL JOIN user WHERE question_id=? AND content=? AND user_id=?";
              try(PreparedStatement stmt2 = conn.prepareStatement(sql2)) {
                stmt2.setInt(1,qid);
                stmt2.setString(2,content);
                stmt2.setInt(3,uid);
                ResultSet resultSet = stmt2.executeQuery();
                if (resultSet.next()) {
                  jo.put("content",content);
                  jo.put("name",resultSet.getString("name"));
                  jo.put("create_time",resultSet.getString("create_time"));
                }
              }
            }
          }
        } else {
            if (status==0) {
                jo.put("ERROR","Token expired"); 
            } else if (status==-1) {
                jo.put("ERROR","Different IP Address detected. Please login again.");
            } else if (status==-2) {
                jo.put("ERROR","Different browser detected. Please login again.");
            }
        }
        out.println(jo.toString());
      }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CommentListServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CommentListServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}