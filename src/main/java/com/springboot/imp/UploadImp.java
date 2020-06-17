package com.springboot.imp;

import com.springboot.dao.UploadDao;
import com.springboot.entity.TUpload;
import com.springboot.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UploadImp implements UploadService {
    @Autowired
    private UploadDao uploadDao;

    @Override
    public void add(TUpload upload) {
        uploadDao.save(upload);
    }

    @Override
    public List<TUpload> findAll() {
        return uploadDao.findAll();
    }

    @Override
    public List<TUpload> findAllByKw(String kw) {
        return uploadDao.findAllByKw(kw);
    }

    @Override
    public List<TUpload> findAllByKwAndUseremail(String kw, String useremail) {
        return uploadDao.findAllByKwAndUseremail(kw,useremail);
    }

    @Override
    public List<TUpload> findAllByStatus(Boolean status) {
        return uploadDao.findAllByStatus(status);
    }

    @Override
    public void deleteById(long id) {uploadDao.deleteById(id);}

    @Override
    public List<TUpload> showAllByStatus(String kw,Boolean status) {
        return uploadDao.showAllByStatus(kw,status);
    }

    @Override
    public void update(TUpload upload) {
        uploadDao.save(upload);
    }

    @Override
    public void check(String email, boolean b, long id) {
        uploadDao.updateByIdAndEmail(email,b,id);
    }
}
