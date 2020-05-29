package com.springboot.service;


import com.springboot.entity.VPoet;

import java.util.List;

public interface VPoetService {
    List<VPoet> showAll();

    List<VPoet> adminshowAll(String kw);
}
