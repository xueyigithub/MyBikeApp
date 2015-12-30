package com.bikeapp.xueyi.config;

/**
 * Created by XUEYI on 2015/11/24.
 */
public class Consts {

    /**
     * 数据库名称
     */
    public static final String DAtABASE_NAME = "AndroidBike";

    /**
     * 数据库集合名称
     */
    public static final String DOCUMENT_NAME = "default";

    /**
     * 服务端URL
     */
    public static final String BASE_URL = "http://10.7.90.201:8080";
    public static final String URL_IMAGE = BASE_URL + "download/";
    public static final String URL_LOGIN=BASE_URL+"user/login";

    public static final String URL_LIST_CampusNews = BASE_URL + "campusNews/listCampusNews";
    public static final String URL_LIST_LifeGuide = BASE_URL + "lifeGuide/listLifeGuide";
    public static final String URL_LIST_PlayGuide = BASE_URL + "playGuide/listPlayGuide";
    public static final String URL_LIST_StudyGuide = BASE_URL + "studyGuide/listStudyGuide";

    public static final String URL_FIND_LifeGuide = BASE_URL + "lifeGuide/findLifeGuide";
    public static final String URL_FIND_PlayGuide = BASE_URL + "playGuide/findPlayGuide";
    public static final String URL_FIND_StudyGuide = BASE_URL + "studyGuide/findStudyGuide";
    public static final String URL_FIND_CampusNews = BASE_URL + "campusNews/findCampusNews";

    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;
    public static final int PAGE_FIVE = 4;


}
