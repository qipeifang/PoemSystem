package com.springboot.imp;

import com.springboot.dao.PRecommendDao;
import com.springboot.entity.PRecommend;
import com.springboot.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RecommendImp implements RecommendService {
    @Autowired
    PRecommendDao pRecommendDao;
    @Override
    public List<PRecommend> getAll(Integer arg) {
        return pRecommendDao.recommend(arg);
    }
}
