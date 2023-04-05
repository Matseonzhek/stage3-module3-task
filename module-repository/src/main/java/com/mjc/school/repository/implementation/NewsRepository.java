package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.source.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class NewsRepository implements BaseRepository<NewsModel, Long> {

    private final DataSource dataSource;

    @Autowired
    public NewsRepository(DataSource dataSource) {
        this.dataSource = DataSource.getDataSource();
    }

    @Override
    public List<NewsModel> readAll() {
        return dataSource.getNewsModelList();
    }

    @Override
    public Optional<NewsModel> readById(Long id) {

        return Optional.of(dataSource.getNewsModelList()
                .stream()
                .filter(newsModel -> Objects.equals(newsModel.getId(), id))
                .findFirst()
                .get());
    }

    @Override
    public NewsModel create(NewsModel entity) {
        List<NewsModel> newsModelList = dataSource.getNewsModelList();
        newsModelList.sort(Comparator.comparing(NewsModel::getId));
        if (!newsModelList.isEmpty()) {
            entity.setId(newsModelList.get(newsModelList.size() - 1).getId() + 1);
        } else {
            entity.setId(1L);
        }
        newsModelList.add(entity);
        return entity;
    }

    @Override
    public NewsModel update(NewsModel entity) {
        long id = entity.getId();
        Optional<NewsModel> updatedNewsModel = readById(id);
        if (updatedNewsModel.isPresent()) {
            updatedNewsModel.get().setTitle(entity.getTitle());
            updatedNewsModel.get().setContent(entity.getContent());
            updatedNewsModel.get().setUpdateDate(entity.getUpdateDate());
            updatedNewsModel.get().setAuthorId(entity.getAuthorId());
            return updatedNewsModel.get();
        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return dataSource.getNewsModelList().remove(readById(id).get());
    }

    @Override
    public boolean existById(Long id) {
        return dataSource
                .getNewsModelList()
                .stream()
                .anyMatch(newsModel -> Objects.equals(newsModel.getId(), id));
    }
}
