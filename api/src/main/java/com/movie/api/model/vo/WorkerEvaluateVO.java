package com.movie.api.model.vo;

import com.movie.api.model.entity.User;
import com.movie.api.model.entity.WorkerEvaluate;
import lombok.Data;

import java.io.Serializable;

@Data
public class WorkerEvaluateVO implements Serializable {

    private String id;

    private WorkerEvaluate workerEvaluate;

    private User user;

}
