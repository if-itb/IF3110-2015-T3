
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.jws.WebParam;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tama
 */
public class Function {
     static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
     static final String DB_URL = "jdbc:mysql://localhost/stack";
    
     static final String USER = "root";
     static final String PASS = "" ;
     
    public Function(){}
    
    public int statusToken(String t,String ipa,String browser) throws ParseException {
        int ret = 0;
        JSONParser parser = new JSONParser();  
        String msg="";
        try { 
            URL url = new URL("http://localhost:39854/IdentityService/ISValid");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept-Charset","UTF-8");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            String query = String.format("token=%s&ipa=%s&browser=%s",URLEncoder.encode(t, "UTF-8"),URLEncoder.encode(ipa, "UTF-8"),URLEncoder.encode(browser, "UTF-8"));
          
            try (OutputStream output = conn.getOutputStream()) {
                output.write(query.getBytes("UTF-8"));
            }
            InputStream istream = conn.getInputStream();
            System.out.println(istream);
      
            BufferedReader bufread = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
            Object obj;
            JSONObject jsonobj;
      
            while ((output = bufread.readLine()) != null) {
              obj = parser.parse(output);
              jsonobj = (JSONObject) obj;
              msg = String.valueOf(jsonobj.get("status"));
              System.out.println("Status token (message) : |"+msg+"|");
                }      
                conn.disconnect();
                } catch (MalformedURLException e) {
                      e.printStackTrace();
                } catch (IOException e) {
                      e.printStackTrace();
               }
            ret = Integer.valueOf(msg);
            System.out.println("Status token (return) : |"+ret+"|");
            return ret;
        }
    
        public String getTokenOwner(String t) {
             Connection conn =null;
            String ret ="";
            String sql ="";
            Statement stmt = null;
            ResultSet rs = null;
        try {
                Class.forName("com.mysql.jdbc.Driver");                
                conn = DriverManager.getConnection(DB_URL, USER,PASS);               
                sql = "SELECT username FROM token WHERE token='"+t+"'";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                rs.last() ;
                int size =rs.getRow();
                if (size!=0) {
                    ret = rs.getString("username");
                }
                stmt.close();
                conn.close();
            }
            catch (Exception e) {
                e.printStackTrace() ;
            }
        System.out.println("getTokenOwner return :  |"+ret+"|");
        return ret ;
      }
        
      public int addComment(String at,int idc,int ida,String c,String ipa,String browser) {
            int status = 0;
            int r=0;
             try {
                r = statusToken(at,ipa,browser);
            } catch (ParseException ex) {
               r=0;
            }
            status=r;
            if (r>0) {
                Connection conn = null ; 
                Statement stmt = null ;
                ResultSet rs =null; 
                String username = getTokenOwner(at) ;
                try {
                    Class.forName("com.mysql.jdbc.Driver");                
                    conn = DriverManager.getConnection(DB_URL, USER,PASS);               
                    String sql = "INSERT INTO `comment` (`id_c`, `q_id`, `content`, `date`,`username`) VALUES ("+idc+","+ida+",'"+c+"',CURRENT_TIMESTAMP,'"+username+"')";
                    System.out.println("addComment sql : "+sql);
                    stmt = conn.createStatement();                                    
                    stmt.executeUpdate(sql);
                    stmt.close();
                    conn.close();
                }
                catch (Exception ee) {
                    ee.printStackTrace() ;
                }
            }            
            return status ;
        }
      
       public int getVote(String t,int id) {
           Connection conn = null ; 
            Statement stmt = null ;
            ResultSet rs =null;
            int ret = 0 ;
            String sql;
            try {
                Class.forName("com.mysql.jdbc.Driver");                
                conn = DriverManager.getConnection(DB_URL, USER,PASS);  
                if (t.equals("q")) sql = "SELECT vote FROM question WHERE id_q="+id;
                else sql = "SELECT vote FROM answer WHERE id_a="+id;
                stmt= conn.createStatement();
                rs = stmt.executeQuery(sql);                
                rs.next();
                ret = rs.getInt("vote");
                stmt.close();
                conn.close();
            }
            catch (Exception e) {
                e.printStackTrace() ;
            }
            return ret ;
        }        
       
       public int changeVote(String at,String t,int id,int num,String ipa,String browser) {
            Connection conn = null ; 
            Statement stmt = null ;
            ResultSet rs =null;
            int status = 0;
            int r=0;
             try {
                r = statusToken(at,ipa,browser);
            } catch (ParseException ex) {
               r=0;
            }
            status=r;
            String sql;
            if (r>0) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");                
                    conn = DriverManager.getConnection(DB_URL, USER,PASS);  
                    sql = "SELECT * FROM voted WHERE id_v="+id+" AND id_u="+r;
                    stmt= conn.createStatement();
                    rs = stmt.executeQuery(sql);
                    rs.last();
                    int size = rs.getRow();
                    if (size==1) status=-999;
                    else {                    
                        if (t.equals("q")) sql = "UPDATE question SET vote=(vote+"+num+") WHERE id_q="+id;
                        else sql = "UPDATE answer SET vote=(vote+"+num+") WHERE id_a="+id;
                        stmt= conn.createStatement();
                        stmt.executeUpdate(sql);
                        String kode = String.valueOf(id)+"-"+String.valueOf(r);
                        String sql2 = "INSERT INTO voted (kode,id_v,id_u) VALUES ('"+kode+"',"+id+","+r+")";
                        stmt= conn.createStatement();
                        stmt.executeUpdate(sql2);
                        stmt.close();
                        conn.close();
                    }
                }
                catch (Exception e) {
                    e.printStackTrace() ;
                }
            }
            return status ;
        }

     public ArrayList<CComment> getAllComment(int id_a) {
            Connection conn = null ; 
            Statement stmt = null ;
            ResultSet rs =null;
            ArrayList<CComment> ret = new ArrayList<CComment>() ;
            
            try {
                Class.forName("com.mysql.jdbc.Driver");                
                conn = DriverManager.getConnection(DB_URL, USER,PASS);               
                String sql = "SELECT * FROM comment WHERE q_id="+id_a;
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);                
                while (rs.next()) {
                    CComment C = new CComment();
                    int aa = rs.getInt("id_c");
                    int bb = rs.getInt("q_id");
                    C.id_c = aa;
                    C.q_id = bb;
                    C.content = rs.getString("content");
                    C.date = rs.getString("date");
                    C.username = rs.getString("username");
                    ret.add(C);
                }
                stmt.close();
                conn.close();
            }
            catch (Exception e) {
                e.printStackTrace() ;
            }
            return ret ;
        }
        
}
