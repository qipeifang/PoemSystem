package com.springboot.service;

import com.springboot.entity.TPoetry;

import java.util.List;

public interface PoetryService extends java.io.Serializable {
    List<TPoetry> showAll(String kw);


    public TPoetry findById(long id);
}
