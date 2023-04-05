package com.mjc.school.service.constants;

public class Constants {
    public static final String NEWS_NOT_EXIST = "News do not exist";
    public static final String AUTHOR_NOT_EXIST = "Author do not exist";

    public static final String NEWS_ID = "News id";
    public static final String AUTHOR_ID = "Author id";
    public static final Integer NEWS_TITLE_MIN = 5;
    public static final Integer NEWS_TITLE_MAX = 30;
    public static final Integer NEWS_CONTENT_MIN = 5;
    public static final Integer NEWS_CONTENT_MAX = 255;
    public static final Integer AUTHOR_NAME_MIN = 3;
    public static final Integer AUTHOR_NAME_MAX = 15;

    public static final String ERROR_VALUE_STRING = "Text length does not meet requirements: negative, null, less or more than necessary.";
    public static final String ERROR_VALUE_NUMBER = "Id does not meet requirements: negative, null, less or more than necessary.";

    private Constants() {
    }
}
