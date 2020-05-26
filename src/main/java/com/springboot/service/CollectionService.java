package com.springboot.service;

import com.springboot.entity.TCollection;

import java.util.List;

public interface CollectionService extends java.io.Serializable{
    void addCollection(TCollection colleciton);

    void deleteById(long id);

    TCollection findById(long id);

    void deletes(List<TCollection> colls);
}
