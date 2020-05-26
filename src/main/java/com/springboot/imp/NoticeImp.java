package com.springboot.imp;

import com.springboot.dao.NoticeDao;
import com.springboot.entity.TNotice;
import com.springboot.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class NoticeImp implements NoticeService {
    @Autowired
    private NoticeDao noticeDao;

    @Override
    public Page<TNotice> findAll(String email, String kw, Pageable pageable) {
        return noticeDao.findByEmail(email,kw, pageable);
    }

    @Override
    public void deleteById(long id) {
        noticeDao.deleteById(id);
    }

    @Override
    public List<TNotice> showAll(String email, String kw) {

        return noticeDao.findByEmailAndKw(email,kw);
    }

    @Override
    public List<TNotice> adminshowAll(String kw) {
        return null;
    }

    @Override
    public void WriteNotice(TNotice notice) {
        noticeDao.save(notice);
    }

//    @Override
//    public void Write(TNotice notice) {
//        noticeDao.save(notice);
//    }

    @Override
    public List<TNotice> usershowAll() {
        return noticeDao.findAll();
    }

}
