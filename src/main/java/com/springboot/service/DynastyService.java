package com.springboot.service;

import com.springboot.entity.TDynasty;
import com.springboot.entity.TUser;

import java.util.List;

public interface DynastyService extends java.io.Serializable {
    //返回系统中存有的诗歌类型，foreach到界面中，方便用户通过此点击进入相对应类型诗歌集
    List<TDynasty> getAllDynasty();
    void deleteById(long id);
    List<TDynasty> adminshowAll(String kw);

    void AddDynasty(TDynasty dynasty);

    public void save(TDynasty dynasty);


    //添加诗词时，判断朝代是否存在，若不存在，则添加到朝代表，并返回id
    public Long dynasty_exist(String dynastyname);
}
