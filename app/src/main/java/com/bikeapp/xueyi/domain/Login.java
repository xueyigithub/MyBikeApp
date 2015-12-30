package com.bikeapp.xueyi.domain;

import com.google.gson.annotations.Expose;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by XUEYI on 2015/12/27.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
public class Login{
   @Expose private Boolean success;
    @Expose private User user;
}
