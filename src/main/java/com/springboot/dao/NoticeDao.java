package com.springboot.dao;

import com.springboot.entity.TComment;
import com.springboot.entity.TNotice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface NoticeDao extends JpaRepository<TNotice,Long> {
//    @Modifying
//    @Transactional
//    TNotice save(TNotice notice);
//    //通过邮箱查询通知的信息
//    @Query(value = "select * from TNotice u where adminemail=?1 and (content like ?2)",nativeQuery = true)
//    Page<TNotice> findByEmail(String email, String kw, Pageable pageable);
//    @Query(value = "select * from TNotice u where adminemail=?1 and (content like ?2)",nativeQuery = true)
//    List<TNotice> findByEmailAndKw(String email, String kw);

    @Modifying
    @Transactional
        //保存通知
    TNotice save(TNotice notice);
    Optional<TNotice> findByEmail(String email);

    //通过邮箱查询用户评论的信息
    @Query(value = "select * from t_notice u where email=?1 and (content like ?2)",nativeQuery = true)
    Page<TNotice> findByEmail(String email, String kw, Pageable pageable);
    @Query(value = "select * from t_notice u where email=?1 and (content like ?2)",nativeQuery = true)
    List<TNotice> findByEmailAndKw(String email, String kw);


    //管理员通过关键字查询评论信息
    @Query("select u from TNotice u where content like ?1 or email like ?1")
    public List<TNotice> findByKeyword(String kw);



}
