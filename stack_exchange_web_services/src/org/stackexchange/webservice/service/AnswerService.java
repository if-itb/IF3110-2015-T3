package org.stackexchange.webservice.service;

import org.stackexchange.Answer;
import org.stackexchange.webservice.dao.AnswerDao;

import java.util.List;

public class AnswerService {
    public Answer addAnswer(String content, long questionId, String token) {
        AnswerDao answerDao = new AnswerDao();
        return null;
    }

    public List<Answer> getByQuestionId(long questionId) {
        AnswerDao answerDao = new AnswerDao();
        return answerDao.getByQuestionId(questionId);
    }

    public boolean upvote(long questionId, String token) {
        return false;
    }

    public boolean downvote(long questionId, String token) {
        return false;
    }
}
