package com.springboot.service;

import com.springboot.entity.TDynasty;
import com.springboot.entity.TUpload;

import java.util.List;

public interface UploadService extends java.io.Serializable {
    void  add(TUpload upload);

    List<TUpload> findAll();
    List<TUpload> findAllByKw(String kw);
    List<TUpload> findAllByKwAndUseremail(String kw, String useremail);
    List<TUpload> findAllByStatus(Boolean status);
    void deleteById(long id);
    List<TUpload> showAllByStatus(String kw, Boolean status);
    void update(TUpload upload);
    void check(String email, boolean b, long id1);
}
