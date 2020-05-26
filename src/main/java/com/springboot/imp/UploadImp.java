package com.springboot.imp;

import com.springboot.dao.UploadDao;
import com.springboot.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UploadImp implements UploadService {
    @Autowired
    private UploadDao uploadDao;
}
