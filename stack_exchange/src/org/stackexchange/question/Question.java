package org.stackexchange.question;

public class Question{
    private long id;
    private long user_id;
    private String topic;
    private String content;
    private String created_at;
    private long vote;

    public Question(long id, long user_id, String topic, String content, String created_at, long vote) {
        this.id = id;
        this.user_id = user_id;
        this.topic = topic;
        this.content = content;
        this.created_at = created_at;
        this.vote = vote;
    }

    public long getId() {
        return id;
    }

    public long getUser_id() {
        return user_id;
    }

    public String getTopic() {
        return topic;
    }

    public String getContent() {
        return content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public long getVote() {
        return vote;
    }
}