/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stackexchange.client;

import stackexchangews.services.SQLException_Exception;

/**
 *
 * @author davidkwan
 */
public class Testing {

    private static boolean logout(java.lang.String arg0) throws SQLException_Exception {
        stackexchangews.services.UserHandler_Service service = new stackexchangews.services.UserHandler_Service();
        stackexchangews.services.UserHandler port = service.getUserHandlerPort();
        return port.logout(arg0);
    }

    private static int deleteQuestion(int questionId) throws SQLException_Exception {
        stackexchangews.services.QuestionHandler_Service service = new stackexchangews.services.QuestionHandler_Service();
        stackexchangews.services.QuestionHandler port = service.getQuestionHandlerPort();
        return port.deleteQuestion(questionId);
    }
    
}
