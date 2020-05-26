package com.springboot.imp;

import com.springboot.dao.PoetryDao;
import com.springboot.entity.TPoetry;
import com.springboot.service.PoetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PoetryImp implements PoetryService {
    @Autowired
    private PoetryDao poetryDao;

    @Override
    public List<TPoetry> showAll(String kw) {
        return poetryDao.findBykw(kw);
    }

    @Override
    public TPoetry findById(long id) {
        return poetryDao.findById(id).get();
    }
}
