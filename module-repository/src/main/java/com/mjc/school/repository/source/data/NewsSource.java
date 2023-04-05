package com.mjc.school.repository.source.data;

import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.utils.Utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.mjc.school.repository.constants.Constants.*;

public class NewsSource {

    private static final List<NewsModel> newsModelList = new ArrayList<>();
    private static NewsSource instance;
    private final List<String> newsTitle = Utils.readFromFile(NEWS_NAME_FILE);
    private final List<String> newsContent = Utils.readFromFile(CONTENT_NAME_FILE);
    private final List<String> authors = Utils.readFromFile(AUTHOR_NAME_FILE);

    private NewsSource() {
        init();
    }

    public static NewsSource getInstance() {
        if (instance == null) {
            instance = new NewsSource();
        }
        return instance;
    }

    private void init() {
        for (int i = 0; i < newsTitle.size(); i++) {
            LocalDateTime date = Utils.createdRandomDate();
            NewsModel newsModel = new NewsModel((long) (i + 1),
                    newsTitle.get(i),
                    newsContent.get(i),
                    date,
                    date,
                    Utils.createRandomNumber((long) authors.size())
            );
            newsModelList.add(newsModel);
        }
    }

    public  List<NewsModel> getNewsModelList() {
        return newsModelList;
    }
}
