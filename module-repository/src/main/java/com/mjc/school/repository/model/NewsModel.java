package com.mjc.school.repository.model;

import com.mjc.school.repository.BaseEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "news")
public class NewsModel implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "createdDate", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createDate;
    @Column(name = "updatedDate", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateDate;
    @Column(name = "authorId")
    private long authorId;


    public NewsModel(Long id, String title, String content, LocalDateTime createDate, LocalDateTime updateDate, long authorId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.authorId = authorId;
    }

    public NewsModel() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsModel newsModel = (NewsModel) o;
        return authorId == newsModel.authorId && id.equals(newsModel.id) && title.equals(newsModel.title) && content.equals(newsModel.content) && createDate.equals(newsModel.createDate) && updateDate.equals(newsModel.updateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, createDate, updateDate, authorId);
    }

    @Override
    public String toString() {
        return "NewsModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                ", lastUpdateDate=" + updateDate +
                ", authorId=" + authorId +
                '}';
    }
}
