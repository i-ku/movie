package com.movie.api.controller;

import com.movie.api.annotation.DisableBaseResponse;
import com.movie.api.mapper.UploadMapper;
import com.movie.api.model.entity.Upload;
import com.movie.api.service.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 上传图片存放为二进制到mysql
 */
@RestController
@Api(tags = "上传接口")
@RequestMapping("/api/upload")
public class UploadController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private UploadService uploadService;

    @Resource
    private UploadMapper uploadMapper;

    @PostMapping("")
    @ApiOperation(value = "上传图片")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_WORKER')")
    @DisableBaseResponse
    public String upload(MultipartFile file) throws Exception {
        if (file == null) throw new Exception("请求参数缺失");
        if (file.isEmpty()) {
            throw new Exception("上传失败，请选择文件");
        }
        return "http://localhost:" + serverPort + "/api/upload?id=" + uploadService.checkAndSaveUpload(file);
    }

    @DeleteMapping("")
    @ApiOperation(value = "删除图片")
    public void delete(@RequestParam("id") String id) {
        uploadService.deleteById(id);
    }

    @GetMapping("")
    @ApiOperation(value = "获取图片")
    @PermitAll
    @DisableBaseResponse
    public void get(@RequestParam("id") String id, HttpServletResponse response) throws Exception {
        if ("".equals(id)) {
            return;
        }
        Upload upload = uploadMapper.selectById(id);
        if (upload == null) {
            throw new Exception("图片不存在");
        }
        byte[] data = upload.getBytes();
        response.setContentType("image/jpeg");
        response.setCharacterEncoding("UTF-8");
        OutputStream outputStream = response.getOutputStream();
        InputStream in = new ByteArrayInputStream(data);
        int len;
        byte[] buf = new byte[1024];
        while ((len = in.read(buf, 0, 1024)) != -1) {
            outputStream.write(buf, 0, len);
        }
        outputStream.close();
    }

}
