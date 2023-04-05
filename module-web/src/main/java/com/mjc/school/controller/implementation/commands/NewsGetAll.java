package com.mjc.school.controller.implementation.commands;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.interfaces.Command;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;

public class NewsGetAll extends NewsBaseCommand implements Command {

    public NewsGetAll(BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController) {
        super(newsController);
    }

    @Override
    public boolean execute() {
        newsController.readAll().forEach(System.out::println);
        return true;
    }
}
