package com.yangnormal.cvs;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@WebServlet(name = "CommentVoteServiceAPI", urlPatterns = {"/v1/*"} )
public class CommentVoteServiceAPI extends HttpServlet {

    public final String serverURL = "http://localhost:8084";

    final String DB_URL="jdbc:mysql://localhost/mystackexchange";
    final String USER="root";
    final String PASS="";
    Connection conn = null;

    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    protected void doOptions(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setHeader("Access-Control-Allow-Headers", "Content-Type");
        res.setHeader("Access-Control-Allow-Methods", "GET, POST");
        res.setHeader("Access-Control-Allow-Origin", "*");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setHeader("Access-Control-Allow-Origin", "*");
        String path = req.getPathInfo();

        if (path.equals("/")) {
            index(req, res);
        } else if (path.matches("/comment/(\\d+)")) {

            String pComment = "/comment/(\\d+)";
            Pattern rComment = Pattern.compile(pComment);
            Matcher mComment = rComment.matcher(path);

            if (mComment.find()) {
                String qid = mComment.group(1);
                postComment(req, res, qid);
            } else {
                show404(req,res);
            }

        } else if (path.matches("/vote/([.*])/(\\d+)")) {

            String pVote = "/vote/([.*])/(\\d+)";
            Pattern rVote = Pattern.compile(pVote);
            Matcher mVote = rVote.matcher(path);

            if (mVote.find()) {
                String type = mVote.group(1);
                String id = mVote.group(2);

                doVote(req, res);
            } else {
                show404(req,res);
            }

        } else {
            show404(req,res);
        }

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setHeader("Access-Control-Allow-Origin", "*");

        String path = req.getPathInfo();

        if (path.equals("/")) {
            index(req, res);
        } else if (path.matches("/comment/(\\d+)")) {

            String pComment = "/comment/(\\d+)";
            Pattern rComment = Pattern.compile(pComment);
            Matcher mComment = rComment.matcher(path);

            if (mComment.find()) {
                String qid = mComment.group(1);
                getComment(req,res,qid);
            } else {
                show404(req,res);
            }

        } else if (path.matches("/vote/([.*])/(\\d+)")) {

            String pVote = "/vote/([.*])/(\\d+)";
            Pattern rVote = Pattern.compile(pVote);
            Matcher mVote = rVote.matcher(path);

            if (mVote.find()) {
                String type = mVote.group(1);
                String id = mVote.group(2);

                getVote(req,res,type,id);
            } else {
                show404(req,res);
            }

        } else {
            show404(req,res);
        }
    }

    protected void getComment (HttpServletRequest req, HttpServletResponse res, String qid) throws ServletException, IOException{
        serve((request, out) -> {
            try {
                PreparedStatement stmt=conn.prepareStatement("SELECT comments.content, fullname, time_created FROM comments JOIN question ON comments.qid = question.id JOIN user ON comments.uid = user.id WHERE comments.qid = ? ");
                stmt.setString(1, qid);
                ResultSet result = stmt.executeQuery();
                out.print("{");
                out.print("\"comment_list\": [");



                int rowcount = 0;
                if (result.last()) {
                    rowcount = result.getRow();
                    result.beforeFirst();
                }

                for (int i=0;i<rowcount-1;i++){
                    result.next();
                    String content= result.getString("content");
                    String name= result.getString("fullname");
                    String time= result.getString("time_created");
                    out.print("{\"content\": \"" + content + "\", " + "\"name\":\"" + name + "\", " + "\"time_created\":\"" + time + "\"}, ");
                }

                result.next();
                String content= result.getString("content");
                String name= result.getString("fullname");
                String time= result.getString("time_created");
                out.print("{\"content\": \"" + content + "\", " + "\"name\":\"" + name + "\", " + "\"time_created\":\"" + time + "\"}");
                out.print("]}");
                out.flush();

            } catch (Exception e) {
                res.setStatus(500);
                out.print("{\"error\":\""+e.getMessage()+"\"}");
                e.printStackTrace();
                out.flush();
            }

        }, req, res);
    }

    protected void getVote (HttpServletRequest req, HttpServletResponse res, String type, String id) throws ServletException, IOException{
        serve((request, out) -> {
            try {
                PreparedStatement stmt = conn.prepareStatement("");
                if (type.equals("question")){
                    stmt=conn.prepareStatement("SELECT vote FROM question WHERE id = ?");
                }
                else if (type.equals("answer")){
                    stmt=conn.prepareStatement("SELECT vote FROM answer WHERE id = ?");
                }
                stmt.setString(1, String.valueOf(id));
                ResultSet result = stmt.executeQuery();
                while (result.next()){
                    int vote = result.getInt("vote");
                    out.print("{\"vote\":\""+ vote + "\"}");
                }
                out.flush();

            } catch (Exception e) {
                res.setStatus(500);
                out.print("{\"error\":\""+e.getMessage()+"\"}");
                e.printStackTrace();
                out.flush();
            }

        }, req, res);
    }

