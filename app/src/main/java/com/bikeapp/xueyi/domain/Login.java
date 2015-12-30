package com.bikeapp.xueyi.domain;

import com.google.gson.annotations.Expose;

import lombok.Builder;
import lombok.Data;

/**
 * Created by XUEYI on 2015/12/27.
 */
@Data
@Builder
public class Login{
   @Expose private Boolean success;
    @Expose private User user;
}
