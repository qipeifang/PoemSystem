package com.springboot.dao;

import com.springboot.entity.PRecommend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PRecommendDao extends JpaRepository<PRecommend,Long> {
    @Query(value = " call recommend(:arg)", nativeQuery = true)
    List<PRecommend> recommend(@Param("arg")Integer arg);
}
