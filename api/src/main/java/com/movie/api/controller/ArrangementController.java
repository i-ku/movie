package com.movie.api.controller;

import com.movie.api.model.entity.Arrangement;
import com.movie.api.model.vo.ArrangementVO;
import com.movie.api.service.ArrangementService;
import com.movie.api.service.FilmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "电影排片场次接口")
@RequestMapping("/api/arrangement")
public class ArrangementController {

    @Resource
    private ArrangementService arrangementService;

    @Resource
    private FilmService filmService;

    @PostMapping("")
    @ApiOperation("新增电影场次")
    public void save(@RequestBody Arrangement arrangement) {
        arrangementService.save(arrangement);
    }

    @PutMapping("")
    @ApiOperation("修改排片信息")
    public Arrangement update(@RequestBody Arrangement arrangement) {
        return arrangementService.Update(arrangement);
    }

    @DeleteMapping("")
    @ApiOperation("根据id删除排片")
    public void delete(@RequestParam String id) {
        arrangementService.deleteById(id);
    }

    @GetMapping("")
    @ApiOperation("列出电影排片")
    public List<Arrangement> list() {
        return arrangementService.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation("查询拍片")
    public Map<String, Object> findById(@PathVariable String id) {
        HashMap<String, Object> map = new HashMap<>();
        Arrangement arrangement = arrangementService.findById(id);
        map.put("film", filmService.findById(arrangement.getFid()));
        map.put("arrangement", arrangement);
        return map;
    }

    @GetMapping("/getSeats")
    @ApiOperation("获取座位情况")
    public List<Integer> getSeats(String id) {
        return arrangementService.getSeatsHaveSelected(id);
    }

    @GetMapping("/film/{fid}")
    @ApiOperation("查询某个电影的所有拍片")
    public ArrangementVO findByFilmId(@PathVariable String fid) {
        return arrangementService.findByFilmId(fid);
    }

}
