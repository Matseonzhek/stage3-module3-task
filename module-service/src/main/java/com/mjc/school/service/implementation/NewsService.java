package com.mjc.school.service.implementation;


import com.mjc.school.repository.interfaces.AuthorRepository;
import com.mjc.school.repository.interfaces.NewsRepository;
import com.mjc.school.repository.interfaces.TagRepository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.TagModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.annotation.Validate;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.dto.TagDtoResponse;
import com.mjc.school.service.exception.NotFoundException;
import com.mjc.school.service.interfaces.AuthorMapper;
import com.mjc.school.service.interfaces.NewsMapper;
import com.mjc.school.service.interfaces.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.mjc.school.service.constants.Constants.AUTHOR_NOT_EXIST;
import static com.mjc.school.service.constants.Constants.NEWS_NOT_EXIST;

@Component
public class
NewsService implements BaseService<NewsDtoRequest, NewsDtoResponse, Long> {

    private final NewsRepository newsRepository;
    private final AuthorRepository authorRepository;
    private final TagRepository tagRepository;

    @Autowired
    public NewsService(NewsRepository newsRepository, AuthorRepository authorRepository, TagRepository tagRepository) {
        this.newsRepository = newsRepository;
        this.authorRepository = authorRepository;
        this.tagRepository = tagRepository;
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
        if (authorRepository.existsById(createRequest.getAuthorId())) {
            NewsModel newsModel = NewsMapper.INSTANCE.newsDtoRequestToNews(createRequest);
            if (tagRepository.existsById(createRequest.getTagId())) {
                TagModel tagModel = tagRepository.findById(createRequest.getTagId()).get();
                newsModel.addTag(tagModel);
            }
            AuthorModel authorModel = authorRepository.findById(createRequest.getAuthorId()).get();
            newsModel.setAuthorModel(authorModel);
            NewsModel createdNewsModel = newsRepository.save(newsModel);
            return NewsMapper.INSTANCE.newsToNewsDtoResponse(createdNewsModel);
        } else throw new NotFoundException(AUTHOR_NOT_EXIST);

    }

    @Transactional
    @Validate(value = "checkNews")
    @Override
    public NewsDtoResponse update(NewsDtoRequest updateRequest) {
        if (newsRepository.existsById(updateRequest.getId())) {
            NewsModel newsModel = newsRepository.findById(updateRequest.getId()).get();
            if (authorRepository.existsById(updateRequest.getAuthorId())) {
                AuthorModel authorModel = authorRepository.findById(updateRequest.getAuthorId()).get();
                newsModel.setTitle(updateRequest.getTitle());
                newsModel.setContent(updateRequest.getContent());
                newsModel.setAuthorModel(authorModel);
                if (tagRepository.existsById(updateRequest.getTagId())) {
                    TagModel tagModel = tagRepository.findById(updateRequest.getTagId()).get();
                    newsModel.addTag(tagModel);
                }
                NewsModel updatedNewsModel = newsRepository.saveAndFlush(newsModel);
                return NewsMapper.INSTANCE.newsToNewsDtoResponse(updatedNewsModel);
            } else throw new NotFoundException(AUTHOR_NOT_EXIST);
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


    public AuthorDtoResponse getAuthorByNewsId(Long id) {
        if (newsRepository.existsById(id)) {
            return AuthorMapper.INSTANCE.authorModelToAuthorDtoResponse(newsRepository.getAuthorByNewsId(id));
        } else throw new NotFoundException(NEWS_NOT_EXIST);
    }

    public List<TagDtoResponse> getTagByNewsId(Long id) {
        if (newsRepository.existsById(id)) {
            return TagMapper.INSTANCE.listTagToTagDtoResponse(newsRepository.getTagByNewsId(id));
        } else throw new NotFoundException(NEWS_NOT_EXIST);
    }
}
