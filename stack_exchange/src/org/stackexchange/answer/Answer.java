package org.stackexchange.answer;

public class Answer {
    private long id;
    private long user_id;
    private long question_id;
    private String content;
    private long vote;
    private String created_at;

    public Answer(long id, long userid, long questionid, String content, long vote, String createdAt) {
        this.id = id;
        user_id = userid;
        question_id = questionid;
        this.content = content;
        this.vote = vote;
        created_at = createdAt;
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return user_id;
    }

    public long getQuestionId() {
        return question_id;
    }

    public String getContent() {
        return content;
    }
    public long getVote() {
        return vote;
    }

    public String getCreatedAt() {
        return created_at;
    }
}