package com.springboot.dao;

import com.springboot.entity.VCollAndPoem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface VCollAndPoemDao extends JpaRepository<VCollAndPoem, Long> {
    @Modifying
    @Transactional
    VCollAndPoem save(VCollAndPoem vCollAndPoem);
    //通过邮箱查询用户收藏诗歌的信息
     @Query(value = "select * from VCollAndPoem u where email=?1 and (poetryname like ?2 or poetname like ?2 or type like ?2)",nativeQuery = true)
    Page<VCollAndPoem> findByEmail(String email, String kw, Pageable pageable);
    @Query(value = "select * from VCollAndPoem u where email=?1 and (poetryname like ?2 or poetname like ?2 or type like ?2)",nativeQuery = true)
    List<VCollAndPoem> findByEmailAndKw(String email, String kw);
}
