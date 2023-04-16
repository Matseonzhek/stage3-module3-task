package com.mjc.school.controller.operations;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.implementation.commands.*;
import com.mjc.school.controller.interfaces.Command;
import com.mjc.school.service.dto.*;

import java.util.Scanner;

public enum Operations {
    GET_ALL_NEWS(1, "Get all news") {
        @Override
        public <T> Command getOperation(Scanner scanner, T controller) {
            return new NewsGetAll((BaseController<NewsDtoRequest, NewsDtoResponse, Long>) controller);
        }
    },
    GET_NEWS_BY_ID(2, "Get news by ID") {
        @Override
        public <T> Command getOperation(Scanner scanner, T controller) {
            return new NewsGetById(scanner, (BaseController<NewsDtoRequest, NewsDtoResponse, Long>) controller);
        }
    },
    CREAT_NEWS(3, "Create news") {
        @Override
        public <T> Command getOperation(Scanner scanner, T controller) {
            return new NewsCreate(scanner, (BaseController<NewsDtoRequest, NewsDtoResponse, Long>) controller);
        }
    },
    UPDATE_NEWS(4, "Update news") {
        @Override
        public <T> Command getOperation(Scanner scanner, T controller) {
            return new NewsUpdate(scanner, (BaseController<NewsDtoRequest, NewsDtoResponse, Long>) controller);
        }
    },
    DELETE_NEWS(5, "Delete news") {
        @Override
        public <T> Command getOperation(Scanner scanner, T controller) {
            return new NewsDelete(scanner, (BaseController<NewsDtoRequest, NewsDtoResponse, Long>) controller);
        }
    },
    GET_ALL_AUTHORS(6, "Get all authors") {
        @Override
        public <T> Command getOperation(Scanner scanner, T controller) {
            return new AuthorGetAll((BaseController<AuthorDtoRequest, AuthorDtoResponse, Long>) controller);
        }
    },
    GET_AUTHOR_BY_ID(7, "Get author by ID") {
        @Override
        public <T> Command getOperation(Scanner scanner, T controller) {
            return new AuthorGetById(scanner, (BaseController<AuthorDtoRequest, AuthorDtoResponse, Long>) controller);
        }
    },
    CREATE_AUTHOR(8, "Create author") {
        @Override
        public <T> Command getOperation(Scanner scanner, T controller) {
            return new AuthorCreate(scanner, (BaseController<AuthorDtoRequest, AuthorDtoResponse, Long>) controller);
        }
    },
    UPDATE_AUTHOR(9, "Update author") {
        @Override
        public <T> Command getOperation(Scanner scanner, T controller) {
            return new AuthorUpdate(scanner, (BaseController<AuthorDtoRequest, AuthorDtoResponse, Long>) controller);
        }
    },
    DELETE_AUTHOR(10, "Delete author") {
        @Override
        public <T> Command getOperation(Scanner scanner, T controller) {
            return new AuthorDelete(scanner, (BaseController<AuthorDtoRequest, AuthorDtoResponse, Long>) controller);
        }
    },
    GET_ALL_TAG(11, "Get all tags") {
        @Override
        public <T> Command getOperation(Scanner scanner, T controller) {
            return new TagGetAll((BaseController<TagDtoRequest, TagDtoResponse, Long>) controller);
        }
    },
    GET_TAG_BY_ID(12, "Get tag by ID") {
        @Override
        public <T> Command getOperation(Scanner scanner, T controller) {
            return new TagGetById(scanner, (BaseController<TagDtoRequest, TagDtoResponse, Long>) controller);
        }
    },
    CREATE_TAG(13, "Create tag") {
        @Override
        public <T> Command getOperation(Scanner scanner, T controller) {
            return new TagCreate(scanner, (BaseController<TagDtoRequest, TagDtoResponse, Long>) controller);
        }
    },
    UPDATE_TAG(14, "Update tag") {
        @Override
        public <T> Command getOperation(Scanner scanner, T controller) {
            return new TagUpdate(scanner, (BaseController<TagDtoRequest, TagDtoResponse, Long>) controller);
        }
    },
    DELETE_TAG(15, "Delete tag") {
        @Override
        public <T> Command getOperation(Scanner scanner, T controller) {
            return new TagDelete(scanner, (BaseController<TagDtoRequest, TagDtoResponse, Long>) controller);
        }
    },
    EXIT_COMMAND(0, "Exit") {
        @Override
        public <T> Command getOperation(Scanner scanner, T controller) {
            return new ExitCommand();
        }
    };

    private final Integer operationNumber;
    private final String operationName;

    Operations(Integer operationNumber, String operationName) {
        this.operationNumber = operationNumber;
        this.operationName = operationName;
    }

    public abstract <T> Command getOperation(Scanner scanner, T controller);

    public Integer getOperationNumber() {
        return operationNumber;
    }

    public String getOperationName() {
        return operationName;
    }
}