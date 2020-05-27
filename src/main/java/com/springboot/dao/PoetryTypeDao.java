package com.springboot.dao;

import com.springboot.entity.TPoetryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface PoetryTypeDao extends JpaRepository<TPoetryType,Long> {
    @Modifying
    @Transactional
    List<TPoetryType> findAll();

    //管理员通过关键字查询评论信息
    @Query("select u from TPoetryType u where type like ?1")
    public List<TPoetryType> findByKeyword(String kw);
}
