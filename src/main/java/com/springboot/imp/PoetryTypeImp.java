package com.springboot.imp;

import com.springboot.dao.PoetryTypeDao;
import com.springboot.entity.TPoet;
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

    @Override
    public List<TPoetryType> adminshowAll(String kw) {
        return poetryTypeDao.findByKeyword(kw);
    }

    @Override
    public void deleteById(long id) {
        poetryTypeDao.deleteById(id);
    }

    @Override
    public void AddPoetryType(TPoetryType poetryType) {
        poetryTypeDao.save(poetryType);
    }


    //添加诗词时，判断类型是否存在，若不存在，则添加到类型表
    @Override
    public Long type_exist(String type) {
        List<TPoetryType> typeList=poetryTypeDao.findByType(type);
        if (typeList.size()==0){//若不存在，则添加到类型表
            TPoetryType poetryType=new TPoetryType();
            poetryType.setType(type);
            poetryTypeDao.save(poetryType);//添加到类型表


            List<TPoetryType> tList=poetryTypeDao.findByType(type);
            return tList.get(0).getId();
        }
        List<TPoetryType> tList=poetryTypeDao.findByType(type);
        return tList.get(0).getId();
    }
}
