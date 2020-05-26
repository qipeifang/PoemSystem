package com.springboot.imp;

import com.springboot.dao.CollectionDao;
import com.springboot.dao.VCollAndPoemDao;
import com.springboot.entity.VCollAndPoem;
import com.springboot.service.VCollAndPoemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class vCollAndPoemImp implements VCollAndPoemService {
    @Autowired
    private VCollAndPoemDao vCollAndPoemDao;
    @Override
    public Page<VCollAndPoem> findAll(String email,String kw, Pageable pageable) {
        return vCollAndPoemDao.findByEmail(email,kw, pageable);
    }

    @Override
    public void deleteById(long id) {
        vCollAndPoemDao.deleteById(id);
    }

    @Override
    public List<VCollAndPoem> showAll(String email, String kw) {
        return vCollAndPoemDao.findByEmailAndKw(email,kw);
    }

}
