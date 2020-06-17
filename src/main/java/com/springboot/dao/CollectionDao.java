package com.springboot.dao;

import com.springboot.entity.TCollection;
import com.springboot.entity.TUser;
import org.hibernate.hql.internal.ast.SqlASTFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

public interface CollectionDao  extends JpaRepository<TCollection, Long>{
    @Modifying
    @Transactional
    //保存收藏
    TCollection save(TCollection colleciton);

}
