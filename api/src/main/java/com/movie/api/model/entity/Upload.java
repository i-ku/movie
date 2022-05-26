package com.movie.api.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 上传的图片
 */
@Data
@TableName("t_upload")
@NoArgsConstructor
@AllArgsConstructor
public class Upload implements Serializable {

    private String id;

    private byte[] bytes;

    //文件的md5值
    private String md5;

}
