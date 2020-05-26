package com.springboot.service;

import com.springboot.entity.TNotice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NoticeService extends java.io.Serializable {
//    Page<TNotice> findAll(String email, String kw, Pageable pageable);
//
//    void deleteById(long id);
//
//    List<TNotice> showAll(String email, String kw);
//
//    public void Write(TNotice notice);
//
    List<TNotice> usershowAll();


    Page<TNotice> findAll(String email, String kw, Pageable pageable);

    void deleteById(long id);

    List<TNotice> showAll(String email, String kw);

    List<TNotice> adminshowAll(String kw);

    void WriteNotice(TNotice notice);



}
