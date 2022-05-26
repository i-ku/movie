package com.movie.api.annotation;

import java.lang.annotation.*;

/**
 * 作用: 不需要GlobalResponseHandler统一拦截返回JSON的方法
 * 例如: 获取二进制图片 Response需要写入文件流 不需要返回json
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DisableBaseResponse {

}
