package com.springboot.dao;

import com.springboot.entity.TDynasty;
import com.springboot.entity.TPoetryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

public interface DynastyDao extends JpaRepository<TDynasty,Long> {
    @Modifying
    @Transactional
    List<TDynasty> findAll();
}
