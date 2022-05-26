package com.movie.api.controller;

import com.movie.api.model.entity.FilmEvaluate;
import com.movie.api.model.vo.FilmEvaluateVO;
import com.movie.api.service.FilmEvaluateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "电影评价接口")
@RequestMapping("/api/fe")
public class FilmEvaluateController {

    @Resource
    private FilmEvaluateService filmEvaluateService;

    @PostMapping("")
    @ApiOperation("评论电影")
    public void save(@RequestBody FilmEvaluate filmEvaluate) throws Exception {
        filmEvaluateService.save(filmEvaluate);
    }

    @GetMapping("")
    @ApiOperation("获取电影评论")
    public List<FilmEvaluateVO> list(@RequestParam(name = "fid") String fid) {
        if (fid != null) {
            return filmEvaluateService.findAllByFilmId(fid);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据id删除评论")
    public void remove(@PathVariable String id) {
        filmEvaluateService.deleteById(id);
    }

    @DeleteMapping("")
    @ApiOperation("删除该电影的所有评分")
    public void removeAll(@RequestParam(name = "fid") String fid) {
        filmEvaluateService.deleteAllByFilmId(fid);
    }

}
