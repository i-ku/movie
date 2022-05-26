package com.movie.api.model.support;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@ApiModel(value = "api返回结果")
@AllArgsConstructor
public class ResponseResult<T> {

    private Integer code;

    private boolean success;

    private String msg;

    private T data;

    public ResponseResult() {
        this.code = 200;
        this.success = true;
        this.msg = null;
        this.data = null;
    }

    //不附带msg返回数据
    public ResponseResult(T data) {
        this.code = 200;
        this.success = true;
        this.msg = null;
        this.data = data;
    }

    //默认附带msg返回数据
    public ResponseResult(String msg, T data) {
        this.code = 200;
        this.success = true;
        this.msg = msg;
        this.data = data;
    }

    //默认的错误返回
    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.success = false;
        this.msg = msg;
        this.data = null;
    }

}
