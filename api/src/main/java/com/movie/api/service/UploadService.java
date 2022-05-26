package com.movie.api.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    //检验文件并且保存 返回值为Upload字段的id
    String checkAndSaveUpload(MultipartFile file);

    void deleteById(String id);

}
