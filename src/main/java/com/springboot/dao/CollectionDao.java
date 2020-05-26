package com.springboot.dao;

import com.springboot.entity.TCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;

public interface CollectionDao extends JpaRepository<TCollection, Long> {
    @Modifying
    @Transactional
    //保存收藏
    TCollection save(TCollection colleciton);


}
