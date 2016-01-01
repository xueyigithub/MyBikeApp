package com.bikeapp.xueyi.service;


import com.bikeapp.xueyi.domain.FileInfo;
import com.bikeapp.xueyi.domain.PlayGuide;
import com.bikeapp.xueyi.domain.User;
import com.bikeapp.xueyi.dto.Page;
import com.bikeapp.xueyi.enums.CityEnum;

import java.io.File;
import java.util.Map;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.QueryMap;


/**
 * Created by xueyi on 2015/12/27.
 */
public interface HttpService {

    @GET("/bike/download/{download}")
    Call<File> download(@Path("download") String download);

    @Multipart
    @POST("/bike/upload")
    Call<FileInfo> upload(@Part("fileContent") File file);

    @POST("/bike/user/register")
    Call<User> register(@Body User user);

    @POST("/bike/user/login")
    Call<User> login(@Query("userName") String userName, @Query("password") String password);

    @GET("/bike/playGuide/listPlayGuide?sort=baiduPage,Asc&sort=id,Asc")
    Call<Page<PlayGuide>> listPlayGuide(@Query("page") Integer page, @Query("city") CityEnum city);

    /**
     * 注册
     */
    @POST("/bike/user/register")
    Call<User> register(@QueryMap Map<String, Object> userMap);
    /**
     * listPlayGuide
     */
    @GET("/bike/playGuide/listPlayGuide?sort=baiduPage,Asc&sort=id,Asc")
    Call<Page<PlayGuide>> listPlayGuide(@Query("page") Integer page, @QueryMap Map<String, Object> playGuideMap);
}
