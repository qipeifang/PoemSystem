package com.springboot.service;

import com.springboot.entity.TPoetryType;

import java.util.List;

public interface PoetryTypeService extends java.io.Serializable {
    //返回系统中存有的诗歌类型，foreach到界面中，方便用户通过此点击进入相对应类型诗歌集
    List<TPoetryType> getAllPoetryType();

    List<TPoetryType> adminshowAll(String kw);

    public void deleteById(long id);

    void AddPoetryType(TPoetryType poetryType);


    //添加诗词时，判断类型是否存在，若不存在，则添加到类型表,并返回id
    public Long type_exist(String type);
}
