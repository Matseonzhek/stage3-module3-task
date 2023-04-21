package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.TagModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class NewsRepository implements BaseRepository<NewsModel, Long> {

    private final EntityManager entityManager;

    @Autowired
    public NewsRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<NewsModel> readAll() {
        String jpql = "select news from NewsModel news order by news.id";
        TypedQuery<NewsModel> query = entityManager.createQuery(jpql, NewsModel.class);
        return query.getResultList();
    }

    @Override
    public Optional<NewsModel> readById(Long id) {
        NewsModel newsModel = entityManager.find(NewsModel.class, id);
        return Optional.of(newsModel);
    }

    @Transactional
    @Override
    public NewsModel create(NewsModel entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public NewsModel update(NewsModel entity) {
        long id = entity.getId();
        if (existById(id)) {
            NewsModel updatedNewsModel = readById(id).get();
            updatedNewsModel.setTitle(entity.getTitle());
            updatedNewsModel.setContent(entity.getContent());
            updatedNewsModel.setAuthorModel(entity.getAuthorModel());
            entityManager.persist(updatedNewsModel);
            return updatedNewsModel;
        }
        return null;
    }

    @Transactional
    @Override
    public boolean deleteById(Long id) {
        if (existById(id)) {
            NewsModel newsModel = readById(id).get();
            entityManager.remove(newsModel);
            return true;
        }
        return false;
    }

    @Override
    public boolean existById(Long id) {
        return readById(id).isPresent();
    }

    public AuthorModel getAuthorByNewsId(Long id) {
        return (AuthorModel) entityManager.createQuery("select authors from AuthorModel authors, NewsModel news" +
                " where news.authorModel = authors and news.id=?1").setParameter(1, id).getSingleResult();
    }

    public List<TagModel> getTagByNewsId(Long id) {
        String jpql = " select tagModel from TagModel " +
                " tagModel, NewsTagModel as newsTagModel, NewsModel as newsModel " +
                " where tagModel = newsTagModel.tagModel " +
                "and newsTagModel.newsModel = newsModel " +
                "and newsModel.id=?1";
        TypedQuery<TagModel> query = entityManager.createQuery(jpql,TagModel.class).setParameter(1,id);
        return query.getResultList();
    }
}