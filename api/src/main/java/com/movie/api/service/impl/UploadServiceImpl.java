package com.movie.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.movie.api.mapper.UploadMapper;
import com.movie.api.model.entity.Upload;
import com.movie.api.service.UploadService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class UploadServiceImpl implements UploadService {

    @Resource
    private UploadMapper uploadMapper;

    @Override
    public String checkAndSaveUpload(MultipartFile file) {
        try {
            InputStream is = file.getInputStream();
            byte[] data = new byte[(int) file.getSize()];
            int i = is.read(data);
            System.out.println("上传的文件大小=" + i);
            String md5 = DigestUtils.md5DigestAsHex(data);

            //判断是否有相同md5值的文件
            Upload one = uploadMapper.selectOne(new QueryWrapper<Upload>().eq("md5", md5));

            //如果存在则直接返回已存在的文件id
            if (one != null) return one.getId();

            //不存在则存入数据库
            String id = UUID.randomUUID().toString();
            uploadMapper.insert(new Upload(id, data, md5));
            return id;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteById(String id) {
        uploadMapper.deleteById(id);
    }

}
