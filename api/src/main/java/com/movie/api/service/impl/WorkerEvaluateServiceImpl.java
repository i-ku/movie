package com.movie.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.movie.api.mapper.WorkerEvaluateMapper;
import com.movie.api.model.entity.WorkerEvaluate;
import com.movie.api.service.WorkerEvaluateService;
import com.movie.api.utils.DataTimeUtil;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = "workerEvaluate")
public class WorkerEvaluateServiceImpl implements WorkerEvaluateService {

    @Resource
    private WorkerEvaluateMapper workerEvaluateMapper;

    @Override
    public void save(WorkerEvaluate workerEvaluate) {
        workerEvaluate.setCreateAt(DataTimeUtil.getNowTimeString());
        workerEvaluate.setId(UUID.randomUUID().toString());
        workerEvaluateMapper.insert(workerEvaluate);
    }

    @CacheEvict
    @Override
    public void deleteById(String id) {
        workerEvaluateMapper.deleteById(id);
    }

    @CacheEvict
    @Override
    public void deleteAllByWid(String wid) {
        workerEvaluateMapper.delete(new QueryWrapper<WorkerEvaluate>().in("wid", wid));
    }

    @Override
    public List<WorkerEvaluate> findByWorkerId(String wid) {
        return workerEvaluateMapper.selectList(new QueryWrapper<WorkerEvaluate>().in("wid", wid));
    }

}
