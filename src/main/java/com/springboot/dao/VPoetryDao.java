package com.springboot.dao;

import com.springboot.entity.TPoet;
import com.springboot.entity.VCollAndPoem;
import com.springboot.entity.VPoetry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface VPoetryDao extends JpaRepository<VPoetry, Long> {
    @Modifying
    @Transactional
    @Query(value = "select * from VPoetry u where name like ?1 or poetname like ?1 or type like ?1 or dynastyname like ?1",nativeQuery = true)
    List<VPoetry> findAllbyKw(String kw);
    List<VPoetry> findById(long id);
    List<VPoetry> findByIdIn(List<Long> id);

    List<VPoetry> findByTypeid(long id1);

    List<VPoetry> findByDynastyid(long id1);

    //管理员通过关键字查询诗词信息
    @Query("select u from VPoetry u where name like ?1 or poetname like ?1 or type like ?1 or dynastyname like ?1")
    public List<VPoetry> findByKeyword(String kw);
}
