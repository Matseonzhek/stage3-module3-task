package com.mjc.school.controller.implementation.commands;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.interfaces.Command;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;

public class TagGetAll extends TagBaseCommand implements Command {


    public TagGetAll(BaseController<TagDtoRequest, TagDtoResponse, Long> tagController) {
        super(tagController);
    }

    @Override
    public boolean execute() {
        tagController.readAll().forEach(System.out::println);
        return true;
    }
}
