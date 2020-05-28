package com.springboot.dao;

import com.springboot.entity.TPoet;
import com.springboot.entity.TUser;
import com.springboot.entity.VCollAndPoem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface PoetDao  extends JpaRepository<TPoet, Long> {
    @Modifying
    @Transactional
    TPoet save(TPoet poet);

    @Query(value = "select * from t_poet u where name like ?1",nativeQuery = true)
    List<TPoet> findBykw(String kw);
    List<TPoet> findById(long id);

    Optional<TPoet> findByName(String name);
}
