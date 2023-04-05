package com.mjc.school.controller.implementation.commands;

import com.mjc.school.controller.constants.Constants;
import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.interfaces.Command;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;

import java.util.Scanner;


public class NewsCreate extends NewsBaseCommand implements Command {

    private final Scanner scanner;

    public NewsCreate(Scanner scanner, BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController) {
        super(newsController);
        this.scanner = scanner;
    }

    @Override
    public boolean execute() {
        System.out.println(Constants.NEWS_TITLE);
        String title = scanner.nextLine();
        System.out.println(Constants.NEWS_CONTENT);
        String content = scanner.nextLine();
        System.out.println(Constants.NEWS_AUTHOR_ID);
        long author_id = Long.parseLong(scanner.nextLine());
        NewsDtoRequest newsDtoRequest = new NewsDtoRequest(null, title, content, author_id);
        System.out.println(newsController.create(newsDtoRequest));
        return true;
    }
}
