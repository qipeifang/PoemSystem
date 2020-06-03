package com.springboot.imp;

import com.springboot.dao.DynastyDao;
import com.springboot.entity.TDynasty;
import com.springboot.service.DynastyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DynastyImp implements DynastyService {
    @Autowired
    private DynastyDao dynastyDao;


    @Override
    public List<TDynasty> getAllDynasty() {
        return dynastyDao.findAll();
    }

    @Override
    public void deleteById(long id) {
        dynastyDao.deleteById(id);
    }

    @Override
    public List<TDynasty> adminshowAll(String kw) {
        return dynastyDao.findByKeyword(kw);
    }

    @Override
    public void AddDynasty(TDynasty dynasty) {
        dynastyDao.save(dynasty);
    }

    @Override
    public void save(TDynasty dynasty) {
        dynastyDao.save(dynasty);
    }


    //添加诗词时，判断朝代是否存在，若不存在，则添加到朝代表，并返回id
    @Override
    public Long dynasty_exist(String dynastyname) {
        List<TDynasty> dynastyList=dynastyDao.findByName(dynastyname);
        if (dynastyList.size()==0){//若不存在，则添加到朝代表
            TDynasty dynasty=new TDynasty();
            dynasty.setName(dynastyname);
            dynastyDao.save(dynasty);//添加到朝代表

            List<TDynasty> dList=dynastyDao.findByName(dynastyname);
            return dList.get(0).getId();
        }
        List<TDynasty> dList=dynastyDao.findByName(dynastyname);
        return dList.get(0).getId();
    }
}
