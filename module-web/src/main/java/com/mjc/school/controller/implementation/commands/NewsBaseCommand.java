package com.mjc.school.controller.implementation.commands;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;

public abstract class NewsBaseCommand {

    protected final BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController;

    protected NewsBaseCommand(BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController) {
        this.newsController = newsController;
    }
}
