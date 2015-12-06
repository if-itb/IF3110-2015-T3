package Comment;

import DB.Database;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author M. Fauzan Naufan
 */
public class AddComment extends HttpServlet {

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
        String access_token = "z21F#Mozilla/5.0 (Windows NT 10.0  WOW64  rv:42.0) Gecko/20100101 Firefox/42.0#127.0.0.1";
        String content = "comment1";
        int QID = 1;
        int AID = 1;
        
        Connection conn = new Database().connect();
        Statement stmt;
        String validation = "Oke!";//new IS.CheckToken().checkToken(access_token);
        PrintWriter out = response.getWriter();
        response.setContentType("application/xml;charset=UTF-8");
        switch (validation) {
            case "accesstokenusedinotherbrowser":
                out.println("Used in other browser");
                break;
            case "accesstokenusedwithotherconnection":
                out.println("Used in other connection");
                break;
            case "accesstokennotvalid":
                out.println("Invalid");
                break;
            case "accesstokenexpired":
                out.println("Expired");
                break;
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
                    out.println("Respons oke!");
                } catch (SQLException se) {
                    out.println("Gagal!");
                }
                break;
        }
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
