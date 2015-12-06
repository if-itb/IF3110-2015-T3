package com.yangnormal.is;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.nimbus.State;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;
import java.util.function.BiConsumer;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by ibrohim on 11/17/15.
 */
@WebServlet(name = "IdentityServiceAPI", urlPatterns = {"/v1/*"} )
public class IdentityServiceAPI extends HttpServlet {

    public final String serverURL = "http://localhost:8083";

    final String DB_URL="jdbc:mysql://localhost/mystackexchange";
    final String USER="root";
    final String PASS="";
    Connection conn = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        //config.getInitParameter("foo")
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        switch (req.getPathInfo()) {
            case "/login":
                IdentityServiceLogin(req, res);
                break;
            default:
                show404(req,res);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        switch (req.getPathInfo()) {
            case "/":
                index(req, res);
                break;
            case "/check":
                IdentityServiceCheck(req, res);
                break;
            default:
                show404(req,res);
        }
    }

    protected void show404(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        out.print("{\"message\": \"404 Not Found\"}");
        out.flush();
    }

    private  void index(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        out.print("{\"login_url\":\""+serverURL+"/v1/login\",\"token_check_url\":\""+serverURL+"/v1/check\"}");
        out.flush();
    }



    private void IdentityServiceCheck(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        serve((request, out) -> {
            try {
                Map<String, String> queryStringMap = splitQuery(req.getQueryString());
                int status;
                int uid=0;
                String token = queryStringMap.get("token");
                String userAgent = queryStringMap.get("userAgent");
                String ipAddress = queryStringMap.get("ipAddress");
                System.out.println(token+userAgent+ipAddress);
                boolean isExpired=true, isDifferentUserAgent=true, isDifferentIP=true;
                PreparedStatement stmt=conn.prepareStatement("SELECT * FROM token WHERE token = ?");
                stmt.setString(1,token);
                ResultSet result = stmt.executeQuery();
                Timestamp timeNow = new Timestamp(Calendar.getInstance().getTime().getTime());
                result.beforeFirst();
                while (result.next()){ //Cek apakah useragent sama
                    System.out.println(result.getString("useragent"));
                    isDifferentUserAgent=(!result.getString("useragent").equals(userAgent));
                    if (!isDifferentUserAgent){
                        break;
                    }
                }
                result.beforeFirst();
                while (result.next()){ //Cek apakah ip sama
                    System.out.println(result.getString("ip"));
                    System.out.println(ipAddress);
                    isDifferentIP=(!result.getString("ip").equals(ipAddress));
                    System.out.println(isDifferentIP);
                    if (!isDifferentIP){
                        break;
                    }
                }
                result.beforeFirst();
                while (result.next()){ //cek apakah expired atau tidak
                    isExpired=(timeNow.after(result.getTimestamp("expired")));
                    if (!isExpired){
                        break;
                    }
                }
                if ((!isDifferentIP)&&(!isExpired)&&(!isDifferentUserAgent)){
                    result.first();
                    uid = result.getInt("uid"); //kalau useragent sama, ip sama, dan tidak expired, return cari uid nya
                }
                result.beforeFirst();
                if (!result.next()){
                    status = 0;
                } else if (isExpired){ //return -1 kalau expired
                    status = -1;
                } else if (isDifferentIP) { //return -2 kalau beda ip
                    status = -2;
                } else if (isDifferentUserAgent){ //return -3 kalau beda useragent
                    status = -3;
                } else { //return uid kalau valid
                    status = uid;
                }

                out.print("{\"uid\":\""+status+"\"}");
                out.flush();
            } catch (Exception e) {
                res.setStatus(500);
                e.printStackTrace();
                out.print("{\"error\":\""+e.getMessage()+"\"}");
                out.flush();
            }
        }, req, res);
    }

    private void IdentityServiceLogin(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        serve((request, out) -> {
            try {
                ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> map = new HashMap<String, Object>();
                map = mapper.readValue(request.getReader(), new TypeReference<Map<String, String>>(){});
                String email = (String)map.get("email");
                String password = (String)map.get("password");
                String ipAddress = request.getHeader("X-FORWARDED-FOR");
                if (ipAddress == null) {
                    ipAddress = request.getRemoteAddr();
                }
                String userAgent = request.getHeader("User-Agent");
                System.out.println(email+"-"+password+"-"+ipAddress+"-"+userAgent);

                if (checkAuthentication(email, password)) {
                    // Ambil id
                    PreparedStatement stmt=conn.prepareStatement("SELECT id FROM user WHERE email = ?");
                    stmt.setString(1, email);
                    ResultSet result = stmt.executeQuery();
                    result.next();
                    int uid = result.getInt("id");
                    String token = issueAuthorization(uid,userAgent,ipAddress);
                    System.out.println(token);
                    out.print("{\"status\":\"1\", \"token\":\""+ token + "\"}");
                } else {
                    out.print("{\"status\":\"0\"}");
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


    private boolean checkAuthentication(String email, String password) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) as count FROM user WHERE email=? AND password=MD5(?)");
        stmt.setString(1, email);
        stmt.setString(2, password);
        ResultSet result = stmt.executeQuery();

        result.next();
        return result.getInt("count") == 1;
    }

    private String issueAuthorization(int uid, String userAgent, String ipAddress) throws SQLException {
        Statement stmt = conn.createStatement();
        String token = UUID.randomUUID().toString();
        stmt.executeUpdate("INSERT INTO `token` (`uid`, `token`, `expired`, `useragent`, `ip`) VALUES ('"+uid+"', '"+token+"', NOW() + INTERVAL 12 HOUR,'"+userAgent+"','"+ipAddress+"');");
        return token;
    }

    private static Map<String, String> splitQuery(String queryString) throws UnsupportedEncodingException {
        Map<String, String> query_pairs = new LinkedHashMap<String, String>();
        String[] pairs = queryString.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
        }
        return query_pairs;
    }
}
