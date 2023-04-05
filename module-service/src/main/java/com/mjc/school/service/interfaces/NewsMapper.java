package com.mjc.school.service.interfaces;

import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper
public interface NewsMapper {

    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    NewsDtoResponse newsToNewsDtoResponse(NewsModel newsModel);

    NewsDtoRequest newsToNewsDtoRequest(NewsModel newsModel);

    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    NewsModel newsDtoRequestToNews(NewsDtoRequest newsDtoRequest);

    NewsModel newsDtoResponseToNews(NewsDtoResponse newsDtoResponse);

    List<NewsDtoResponse> listNewsToNewsDtoResponse(List<NewsModel> newsModelList);

}
