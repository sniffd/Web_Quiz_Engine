package engine.model;

import engine.entity.Completion;

import java.util.Date;

public class CompletionDto {

    private long id;
    private Date completedAt;

    public CompletionDto(Completion completion) {
        this.id = completion.getQuizId();
        this.completedAt = completion.getCompletedAt();
    }

    public CompletionDto() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Date completedAt) {
        this.completedAt = completedAt;
    }
}
