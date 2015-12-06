/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.Vote;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author yoga
 */
@Entity
@Table(name = "questions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Questions.findAll", query = "SELECT q FROM Questions q"),
    @NamedQuery(name = "Questions.findByQuestionID", query = "SELECT q FROM Questions q WHERE q.questionID = :questionID"),
    @NamedQuery(name = "Questions.findByVotes", query = "SELECT q FROM Questions q WHERE q.votes = :votes"),
    @NamedQuery(name = "Questions.findByAnswers", query = "SELECT q FROM Questions q WHERE q.answers = :answers"),
    @NamedQuery(name = "Questions.findByTopic", query = "SELECT q FROM Questions q WHERE q.topic = :topic"),
    @NamedQuery(name = "Questions.findByQuestion", query = "SELECT q FROM Questions q WHERE q.question = :question"),
    @NamedQuery(name = "Questions.findByName", query = "SELECT q FROM Questions q WHERE q.name = :name"),
    @NamedQuery(name = "Questions.findByEmail", query = "SELECT q FROM Questions q WHERE q.email = :email"),
    @NamedQuery(name = "Questions.findByDatetime", query = "SELECT q FROM Questions q WHERE q.datetime = :datetime")})
public class Questions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "QuestionID")
    private Integer questionID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Votes")
    private int votes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Answers")
    private int answers;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "Topic")
    private String topic;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9999)
    @Column(name = "Question")
    private String question;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Name")
    private String name;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionID")
    private Collection<Answers> answersCollection;

    public Questions() {
    }

    public Questions(Integer questionID) {
        this.questionID = questionID;
    }

    public Questions(Integer questionID, int votes, int answers, String topic, String question, String name, String email, Date datetime) {
        this.questionID = questionID;
        this.votes = votes;
        this.answers = answers;
        this.topic = topic;
        this.question = question;
        this.name = name;
        this.email = email;
        this.datetime = datetime;
    }

    public Integer getQuestionID() {
        return questionID;
    }

    public void setQuestionID(Integer questionID) {
        this.questionID = questionID;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public int getAnswers() {
        return answers;
    }

    public void setAnswers(int answers) {
        this.answers = answers;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    @XmlTransient
    public Collection<Answers> getAnswersCollection() {
        return answersCollection;
    }

    public void setAnswersCollection(Collection<Answers> answersCollection) {
        this.answersCollection = answersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (questionID != null ? questionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Questions)) {
            return false;
        }
        Questions other = (Questions) object;
        if ((this.questionID == null && other.questionID != null) || (this.questionID != null && !this.questionID.equals(other.questionID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "is.VoteUpQuestion.Questions[ questionID=" + questionID + " ]";
    }
    
}
