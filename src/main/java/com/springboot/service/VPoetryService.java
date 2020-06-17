package com.springboot.service;


import com.springboot.entity.VPoetry;

import java.util.List;

public interface VPoetryService {
    List<VPoetry> showAll();

    List<VPoetry> showAllbyKw(String kw);

    List<VPoetry> displayPoetry(long id1);

    List<VPoetry> showAllByTypeId(long id1);

    List<VPoetry> showAllByDynastyId(long id1);

    void deleteById(long id);

    List<VPoetry> adminshowAll(String kw);
}
