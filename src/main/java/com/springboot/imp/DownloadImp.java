package com.springboot.imp;

import com.springboot.dao.DownloadDao;
import com.springboot.service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DownloadImp implements DownloadService {
    @Autowired
    private DownloadDao downloadDao;
}
