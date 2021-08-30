package engine.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Completion {

    @Id
    @GeneratedValue
    private Long id;

    private Long quizId;
    private Date completedAt;
    private String user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Date completedAt) {
        this.completedAt = completedAt;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public Completion(Long quizId, Date completedAt, String user) {
        this.quizId = quizId;
        this.completedAt = completedAt;
        this.user = user;
    }

    public Completion() {

    }
}
