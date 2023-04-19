package com.mjc.school.repository.interfaces;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.TagModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NewsRepository extends JpaRepository<NewsModel, Long> {

    @Query(value = "SELECT news from NewsModel news order by news.id")
    List<NewsModel> findAll();

    @Query(value = "select news from NewsModel news where news.id=:aLong")
    Optional<NewsModel> findById(@Param("aLong") Long aLong);

    @Query(value = "select authors from AuthorModel authors, NewsModel news where news.authorModel=authors.id and news.id=:id")
    AuthorModel getAuthorByNewsId(@Param("id") Long id);

    @Query(value = " select tagModel from TagModel " +
            " tagModel, NewsTagModel as newsTagModel, NewsModel as newsModel " +
            " where tagModel.id = newsTagModel.tagModel " +
            "and newsTagModel.newsModel = newsModel.id " +
            "and newsModel.id=:id")
    List<TagModel> getTagByNewsId(@Param("id") Long id);

}
