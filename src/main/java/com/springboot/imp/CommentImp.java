package com.springboot.imp;

import com.springboot.dao.CommentDao;
import com.springboot.entity.TComment;
import com.springboot.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommentImp implements CommentService {
    @Autowired
    private CommentDao commentDao;

    @Override
    public Page<TComment> findAll(String email,String kw, Pageable pageable) {
        return commentDao.findByEmail(email,kw, pageable);
    }

    @Override
    public void deleteById(long id) {
        commentDao.deleteById(id);
    }

    @Override
    public List<TComment> showAll(String email, String kw) {
        return commentDao.findByEmailAndKw(email,kw);
    }

    @Override
    public List<TComment> adminshowAll(String kw) {
        return commentDao.findByKeyword(kw);
    }

    @Override
    public void WriteComment(TComment comment) {
        commentDao.save(comment);
    }

    @Override
    public List<TComment> poetry_listcomments(String poetryname) {
        return commentDao.findByPoetryname(poetryname);
    }

//    @Override
//    public void addComment(TComment comment) {
//        commentDao.save(comment);
//    }
//
//    @Override
//    public void modifyComment(TComment comment) {
//        commentDao.save(comment);
//    }
//
//    @Override
//    public List<TComment> findEmail(String email) {
//        List<TComment> commentList=UserImp.toList(commentDao.findByEmail(email));
//        return commentList;
//    }
//
//    @Override
//    public void save(TComment c) throws Exception {
//        try{
//            commentDao.save(c);
//        }catch (Exception ex){
//            throw ex;
//        }
//    }
//
//    @Override
//    public Page<TComment> findAll(String kw, Pageable pageable) {
//        return commentDao.findByKeyword(kw, pageable);
//    }
//
//    @Override
//    public TComment findById(long id) {
//        return commentDao.findById(id).get();
//    }
//
//    @Override
//    public void deletecomment(TComment c) {
//        commentDao.delete(c);
//    }
//
//    @Override
//    public boolean deletecommentById(long id) {
//        commentDao.deleteById(id);
//        return false;
//    }
//
//    @Override
//    @Transactional//保证数据删除的完整性
//    public boolean deletecomments(List<TComment> comments) {
//        for (TComment c:comments){
//            commentDao.delete(c);
//        }
//        return false;
//    }
//
//    @Override
//    public Page<TComment> findBookNoCriteria(Integer page, Integer size) {
//        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "id");
//        return commentDao.findAll(pageable);
//    }
//
//    @Override
//    public Page<TComment> findBookCriteria(Integer page, Integer size, String kw) {
//        Pageable pageable =  PageRequest.of(page, size, Sort.Direction.ASC, "id");
////        Page<TUser> userPage = userDao.findAll((Specification<TUser>) userDao.findByKeyword(kw, pageable),pageable);
//        Page<TComment> userPage = commentDao.findByKeyword(kw, pageable);
//        return userPage;
//    }
}
