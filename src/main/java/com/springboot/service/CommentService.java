package com.springboot.service;

import com.springboot.entity.TComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CommentService extends java.io.Serializable {


    Page<TComment> findAll(String email, String kw, Pageable pageable);

    void deleteById(long id);

    List<TComment> showAll(String email, String kw);

    List<TComment> adminshowAll(String kw);

    void WriteComment(TComment comment);

    List<TComment> poetry_listcomments(String poetryname);
}
