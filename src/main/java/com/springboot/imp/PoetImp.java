package com.springboot.imp;

import com.springboot.dao.PoetDao;
import com.springboot.entity.TPoet;
import com.springboot.entity.TUser;
import com.springboot.service.PoetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    @Override
    public void AddPoet(TPoet poet) {
        poetDao.save(poet);
    }

    @Override
    public void modifyPoet(TPoet poet) {
        poetDao.save(poet);
    }

    @Override
    public List<TPoet> findName(String name) {
        List<TPoet> poetList=UserImp.toList(poetDao.findByName(name));
        return poetList;
    }

    //Optional转换为List
    public static <T> List <T> toList(Optional<T> optional){
        return optional.map(Collections::singletonList).orElse(Collections.emptyList());
    }
}

