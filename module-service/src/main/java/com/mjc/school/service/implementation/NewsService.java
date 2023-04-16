package com.mjc.school.service.implementation;


import com.mjc.school.repository.interfaces.NewsRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.annotation.Validate;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exception.NotFoundException;
import com.mjc.school.service.interfaces.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.mjc.school.service.constants.Constants.NEWS_NOT_EXIST;

@Component
public class
NewsService implements BaseService<NewsDtoRequest, NewsDtoResponse, Long> {

    private final NewsRepository newsRepository;

    @Autowired
    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public List<NewsDtoResponse> readAll() {
        return NewsMapper.INSTANCE.listNewsToNewsDtoResponse(newsRepository.findAll());
    }

    @Validate(value = "checkNewsId")
    @Override
    public NewsDtoResponse readById(Long id) {
        if (newsRepository.existsById(id)) {
            Optional<NewsModel> optionalNewsModel = newsRepository.findById(id);
            return NewsMapper.INSTANCE.newsToNewsDtoResponse(optionalNewsModel.get());
        } else {
            throw new NotFoundException(NEWS_NOT_EXIST);
        }
    }

    @Override
    @Validate(value = "checkNews")
    public NewsDtoResponse create(NewsDtoRequest createRequest) {
        NewsModel newsModel = NewsMapper.INSTANCE.newsDtoRequestToNews(createRequest);
        NewsModel createdNewsModel = newsRepository.save(newsModel);
        return NewsMapper.INSTANCE.newsToNewsDtoResponse(createdNewsModel);
    }

    @Validate(value = "checkNews")
    @Override
    public NewsDtoResponse update(NewsDtoRequest updateRequest) {
        if (newsRepository.existsById(updateRequest.getId())) {
            NewsModel newsModel = newsRepository.findById(updateRequest.getId()).get();
            newsModel.setTitle(updateRequest.getTitle());
            newsModel.setContent(updateRequest.getContent());
            newsModel.setAuthorId(updateRequest.getAuthorId());
            NewsModel updatedNewsModel = newsRepository.saveAndFlush(newsModel);
            return NewsMapper.INSTANCE.newsToNewsDtoResponse(updatedNewsModel);
        } else {
            throw new NotFoundException(NEWS_NOT_EXIST);
        }
    }

    @Validate(value = "checkNewsId")
    @Override
    public boolean deleteById(Long id) {
        if (newsRepository.existsById(id)) {
            newsRepository.deleteById(id);
            return true;
        } else {
            throw new NotFoundException(NEWS_NOT_EXIST);
        }
    }
}
