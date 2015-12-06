package Vote;

import DB.Database;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author M. Fauzan Naufan
 */
public class VoteAnswer extends HttpServlet {

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
        String access_token = "g67M#Mozilla/5.0 (Windows NT 10.0  WOW64  rv:42.0) Gecko/20100101 Firefox/42.0#127.0.0.1";
        int AID = 0;
        int QID = 1;
        boolean voteUp = true;

        Connection conn = new Database().connect();
        Statement stmt;
        ResultSet rs;
        String validation = new IS.CheckToken().checkToken(access_token);

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
                    String sqlCheck = "SELECT * FROM voterelation WHERE UserID = ? AND AID = ?";
                    PreparedStatement pstmtCheck = conn.prepareStatement(sqlCheck);
                    pstmtCheck.setInt(1, userID);
                    pstmtCheck.setInt(2, AID);
                    rs = pstmtCheck.executeQuery();
                    int i = 0;
                    while (rs.next()) {
                        i++;
                    }
                    if (i > 0) {
                        out.println("Gagal!");
                    } else {
                        String sql, sql2;
                        if (voteUp) {
                            sql = "UPDATE answer SET Votes=Votes+1 WHERE aid = ?";
                        } else {
                            sql = "UPDATE answer SET Votes=Votes-1 WHERE aid = ?";
                        }
                        sql2 = "INSERT INTO voterelation VALUES (?,?,?)";
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        PreparedStatement pstmt2 = conn.prepareStatement(sql2);
                        pstmt.setInt(1, AID);
                        pstmt2.setInt(1, userID);
                        pstmt2.setInt(2, QID);
                        pstmt2.setInt(3, AID);
                        int a = pstmt.executeUpdate();
                        int b = pstmt2.executeUpdate();
                        out.println("Respons oke!");
                    }
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
