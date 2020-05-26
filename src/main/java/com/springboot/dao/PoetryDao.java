package com.springboot.dao;

import com.springboot.entity.TPoet;
import com.springboot.entity.TPoetry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface PoetryDao extends JpaRepository<TPoetry,Long> {
    @Modifying
    @Transactional
    @Query(value = "select * from t_poetry u where name like ?1",nativeQuery = true)
    List<TPoetry> findBykw(String kw);
}
