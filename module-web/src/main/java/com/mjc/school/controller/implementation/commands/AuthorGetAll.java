package com.mjc.school.controller.implementation.commands;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.interfaces.Command;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;

public class AuthorGetAll extends AuthorBaseCommand implements Command {

    public AuthorGetAll(BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController) {
        super(authorController);
    }

    @Override
    public boolean execute() {
        authorController.readAll().forEach(System.out::println);
        return true;
    }
}
