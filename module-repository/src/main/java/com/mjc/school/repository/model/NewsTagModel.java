package com.mjc.school.repository.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "news_tag")
public class NewsTagModel implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private NewsModel newsModel;
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private TagModel tagModel;

    public NewsTagModel() {
    }

    public NewsTagModel(NewsModel newsModel, TagModel tagModel) {
        this.newsModel = newsModel;
        this.tagModel = tagModel;
    }

    public NewsModel getNewsModel() {
        return newsModel;
    }

    public void setNewsModel(NewsModel newsModel) {
        this.newsModel = newsModel;
    }

    public TagModel getTagModel() {
        return tagModel;
    }

    public void setTagModel(TagModel tagModel) {
        this.tagModel = tagModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsTagModel that = (NewsTagModel) o;
        return newsModel.equals(that.newsModel) && tagModel.equals(that.tagModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(newsModel, tagModel);
    }
}
