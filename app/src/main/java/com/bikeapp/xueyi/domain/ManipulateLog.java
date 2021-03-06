package com.bikeapp.xueyi.domain;

import com.bikeapp.xueyi.enums.StatusEnum;
import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 操作日志
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
public class ManipulateLog implements Serializable {

    private static final long serialVersionUID = 6523931250661464934L;

    @Expose
    private String id;

    /**
     * 操作人User
     */
    @Expose
    private User operator;

    /**
     * 状态StatusEnum
     */
    @Expose
    private StatusEnum status;

    /**
     * 创建时间
     */
    @Expose
    private Date createTime;

    /**
     * 方法名称
     */
    @Expose
    private String methodName;

    /**
     * 方法类型
     */
    @Expose
    private String methodType;

    /**
     * 数据
     */
    @Expose
    private String methodData;

}
