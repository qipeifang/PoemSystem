package com.springboot.dao;

import com.springboot.entity.TUpload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadDao extends JpaRepository<TUpload,Long> {
}
