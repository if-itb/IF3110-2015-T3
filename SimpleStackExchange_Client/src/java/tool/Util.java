/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author mfikria
 */
public class Util {
    
     public static Boolean isLogin(HttpServletRequest request) {
        
        ConsumerREST qr = new ConsumerREST();
        
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                Integer res = qr.auth(cookie.getValue());
                if(res == -1) {
                    
                }
                if(cookie.getName().equals("token") && res == 1) {
                     return true;
                }
            }
        }
        return false;
    }
     
     public static Boolean isAuthUser(HttpServletRequest request, int theUid) {
        
        ConsumerREST qr = new ConsumerREST(); 
        
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("token") && qr.auth(cookie.getValue()) == 1) {
                    int uid = qr.getUidByToken(cookie.getValue());
                   
                    if(uid != 0 && uid == theUid) {
                        return true;
                    }
                }
            }
        }
        return false;
     }

    
    public static Integer getUid(HttpServletRequest request) {
        ConsumerREST qr = new ConsumerREST();
        
         Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("token") && qr.auth(cookie.getValue()) == 1) {
                     Integer uid =  qr.getUidByToken(cookie.getValue());
                     if(uid > 0) return uid;
                     else return null;
                }
            }
        }
        return null;
    }
    
    public static String getTokenCookie(HttpServletRequest request) {
        String token = new String();
        Cookie[] cookies = request.getCookies();
        if(cookies !=null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("token")) token = cookie.getValue();
            }
        }
        return token;
    }

    public static String getNameByUid(int uid) {
        user.UserWS_Service service = new user.UserWS_Service();
        user.UserWS port = service.getUserWSPort();
        return port.getNameByUid(uid);
    }

    public static Boolean hasVotedAnswer(int aid, int uid, int status) {
        answer.AnswerWS_Service service = new answer.AnswerWS_Service();
        answer.AnswerWS port = service.getAnswerWSPort();
        return port.hasVotedAnswer(aid, uid, status);
    }

    public static Boolean hasVotedQuestion(int qid, int uid, int status) {
        question.QuestionWS_Service service = new question.QuestionWS_Service();
        question.QuestionWS port = service.getQuestionWSPort();
        return port.hasVotedQuestion(qid, uid, status);
    }





}
