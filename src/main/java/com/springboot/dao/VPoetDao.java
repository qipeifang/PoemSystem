package com.springboot.dao;

import com.springboot.entity.TPoet;
import com.springboot.entity.VCollAndPoem;
import com.springboot.entity.VPoet;
import com.springboot.entity.VPoetry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface VPoetDao extends JpaRepository<VPoet, Long> {
    @Modifying
    @Transactional
//    @Query(value = "select * from VPoet u where name like ?1 or poetname like ?1 or type like ?1 or dynastyname like ?1",nativeQuery = true)
//    List<VPoet> findAllbyKw(String kw);
    List<VPoet> findById(long id);

    //管理员通过关键字查询诗词信息
    @Query(value = "select * from VPoet u where name like ?1 or name_zi like ?1 or name_hao like ?1 or masterwork like ?1 or dynastyname like ?1",nativeQuery = true)
    public List<VPoet> findByKeyword(String kw);
}
