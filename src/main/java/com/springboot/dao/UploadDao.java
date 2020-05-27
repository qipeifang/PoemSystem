package com.springboot.dao;

import com.springboot.entity.TUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface UploadDao extends JpaRepository<TUpload,Long> {
    @Modifying
    @Transactional
    List<TUpload> findAll();
    @Query(value = "select * from t_upload u where name like?1 ",nativeQuery = true)
    List<TUpload> findAllByKw(String kw);
    @Query(value = "select * from t_upload u where name like?1  and (useremail=?2)",nativeQuery = true)
    List<TUpload> findAllByKwAndUseremail(String kw,String useremail);
    //未审核的上传资源
   // @Query(value = "select * from t_upload u where status=?1 ",nativeQuery = true)
    List<TUpload> findAllByStatus(Boolean status);
}
