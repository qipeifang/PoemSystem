package com.springboot.dao;

import com.springboot.entity.TComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentDaoMap {
    //查询父级评论
    List<TComment> findByPid(@Param("poetryid") Long poetryid);
}
