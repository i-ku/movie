package com.movie.api.controller;

import com.movie.api.model.entity.Poster;
import com.movie.api.service.PosterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "首页海报接口")
@RequestMapping("/api/poster")
public class PosterController {

    @Resource
    private PosterService posterService;

    @PostMapping("")
    @ApiOperation("添加首页海报")
    public void save(@RequestBody Poster poster) {
        posterService.save(poster);
    }

    @PutMapping("")
    @ApiOperation("更新海报")
    public void update(@RequestBody Poster poster) {
        posterService.update(poster);
    }

    @GetMapping("")
    @ApiOperation("获取所有海报")
    public List<Poster> list(String status) {
        if (status != null) {
            return posterService.findByStatus(Boolean.parseBoolean(status));
        }
        return posterService.findAll();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(("删除海报"))
    public void delete(@PathVariable String id) {
        posterService.deleteById(id);
    }

    @DeleteMapping("")
    @ApiOperation(("删除所有海报"))
    public void deleteAll() {
        posterService.deleteAll();
    }

}
