package com.springboot.dao;

import com.springboot.entity.TDynasty;
import com.springboot.entity.TPoetryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface DynastyDao extends JpaRepository<TDynasty,Long> {
    @Modifying
    @Transactional
    List<TDynasty> findAll();

    //管理员通过关键字查询朝信息
    @Query("select u from TDynasty u where name like ?1 or start like ?1 or intro like ?1")
    public List<TDynasty> findByKeyword(String kw);
}
