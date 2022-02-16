package dto;

import java.time.LocalDateTime;

public class WorkLogEntry {

    private Integer id;
    private String comment;
    private Integer timeSpent;
    private String author;
    private String authorFullName;
    private LocalDateTime created;
    private LocalDateTime startDate;
    private String updateAuthor;
    private String updateAuthorFullName;
    private LocalDateTime updated;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(Integer timeSpent) {
        this.timeSpent = timeSpent;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorFullName() {
        return authorFullName;
    }

    public void setAuthorFullName(String authorFullName) {
        this.authorFullName = authorFullName;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public String getUpdateAuthor() {
        return updateAuthor;
    }

    public void setUpdateAuthor(String updateAuthor) {
        this.updateAuthor = updateAuthor;
    }

    public String getUpdateAuthorFullName() {
        return updateAuthorFullName;
    }

    public void setUpdateAuthorFullName(String updateAuthorFullName) {
        this.updateAuthorFullName = updateAuthorFullName;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "WorkLogEntry{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", timeSpent=" + timeSpent +
                ", author='" + author + '\'' +
                ", authorFullName='" + authorFullName + '\'' +
                ", created=" + created +
                ", startDate=" + startDate +
                ", updateAuthor='" + updateAuthor + '\'' +
                ", updateAuthorFullName='" + updateAuthorFullName + '\'' +
                ", updated=" + updated +
                '}';
    }
}
