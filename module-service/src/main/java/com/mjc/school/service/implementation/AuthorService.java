package com.mjc.school.service.implementation;

import com.mjc.school.repository.interfaces.AuthorRepository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.annotation.Validate;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.exception.NotFoundException;
import com.mjc.school.service.interfaces.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mjc.school.service.constants.Constants.AUTHOR_NOT_EXIST;

@Service(value = "authorService")
public class AuthorService implements BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<AuthorDtoResponse> readAll() {
        return AuthorMapper.INSTANCE.listAuthorToAuthorDtoResponse(authorRepository.findAll());
    }

    @Validate(value = "checkAuthorId")
    @Override
    public AuthorDtoResponse readById(Long id) {
        if (authorRepository.existsById(id)) {
            return AuthorMapper.INSTANCE.authorModelToAuthorDtoResponse(authorRepository.findById(id).get());
        } else {
            throw new NotFoundException(AUTHOR_NOT_EXIST);
        }
    }

    @Validate(value = "checkAuthor")
    @Override
    public AuthorDtoResponse create(AuthorDtoRequest createRequest) {
        AuthorModel authorModel = AuthorMapper.INSTANCE.authorDtoRequestToAuthorModel(createRequest);
        AuthorModel createdAuthorModel = authorRepository.save(authorModel);
        return AuthorMapper.INSTANCE.authorModelToAuthorDtoResponse(createdAuthorModel);
    }

    @Validate(value = "checkAuthor")
    @Override
    public AuthorDtoResponse update(AuthorDtoRequest updateRequest) {
        if (authorRepository.existsById(updateRequest.getId())) {
            AuthorModel authorModel = authorRepository.findById(updateRequest.getId()).get();
            authorModel.setName(updateRequest.getName());
            return AuthorMapper.INSTANCE.authorModelToAuthorDtoResponse(authorRepository.saveAndFlush(authorModel));
        } else {
            throw new NotFoundException(AUTHOR_NOT_EXIST);
        }
    }

    @Validate(value = "checkAuthorId")
    @Override
    public boolean deleteById(Long id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
            return true;
        } else {
            throw new NotFoundException(AUTHOR_NOT_EXIST);
        }

    }
}
