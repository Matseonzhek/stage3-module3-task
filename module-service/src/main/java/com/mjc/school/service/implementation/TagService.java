package com.mjc.school.service.implementation;

import com.mjc.school.repository.interfaces.TagRepository;
import com.mjc.school.repository.model.TagModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.annotation.Validate;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import com.mjc.school.service.exception.NotFoundException;
import com.mjc.school.service.interfaces.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mjc.school.service.constants.Constants.TAG_NOT_EXIST;

@Service
public class TagService implements BaseService<TagDtoRequest, TagDtoResponse, Long> {

    private final TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }


    @Override
    public List<TagDtoResponse> readAll() {
        return TagMapper.INSTANCE.listTagToTagDtoResponse(tagRepository.findAll());
    }

    @Validate
    @Override
    public TagDtoResponse readById(Long id) {
        if (tagRepository.existsById(id)) {
            return TagMapper.INSTANCE.tagToTagDtoResponse(tagRepository.findById(id).get());
        } else throw new NotFoundException(TAG_NOT_EXIST);
    }

    @Validate
    @Override
    public TagDtoResponse create(TagDtoRequest createRequest) {
        return TagMapper.INSTANCE.tagToTagDtoResponse(tagRepository.save(TagMapper.INSTANCE.tagDtoRequestToTag(createRequest)));
    }

    @Validate
    @Override
    public TagDtoResponse update(TagDtoRequest updateRequest) {
        if (tagRepository.existsById(updateRequest.getId())) {
            TagModel tagModel = tagRepository.findById(updateRequest.getId()).get();
            tagModel.setName(updateRequest.getName());
            return TagMapper.INSTANCE.tagToTagDtoResponse(tagModel);
        } else throw new NotFoundException(TAG_NOT_EXIST);
    }

    @Validate
    @Override
    public boolean deleteById(Long id) {
        if (tagRepository.existsById(id)) {
            tagRepository.deleteById(id);
            return true;
        } else throw new NotFoundException(TAG_NOT_EXIST);
    }
}
