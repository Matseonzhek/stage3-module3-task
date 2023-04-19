package com.mjc.school.repository.model;

import com.mjc.school.repository.BaseEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "news")
public class NewsModel implements BaseEntity<Long> {
    @ManyToOne
    private AuthorModel authorModel;

    @OneToMany(mappedBy = "newsModel", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<NewsTagModel> taggedNews = new ArrayList<>();

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


    public NewsModel(Long id, String title, String content, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.updateDate = updateDate;
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

    public AuthorModel getAuthorModel() {
        return authorModel;
    }

    public void setAuthorModel(AuthorModel authorModel) {
        this.authorModel = authorModel;
    }

    public List<NewsTagModel> getTaggedNews() {
        return taggedNews;
    }

    public void setTaggedNews(List<NewsTagModel> taggedNews) {
        this.taggedNews = taggedNews;
    }

    @Transactional
    public void addTag(TagModel tagModel) {
        NewsTagModel newsTagModel = new NewsTagModel(this, tagModel);
        taggedNews.add(newsTagModel);
        tagModel.getTags().add(newsTagModel);
    }


    public void removeTag(TagModel tagModel) {
        NewsTagModel newsTagModel = new NewsTagModel(this, tagModel);
        tagModel.getTags().remove(newsTagModel);
        taggedNews.remove(newsTagModel);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsModel newsModel = (NewsModel) o;
        return authorModel.equals(newsModel.authorModel) && id.equals(newsModel.id) && title.equals(newsModel.title) && content.equals(newsModel.content) && createDate.equals(newsModel.createDate) && updateDate.equals(newsModel.updateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorModel, id, title, content, createDate, updateDate);
    }
}
