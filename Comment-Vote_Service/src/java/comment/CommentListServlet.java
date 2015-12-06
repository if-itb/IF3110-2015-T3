package comment;

import connection.DB;
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

@WebServlet(name="CommentListServlet", urlPatterns = "/comments")
public class CommentListServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException
    {
      Connection conn = DB.connect();
      response.setContentType("application/json");
      int qid = Integer.parseInt(request.getParameter("id"));
      JSONArray ja = new JSONArray();
      try(PrintWriter out = response.getWriter()){
        String sql = "SELECT * FROM comment WHERE question_id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
          stmt.setInt(1, qid);
          ResultSet result= stmt.executeQuery();
          while (result.next())
          {
            int uid = result.getInt("user_id");
            String content = result.getString("content");
            String create_time = result.getString("create_time");
            JSONObject jo = new JSONObject();
            jo.put("uid",uid);
            jo.put("content",content);
            jo.put("create_time",create_time);
            ja.add(jo);
          }
        }
        out.println(ja.toString());
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