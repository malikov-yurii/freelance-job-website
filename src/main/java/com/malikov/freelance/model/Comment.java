package com.malikov.freelance.model;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = Comment.DELETE, query = "DELETE FROM Comment c WHERE c.id=:id"),
        @NamedQuery(name = Comment.ALL_SORTED, query = "SELECT c FROM Comment c ORDER BY c.datePlaced ASC"),
})
@Entity
@Table(name = "comments")
public class Comment extends BaseEntity{

    public static final String DELETE = "Comment.delete";
    public static final String ALL_SORTED = "Comment.getAllSorted";

    @Column(name = "project_id")
    private Integer projectId;

    @Column(name = "date_placed", columnDefinition = "timestamp default now()")
    @DateTimeFormat(pattern = "yyyy-MM-dd yyyy-MM-dd HH:mm:ss.SSSSSS")
    private LocalDateTime datePlaced = LocalDateTime.now();

    @Column(name = "user_full_name")
    private String userFullName;

    @Column(name = "comment_text")
    private String text;

    @Column(name = "blocked")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean blocked;

    public Comment(){}

    public Comment(Comment comment){
        this(comment.getId(), comment.getProjectId(), comment.getDatePlaced(), comment.getUserFullName(), comment.getText());
    }

    public Comment(Integer projectId, LocalDateTime datePlaced, String userFullName, String text) {
        this.projectId = projectId;
        this.datePlaced = datePlaced;
        this.userFullName = userFullName;
        this.text = text;
        this.blocked = Boolean.FALSE;
    }

    public Comment(Integer id, Integer projectId, LocalDateTime datePlaced, String userFullName, String text) {
        super(id);
        this.projectId = projectId;
        this.datePlaced = datePlaced;
        this.userFullName = userFullName;
        this.text = text;
        this.blocked = Boolean.FALSE;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public LocalDateTime getDatePlaced() {
        return datePlaced;
    }

    public void setDatePlaced(LocalDateTime datePlaced) {
        this.datePlaced = datePlaced;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    @Override
    public String toString() {
        return "Comment{" +
                "projectId=" + projectId +
                ", datePlaced=" + datePlaced +
                ", userFullName='" + userFullName + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        if (!super.equals(o)) return false;
        Comment comment = (Comment) o;
        return Objects.equals(projectId, comment.projectId) &&
                Objects.equals(datePlaced, comment.datePlaced) &&
                Objects.equals(userFullName, comment.userFullName) &&
                Objects.equals(text, comment.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), projectId, datePlaced, userFullName, text);
    }
}
