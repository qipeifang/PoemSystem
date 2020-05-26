package com.springboot.service;

import com.springboot.entity.VCollAndPoem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VCollAndPoemService extends java.io.Serializable {
    Page<VCollAndPoem> findAll(String email, String kw, Pageable pageable);

    void deleteById(long id);

    List<VCollAndPoem> showAll(String email, String kw);
}
