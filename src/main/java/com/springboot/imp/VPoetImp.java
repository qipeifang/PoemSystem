package com.springboot.imp;

import com.springboot.dao.VPoetDao;
import com.springboot.dao.VPoetryDao;
import com.springboot.entity.VPoet;
import com.springboot.entity.VPoetry;
import com.springboot.service.VPoetService;
import com.springboot.service.VPoetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class VPoetImp implements VPoetService {
    @Autowired
    private VPoetDao vPoetDao;

    @Override
    public List<VPoet> adminshowAll(String kw) {
        return vPoetDao.findByKeyword(kw);
    }

    @Override
    public List<VPoet> showAll() {
        return vPoetDao.findAll();
    }
}
