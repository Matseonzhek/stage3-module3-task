package com.mjc.school.controller.operations;

import com.mjc.school.controller.implementation.commands.*;
import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.interfaces.Command;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;

import java.util.Scanner;

public enum Operations {
    GET_ALL_NEWS(1, "Get all news") {
        @Override
        public <T> Command getOperation(Scanner scanner, T controller) {
            return new NewsGetAll((BaseController<NewsDtoRequest, NewsDtoResponse, Long>) controller);
        }
    },
    GET_NEWS_BY_ID(2, "Get news by id") {
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
    GET_AUTHOR_BY_ID(7, "Get author by id") {
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
    DELETE_AUTHOR(10, "Delete author"){
        @Override
        public <T> Command getOperation(Scanner scanner, T controller) {
            return new AuthorDelete(scanner, (BaseController<AuthorDtoRequest, AuthorDtoResponse, Long>) controller);
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