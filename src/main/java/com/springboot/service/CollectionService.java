package com.springboot.service;

import com.springboot.entity.TCollection;

import java.sql.SQLException;
import java.util.List;

public interface CollectionService extends java.io.Serializable{
   

    void deleteById(long id);

    TCollection findById(long id);

    void deletes(List<TCollection> colls);

    String addColletion(TCollection coll) throws SQLException;
}
