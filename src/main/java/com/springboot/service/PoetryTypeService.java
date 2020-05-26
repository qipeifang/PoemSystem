package com.springboot.service;

import com.springboot.entity.TPoetryType;

import java.util.List;

public interface PoetryTypeService extends java.io.Serializable {
    //返回系统中存有的诗歌类型，foreach到界面中，方便用户通过此点击进入相对应类型诗歌集
    List<TPoetryType> getAllPoetryType();
}
