package Comment;

import DB.Database;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author M. Fauzan Naufan
 */
public class GetComment extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int AID = 0;
        int QID = 0;
        
        Connection conn = new Database().connect();
        ArrayList<Comment> comments = new ArrayList();
        Statement stmt;
        ResultSet rs;
        String sql;
        PreparedStatement pstmt;
        PrintWriter out = response.getWriter();
        response.setContentType("application/xml;charset=UTF-8");
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
        out.println(comments);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
