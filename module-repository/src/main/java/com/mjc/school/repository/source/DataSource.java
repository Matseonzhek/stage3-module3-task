package com.mjc.school.repository.source;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.source.data.AuthorSource;
import com.mjc.school.repository.source.data.NewsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSource {
    private static DataSource dataSource;
    private final List<NewsModel> newsModelList;
    private final List<AuthorModel> authorModelList;

    @Autowired
    public DataSource() {
        newsModelList = NewsSource.getInstance().getNewsModelList();
        authorModelList = AuthorSource.getInstance().getAuthorModelList();
    }

    public static DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new DataSource();
        }
        return dataSource;
    }

    public List<AuthorModel> getAuthorModelList() {
        return authorModelList;
    }

    public List<NewsModel> getNewsModelList() {
        return newsModelList;
    }
}
