package com.springboot.imp;

import com.springboot.dao.PoetDao;
import com.springboot.entity.TPoet;
import com.springboot.service.PoetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PoetImp implements PoetService {
    @Autowired
    private PoetDao poetDao;


    @Override
    public void deleteById(long id) {
        poetDao.deleteById(id);
    }

    @Override
    public List<TPoet> showAll(String kw) {
        return poetDao.findBykw(kw);
    }


    @Override
    public List<TPoet> displayPoet(long id) {

        return poetDao.findById(id);
    }


}

