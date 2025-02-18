package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import com.mjc.school.service.implementation.TagService;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TagController implements BaseController<TagDtoRequest, TagDtoResponse, Long> {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @Override
    public List<TagDtoResponse> readAll() {
        return tagService.readAll();
    }

    @Override
    public TagDtoResponse readById(Long id) {
        return tagService.readById(id);
    }

    @Override
    public TagDtoResponse create(TagDtoRequest createRequest) {
        return tagService.create(createRequest);
    }

    @Override
    public TagDtoResponse update(TagDtoRequest updateRequest) {
        return tagService.update(updateRequest);
    }

    @Override
    public boolean deleteById(Long id) {
        return tagService.deleteById(id);
    }
}
