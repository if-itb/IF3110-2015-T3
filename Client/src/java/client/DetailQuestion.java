package client;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import service.Answer;
import service.Exception_Exception;
import service.Question;
import service.StackExchangeService_Service;


@WebServlet(name = "DetailQuestion", urlPatterns = {"/detail"})
public class DetailQuestion extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8082/StackExchangeService/StackExchangeService.wsdl")
    private StackExchangeService_Service service;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception_Exception {
        response.setContentType("text/html;charset=UTF-8");
        

        int qid = Integer.parseInt(request.getParameter("idDetail"));
        
	RequestHandler rh = new RequestHandler(request);
	
	Question question = getQuestionWithoutValidation(qid);
	List<Answer> answers = getAnswers(qid);
	
	System.out.println(question.getQcontent());
	
	response.setContentType("text/html");
	request.setAttribute("question", question);
	request.setAttribute("answers", answers);
	if(rh.isHasToken()){
	    request.setAttribute("isAuthenticated", true);
	    request.setAttribute("username",rh.getUsername());
	}
	
	request.getRequestDispatcher("/detail.jsp").forward(request, response);
	

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code."
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception_Exception ex) {
            Logger.getLogger(DetailQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception_Exception ex) {
            Logger.getLogger(DetailQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private java.util.List<service.Answer> getAnswers(int qid) throws Exception_Exception {
        service.StackExchangeService port = service.getStackExchangeServicePort();
        return port.getAnswers(qid);
    }

    private Question getQuestionWithoutValidation(int qid) throws Exception_Exception {
	// Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
	// If the calling of port operations may lead to race condition some synchronization is required.
	service.StackExchangeService port = service.getStackExchangeServicePort();
	return port.getQuestionWithoutValidation(qid);
    }





}
