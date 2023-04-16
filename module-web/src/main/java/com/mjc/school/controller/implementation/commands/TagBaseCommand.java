package com.mjc.school.controller.implementation.commands;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;

public abstract class TagBaseCommand {
    protected final BaseController<TagDtoRequest, TagDtoResponse, Long> tagController;

    public TagBaseCommand(BaseController<TagDtoRequest, TagDtoResponse, Long> tagController) {
        this.tagController = tagController;
    }
}
