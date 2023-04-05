package com.mjc.school.repository.source.data;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.utils.Utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.mjc.school.repository.constants.Constants.AUTHOR_NAME_FILE;

public class AuthorSource {
    private static AuthorSource instance;
    private static final List<AuthorModel> authorModelList = new ArrayList<>();
    private final List<String> authors = Utils.readFromFile(AUTHOR_NAME_FILE);

    private AuthorSource() {
        init();
    }

    public static AuthorSource getInstance() {
        if (instance == null) {
            instance = new AuthorSource();
        }
        return instance;
    }

    public List<AuthorModel> getAuthorModelList() {
        return authorModelList;
    }

    private void init() {
        for (int i = 0; i < authors.size(); i++) {
            LocalDateTime date = Utils.createdRandomDate();
            AuthorModel authorModel = new AuthorModel(
                    (long) (i + 1),
                    authors.get(i),
                    date,
                    date);
            authorModelList.add(authorModel);
        }
    }
}
