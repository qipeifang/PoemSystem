package com.springboot.service;

import com.springboot.entity.TPoet;

import java.util.List;

public interface PoetService extends java.io.Serializable {
//    Page<TPoet> findAll(String kw, Pageable pageable);

    void deleteById(long id);

    List<TPoet> showAll(String kw);

    List<TPoet> displayPoet(long id);

    void AddPoet(TPoet poet);

    public void modifyPoet(TPoet poet);

    List<TPoet> findName(String name);

    //添加诗词时，判断诗人是否存在，若不存在，则添加到诗人表,并返回id
    public Long poet_exist(String poetname);
}
