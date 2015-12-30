package com.bikeapp.xueyi.service;

import com.bikeapp.xueyi.config.Consts;
import com.bikeapp.xueyi.util.DateDeserializer;
import com.bikeapp.xueyi.util.DateSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;
import java.util.Date;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by xueyi on 2015/12/28.
 */
public class CallService {

    public static final HttpService service;

    static {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .registerTypeAdapter(Date.class, new DateSerializer()).setDateFormat(DateFormat.LONG)
                .registerTypeAdapter(Date.class, new DateDeserializer()).setDateFormat(DateFormat.LONG)
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Consts.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        service = retrofit.create(HttpService.class);
    }
}
