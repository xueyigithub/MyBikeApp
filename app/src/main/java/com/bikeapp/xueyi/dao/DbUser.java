package com.bikeapp.xueyi.dao;

import com.bikeapp.xueyi.domain.User;
import com.google.gson.annotations.Expose;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by tao on 2016/1/3.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
public class DbUser {

    @Expose
    private String id;

    @Expose
    private User user;

    @Expose
    private Boolean hasLogin;

    @Expose
    private Boolean isChekbox;

}
