package com.mjc.school.service.implementation;

import com.mjc.school.repository.implementation.AuthorRepository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.service.annotation.Validate;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.exception.NotFoundException;
import com.mjc.school.service.interfaces.AuthorMapper;
import com.mjc.school.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.mjc.school.service.constants.Constants.AUTHOR_NOT_EXIST;

@Service(value = "authorService")
public class AuthorService implements BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Validate
    @Override
    public List<AuthorDtoResponse> readAll() {
        return AuthorMapper.INSTANCE.listAuthorToAuthorDtoResponse(authorRepository.readAll());
    }

    @Validate (value = "checkAuthorId")
    @Override
    public AuthorDtoResponse readById(Long id) {
        if (authorRepository.existById(id)) {
            return AuthorMapper.INSTANCE.authorModelToAuthorDtoResponse(authorRepository.readById(id).get());
        } else {
            throw new NotFoundException(AUTHOR_NOT_EXIST);
        }
    }

    @Validate(value = "checkAuthor")
    @Override
    public AuthorDtoResponse create(AuthorDtoRequest createRequest) {
        AuthorModel authorModel = AuthorMapper.INSTANCE.authorDtoRequestToAuthorModel(createRequest);
        LocalDateTime localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        authorModel.setCreatedDate(localDateTime);
        authorModel.setUpdatedDate(localDateTime);
        AuthorModel createdAuthorModel = authorRepository.create(authorModel);
        return AuthorMapper.INSTANCE.authorModelToAuthorDtoResponse(createdAuthorModel);
    }

    @Validate(value = "checkAuthor")
    @Override
    public AuthorDtoResponse update(AuthorDtoRequest updateRequest) {
        if (authorRepository.existById(updateRequest.getId())) {
            AuthorModel authorModel = AuthorMapper.INSTANCE.authorDtoRequestToAuthorModel(updateRequest);
            LocalDateTime localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
            authorModel.setName(updateRequest.getName());
            authorModel.setUpdatedDate(localDateTime);
            return AuthorMapper.INSTANCE.authorModelToAuthorDtoResponse(authorRepository.update(authorModel));
        } else {
            throw new NotFoundException(AUTHOR_NOT_EXIST);
        }
    }

    @Validate(value = "checkAuthorId")
    @Override
    public boolean deleteById(Long id) {
        if (authorRepository.existById(id)) {
            return authorRepository.deleteById(id);
        } else {
            throw new NotFoundException(AUTHOR_NOT_EXIST);
        }

    }
}
