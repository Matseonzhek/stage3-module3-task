package com.mjc.school.controller.implementation.commands;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;

public abstract class AuthorBaseCommand {

    protected final BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController;

    protected AuthorBaseCommand(BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController) {
        this.authorController = authorController;
    }
}
