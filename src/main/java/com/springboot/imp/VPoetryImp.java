package com.springboot.imp;

import com.springboot.dao.VPoetryDao;
import com.springboot.entity.VPoetry;
import com.springboot.service.VPoetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class VPoetryImp implements VPoetryService {
    @Autowired
    private VPoetryDao vPoetryDao;
    @Override
    public List<VPoetry> showAllbyKw(String kw) {
        return vPoetryDao.findAllbyKw(kw);
    }

    @Override
    public List<VPoetry> displayPoetry(long id1) {
        return vPoetryDao.findById(id1);
    }

    @Override
    public List<VPoetry> showAllByTypeId(long id1) {
        return vPoetryDao.findByTypeid(id1);
    }

    @Override
    public List<VPoetry> showAllByDynastyId(long id1) {
        return vPoetryDao.findByDynastyid(id1);
    }

    @Override
    public void deleteById(long id) {
        vPoetryDao.deleteById(id);
    }

    @Override
    public List<VPoetry> adminshowAll(String kw) {
        return vPoetryDao.findByKeyword(kw);
    }

    @Override
    public List<VPoetry> showAll() {
        return vPoetryDao.findAll();
    }
}
