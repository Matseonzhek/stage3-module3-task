package com.mjc.school.repository.interfaces;

import com.mjc.school.repository.model.NewsModel;
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



}
