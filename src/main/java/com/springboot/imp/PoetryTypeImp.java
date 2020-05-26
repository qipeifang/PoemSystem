package com.springboot.imp;

import com.springboot.dao.PoetryTypeDao;
import com.springboot.entity.TPoetryType;
import com.springboot.service.PoetryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PoetryTypeImp implements PoetryTypeService {
    @Autowired
    private PoetryTypeDao poetryTypeDao;

    @Override
    public List<TPoetryType> getAllPoetryType() {
        return poetryTypeDao.findAll();
    }

}
