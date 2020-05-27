package com.springboot.service;

import com.springboot.entity.PRecommend;

import java.util.List;

public interface RecommendService extends java.io.Serializable{
    List<PRecommend> getAll(Integer arg);
}
