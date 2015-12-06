import com.yangnormal.sstackex.*;

import java.lang.Exception;
import java.util.ArrayList;

public class Client{
    public static void main(String[] args) throws Exception {
        WebServiceImplService webService = new WebServiceImplService();
        WebServiceInterface wsi = webService.getWebServiceImplPort();
        Question q = wsi.getQuestion(1);
        System.out.println(q.getUser().getName());

        QuestionArray qList = wsi.getQuestionList();
        AnswerArray aList = wsi.getAnswerList(1);
        System.out.println(aList.getItem().size());
        System.out.println(qList.getItem().size());
        ArrayList<Question> QuestionList = (ArrayList<Question>) qList.getItem();
        System.out.println(QuestionList.get(0).getUser().getName());

        //System.out.println(questionList.toString());
        /*System.out.println("getQuestionList Test");
        for (int i=0;i<wsi.getQuestionList().getItem().size();i++){
            System.out.println(wsi.getQuestionList().getItem().get(i).getItem().toString());
        }
        System.out.println("getAnswerList Test");
        for (int i=0;i<wsi.getAnswerList(1).getItem().size();i++) {
            System.out.println(wsi.getAnswerList(1).getItem().get(i).getItem().toString());
        }
        Question q = new Question();
        System.out.println("register Test");
        System.out.println(wsi.register("john", "john@abc.com","abc"));*/
        System.out.println("Post Question Test");
        //wsi.postQuestion(4,"f41ea9be-218c-46ce-b56a-2d66a7698bfb","HAHA", "HAHAHAHAHA");
        /*System.out.println("Post Answer");
        wsi.postAnswer(1,1,"a", "HAHAHAHAHA");
        System.out.println("Vote Question Up Test");
        wsi.vote(0,1,1,1,"a");
        System.out.println("Vote Question Down Test");
        wsi.vote(0,1,-1,2,"a");
        System.out.println("Vote Answer Up Test");
        wsi.vote(1,1,1,1,"a");
        System.out.println("Vote Answer Down Test");
        wsi.vote(1,1,-1,2,"a");
        System.out.println("getQuestion Array Test");
        System.out.println(wsi.getQuestionArray(1).getItem().toString());
        System.out.println("getQuestion (object)test");
        q = wsi.getQuestion(1);
        System.out.println(q.getAnswerSum());
        System.out.println("Delete Question Test");
        wsi.deleteQuestion(3, 1, "a");*/
    }
}