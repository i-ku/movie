package com.movie.api.constant;

import java.util.List;

/**
 * 权限常量
 * SpringSecurity 要求加上 "ROLE_" 前缀
 */
public final class Roles {

    //管理员
    public final static String ROLE_ADMIN = "ROLE_ADMIN";

    //普通用户
    public final static String ROLE_USER = "ROLE_USER";

    //普通员工
    public final static String ROLE_WORKER = "ROLE_WORKER";

    //查询用户
    public final static String ROLE_FIND_USER = "ROLE_FIND_USER";

    //新增电影
    public final static String ROLE_ADD_FILM = "ROLE_ADD_FILM";

    //添加拍片
    public final static String ROLE_ADD_ARRANGEMENT = "ROLE_ADD_ARRANGEMENT";

    //删除电影
    public final static String ROLE_DELETE_FILM = "ROLE_DELETE_FILM";

    public final static String[] roles = {
            ROLE_FIND_USER,
            ROLE_ADD_FILM,
            ROLE_DELETE_FILM,
            ROLE_ADD_ARRANGEMENT,
    };

}

