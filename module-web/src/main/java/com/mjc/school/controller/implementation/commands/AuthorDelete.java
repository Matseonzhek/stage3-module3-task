package com.mjc.school.controller.implementation.commands;

import com.mjc.school.controller.constants.Constants;
import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.interfaces.Command;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;

import java.util.Scanner;


public class AuthorDelete extends AuthorBaseCommand implements Command {

    private final Scanner scanner;

    public AuthorDelete(Scanner scanner, BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController) {
        super(authorController);
        this.scanner = scanner;
    }

    @Override
    public boolean execute() {
        System.out.println(Constants.AUTHOR_ID);
        long id = Long.parseLong(scanner.nextLine());
        authorController.deleteById(id);
        return true;
    }
}
