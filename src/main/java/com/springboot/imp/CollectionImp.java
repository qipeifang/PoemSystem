package com.springboot.imp;

import com.springboot.dao.CollectionDao;
import com.springboot.dao.UserDao;
import com.springboot.entity.TCollection;
import com.springboot.entity.VCollAndPoem;
import com.springboot.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CollectionImp implements CollectionService {
    @Autowired
    private CollectionDao collectionDao;

    @Override
    public void addCollection(TCollection colleciton) {
        collectionDao.save(colleciton);
    }

    @Override
    public void deleteById(long id) {
        collectionDao.deleteById(id);
    }

    @Override
    public TCollection findById(long id) {
        return collectionDao.findById(id).get();
    }
    //批量删除
    @Override
    public void deletes(List<TCollection> colls) {
        for(TCollection c:colls)
            collectionDao.delete(c);
    }
}