    protected void postComment (HttpServletRequest req, HttpServletResponse res, String qid) throws ServletException, IOException{
        serve((request, out) -> {
            String content = req.getParameter("content");
            String token = req.getParameter("token");

            String check = getRequest("http://localhost:8083/v1/check?token="+ token);

            // Parse JSON
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = new HashMap<String, Object>();
            try {
                map = mapper.readValue(check, new TypeReference<Map<String, String>>(){});
            } catch (IOException e) {
                e.printStackTrace();
            }

            int uid = Integer.parseInt((String)map.get("uid"));

            // cek apakah memegang token
            if (uid > 0){
                try {
                PreparedStatement stmt = conn.prepareStatement("");

                stmt=conn.prepareStatement("INSERT INTO comments ('qid','content','uid','time_created') VALUES (" + qid + ", '" + "?" + "', " + uid + ", CURRENT_TIMESTAMP)");

                stmt.setString(1, String.valueOf(content));
                stmt.executeUpdate();

                } catch (Exception e) {
                    res.setStatus(500);
                    out.print("{\"error\":\""+e.getMessage()+"\"}");
                    e.printStackTrace();
                    out.flush();
                }
            }
//
//
        }, req, res);
    }

    protected void doVote (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        serve((request, out) -> {

            String qid = req.getParameter("id");
            String type = req.getParameter("type");
            int direction = Integer.parseInt(req.getParameter("direction"));
            String token = req.getParameter("token");

            String check = getRequest("http://localhost:8083/v1/check?token="+ token);

            // Parse JSON
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = new HashMap<String, Object>();
            try {
                map = mapper.readValue(check, new TypeReference<Map<String, String>>(){});
            } catch (IOException e) {
                e.printStackTrace();
            }

            int uid = Integer.parseInt((String)map.get("uid"));

            // cek apakah memegang token
            if (uid > 0){
                try {
                    Statement stmt;
                    Statement stmt2;
                    ResultSet rs;

                    if (type == "question"){
                        // Cek apakah sudah pernah melakukan vote
                        String querycount = "SELECT COUNT(id) AS count FROM vote_question WHERE qid=" + qid + " AND uid=" + uid;
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(querycount);
                        rs.next();
                        int stat = rs.getInt("count");

                        if (stat == 0) { // Belum pernah melakukan vote
                            if (direction == 1) {
                                String query = "UPDATE question SET vote = vote + 1 WHERE id=" + qid;
                                stmt.executeUpdate(query);
                            } else if (direction == -1) {
                                String query = "UPDATE question SET vote = vote - 1 WHERE id=" + qid;
                                stmt.executeUpdate(query);
                            }
                        }
                        String query2 = "INSERT INTO vote_question (qid, uid, vote) VALUES ("+qid+","+uid+","+direction+")";
                        stmt2 = conn.createStatement();
                        stmt2.executeUpdate(query2);
                    }
                    else if (type == "answer"){
                        // Cek apakah sudah pernah melakukan vote
                        String querycount = "SELECT COUNT(id) AS count FROM vote_answer WHERE aid=" + qid + " AND uid=" + uid;
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(querycount);
                        rs.next();
                        int stat = rs.getInt("count");

                        if (stat == 0) { // Belum pernah melakukan vote
                            if (direction == 1) {
                                String query = "UPDATE answer SET vote = vote + 1 WHERE id=" + qid;
                                stmt.executeUpdate(query);
                            } else if (direction == -1) {
                                String query = "UPDATE answer SET vote = vote - 1 WHERE id=" + qid;
                                stmt.executeUpdate(query);
                            }
                        }
                        String query2 = "INSERT INTO vote_answer (qid, uid, vote) VALUES ("+qid+","+uid+","+direction+")";
                        stmt2 = conn.createStatement();
                        stmt2.executeUpdate(query2);
                    }


                } catch (Exception e) {
                    res.setStatus(500);
                    out.print("{\"error\":\""+e.getMessage()+"\"}");
                    e.printStackTrace();
                    out.flush();
                }
            }

        }, req, res);
    }

    private String getRequest(String urlStr){
        StringBuilder response = new StringBuilder();
        URL url = null;
        try {
            url = new URL(urlStr);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection)url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            conn.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        try {
            conn.connect();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
            }
            rd.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.toString();
    }

    protected void show404 (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        out.print("{\"message\": \"404 Not Found\"}");
        out.flush();
    }

    private  void index (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        out.print("{\"login_url\":\""+serverURL+"/v1/login\",\"token_check_url\":\""+serverURL+"/v1/check\"}");
        out.flush();
    }


    private void checkDatabaseConnection() throws ClassNotFoundException, SQLException {
        if (conn == null) {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        }
    }

    private void serve(BiConsumer<HttpServletRequest, PrintWriter> func, HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();

        try {
            checkDatabaseConnection();
            func.accept(req, out);
            out.flush();
        } catch (Exception e) {
            res.setStatus(500);
            out.print("{\"error\":\""+e.getMessage()+"\"}");
            out.flush();
        }
    }



}
