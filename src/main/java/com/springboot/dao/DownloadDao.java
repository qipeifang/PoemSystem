package com.springboot.dao;

import com.springboot.entity.TDownload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DownloadDao extends JpaRepository<TDownload,Long> {
}
