package com.springboot.service;

import com.springboot.entity.TPoet;

import java.util.List;

public interface PoetService extends java.io.Serializable {
//    Page<TPoet> findAll(String kw, Pageable pageable);

    void deleteById(long id);

    List<TPoet> showAll(String kw);

    List<TPoet> displayPoet(long id);
}
