package com.mjc.school.controller.implementation.commands;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.constants.Constants;
import com.mjc.school.controller.interfaces.Command;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;

import java.util.Scanner;

public class TagDelete extends TagBaseCommand implements Command {

    private final Scanner scanner;

    public TagDelete(Scanner scanner, BaseController<TagDtoRequest, TagDtoResponse, Long> tagController) {
        super(tagController);
        this.scanner = scanner;
    }

    @Override
    public boolean execute() {
        System.out.println(Constants.TAG_ID);
        long id = Long.parseLong(scanner.nextLine());
        tagController.deleteById(id);
        return true;
    }
}
