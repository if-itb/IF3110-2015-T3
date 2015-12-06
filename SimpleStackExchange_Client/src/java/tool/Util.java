/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;

import static java.lang.Math.log;
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


    public static String parseUserAgent(String useragent){
        String  browserDetails  =   useragent;
        String  userAgent       =   browserDetails;
        String  user            =   userAgent.toLowerCase();

        String browser = "";

         //===============Browser===========================
        if (user.contains("msie"))
        {
            String substring=userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
            browser=substring.split(" ")[0].replace("MSIE", "IE")+"-"+substring.split(" ")[1];
        } else if (user.contains("safari") && user.contains("version"))
        {
            browser=(userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0]+"-"+(userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
        } else if ( user.contains("opr") || user.contains("opera"))
        {
            if(user.contains("opera"))
                browser=(userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0]+"-"+(userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
            else if(user.contains("opr"))
                browser=((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-")).replace("OPR", "Opera");
        } else if (user.contains("chrome"))
        {
            browser=(userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
        } else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1)  || (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1) || (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1) )
        {
            //browser=(userAgent.substring(userAgent.indexOf("MSIE")).split(" ")[0]).replace("/", "-");
            browser = "Netscape-?";

        } else if (user.contains("firefox"))
        {
            browser=(userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
        } else if(user.contains("rv"))
        {
            browser="IE";
        } else
        {
            browser = "UnKnown, More-Info: "+userAgent;
        }
        return browser;
    }


}
