/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connector.Database;
import connector.IdentityService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author visat
 */
public class VoteServlet extends HttpServlet {

    private final Connection connection = Database.connection();
    
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
        String sId = request.getParameter("id");
        String type = request.getParameter("type");

        if (sId == null || type == null)
            return;

        int id;
        try {
            id = Integer.parseInt(sId);
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
            return;
        }
        type = type.toLowerCase();

        JSONObject result = new JSONObject();
        switch (type) {
            case "question":
                result.put("votes", getQuestionVotes(id));
                break;
            case "answer":
                result.put("votes", getAnswerVotes(id));
                break;
            default:
                return;
        }

        try (PrintWriter out = response.getWriter()) {
            response.setContentType("application/json");
            out.println(result.toString());
        }

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
        String token = request.getParameter("auth");
        String sId = request.getParameter("id");
        String type = request.getParameter("type");
        String action = request.getParameter("action");

        if (token == null || sId == null || type == null || action == null)
            return;
        
        JSONObject result = new JSONObject();
        String userAgent = request.getHeader("User-Agent");
        String remoteAddr = request.getHeader("Remote-Address");
        if (userAgent == null) userAgent = "";
        if (remoteAddr == null) remoteAddr = "";
        JSONObject identity = IdentityService.requestAuth(token, userAgent, remoteAddr);
        if (identity != null && identity.containsKey("status")) {            
            Long status = (Long) identity.get("status");            
            // token has expired
            if (status == -1) {
                result.put("status", -1);
                result.put("detail", "Token has expired");
            }
            // token found
            else if (status == 1 && identity.containsKey("id")) {
                // workaround, json must be converted to long first                
                long idUserL = (long) identity.get("id");
                int idUser = (int) idUserL;
                int id;
                try {
                    id = Integer.parseInt(sId);
                } catch (NumberFormatException e) {
                    System.err.println(e.getMessage());
                    return;
                }
                type = type.toLowerCase();
                action = action.toLowerCase();

                boolean success = false;
                if (type.equals("question")) {
                    if (action.equals("up"))
                        success = voteQuestionUp(idUser, id);
                    else if (action.equals("down"))
                        success = voteQuestionDown(idUser, id);
                    if (success)
                        result.put("votes", getQuestionVotes(id));
                }
                else if (type.equals("answer")) {                    
                    if (action.equals("up"))
                        success = voteAnswerUp(idUser, id);
                    else if (action.equals("down"))
                        success = voteAnswerDown(idUser, id);
                    if (success)
                        result.put("votes", getAnswerVotes(id));
                }

                if (success)
                    result.put("status", 1);
                else {
                    result.put("status", 0);
                    result.put("detail", "Failed to vote");
                }
            }
            // invalid token
            else {
                result.put("status", -1);
                result.put("detail", "Invalid token");
            }

            try (PrintWriter out = response.getWriter()) {
                response.setContentType("application/json");
                out.println(result.toString());
            }
        }
    }

    private int getQuestionVoteState (int idUser, int idQuestion) {
        int state = 0;
        try {
            String query = "SELECT vote_up FROM vote_question WHERE id_user = ? AND id_question = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, idUser);
                statement.setInt(2, idQuestion);
                try (ResultSet result = statement.executeQuery()) {
                    if (result.next())
                        state = result.getBoolean(1) ? 1 : -1;
                }
            }
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return state;
    }

    private int getAnswerVoteState (int idUser, int idAnswer) {
        int state = 0;
        try {
            String query = "SELECT vote_up FROM vote_answer WHERE id_user = ? AND id_answer = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, idUser);
                statement.setInt(2, idAnswer);
                try (ResultSet result = statement.executeQuery()) {
                    if (result.next())
                        state = result.getBoolean(1) ? 1 : -1;
                }
            }
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return state;
    }

    private boolean voteAnswer(int idUser, int idAnswer, boolean voteUp) {
        boolean success = false;
        try {
            if (answerExists(idAnswer) && getAnswerVoteState(idUser, idAnswer) == 0) {
                String updateQuery = "UPDATE answer SET votes = votes " + (voteUp? "+" : "-")  + " 1 WHERE id = ?";
                String insertQuery = "INSERT INTO vote_answer (id_user, id_answer, vote_up) VALUES (?, ?, ?)";
                connection.setAutoCommit(false);
                try (
                    PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                    PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                    updateStatement.setInt(1, idAnswer);
                    insertStatement.setInt(1, idUser);
                    insertStatement.setInt(2, idAnswer);
                    insertStatement.setBoolean(3, voteUp);
                    success = updateStatement.executeUpdate() > 0 && insertStatement.executeUpdate() > 0;
                    connection.commit();
                }
                finally {
                    connection.setAutoCommit(true);
                }
            }
        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return success;
    }

    private boolean voteAnswerUp(int idUser, int idAnswer) {
        return voteAnswer(idUser, idAnswer, true);
    }

    private boolean voteAnswerDown(int idUser, int idAnswer) {
        return voteAnswer(idUser, idAnswer, false);
    }

    private boolean voteQuestion(int idUser, int idQuestion, boolean voteUp) {
        boolean success = false;
        try {
            if (questionExists(idQuestion) && getQuestionVoteState(idUser, idQuestion) == 0) {
                String updateQuery = "UPDATE question SET votes = votes " + (voteUp? "+" : "-")  + " 1 WHERE id = ?";
                String insertQuery = "INSERT INTO vote_question (id_user, id_question, vote_up) VALUES (?, ?, ?)";
                connection.setAutoCommit(false);
                try (
                    PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                    PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                    updateStatement.setInt(1, idQuestion);
                    insertStatement.setInt(1, idUser);
                    insertStatement.setInt(2, idQuestion);
                    insertStatement.setBoolean(3, voteUp);
                    success = updateStatement.executeUpdate() > 0 && insertStatement.executeUpdate() > 0;
                    connection.commit();
                }
                finally {
                    connection.setAutoCommit(true);
                }
            }
        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return success;
    }

    private boolean voteQuestionUp(int idUser, int idQuestion) {
        return voteQuestion(idUser, idQuestion, true);
    }

    private boolean voteQuestionDown(int idUser, int idQuestion) {
        return voteQuestion(idUser, idQuestion, false);
    }

    private boolean questionExists(int idQuestion) {
        boolean exists = false;
        String query = "SELECT COUNT(*) FROM question WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idQuestion);
            ResultSet result = statement.executeQuery();
            exists = result.next();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return exists;
    }

    private boolean answerExists(int idAnswer) {
        boolean exists = false;
        String query = "SELECT COUNT(*) FROM answer WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idAnswer);
            ResultSet result = statement.executeQuery();
            exists = result.next();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return exists;
    }

    private int getQuestionVotes(int idQuestion) {
        int votes = 0;
        String query = "SELECT votes FROM question WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idQuestion);
            ResultSet result = statement.executeQuery();
            if (result.next())
                votes = result.getInt(1);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return votes;
    }

    private int getAnswerVotes(int idAnswer) {
        int votes = 0;
        String query = "SELECT votes FROM answer WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idAnswer);
            ResultSet result = statement.executeQuery();
            if (result.next())
                votes = result.getInt(1);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return votes;
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

