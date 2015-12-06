package Servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class voteupanswer extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String token = request.getParameter("token");
        String aid = request.getParameter("aid");
        String useragent = request.getHeader("user-agent");
        String ip = request.getHeader("X-FORWARDED-FOR");
        
        if(ip==null){
            ip = request.getRemoteAddr();
        }
        
//        Cookie ipCookie = new Cookie("ipCookie", ip);
//        ipCookie.setMaxAge(6*60*60);
//        ipCookie.setPath("/");
//        response.addCookie(ipCookie);
//        Cookie uaCookie = new Cookie("uaCookie", useragent);
//        uaCookie.setMaxAge(6*60*60);
//        uaCookie.setPath("/");
//        response.addCookie(uaCookie);

        try {
            PrintWriter out = response.getWriter();
            
            URL url = new URL("http://localhost:48567/StackExchangeCV/VoteUpAnswer");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input = "{\"token\":\""+token+"\",\"id_answer\":"+aid+",\"useragent\":\""+useragent+"\",\"ip\":\""+ip+"\"}";

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();
            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
		throw new RuntimeException("Failed : HTTP error code : "
			+ conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
            String output;
            while ((output = br.readLine()) != null) {
		out.println(output);
            }
            conn.disconnect();

	} catch (MalformedURLException e) {
            e.printStackTrace();
	} catch (IOException e) {
            e.printStackTrace();
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
