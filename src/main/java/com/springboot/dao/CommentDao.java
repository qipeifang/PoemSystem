package com.springboot.dao;

import com.springboot.entity.TComment;
import com.springboot.entity.TUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface CommentDao extends JpaRepository<TComment, Long> {
    @Modifying
    @Transactional
        //保存评论
    TComment save(TComment comment);
    Optional<TComment> findByEmail(String email);

    //通过邮箱查询用户评论的信息
    @Query(value = "select * from t_comment u where email=?1 and (comments like ?2 or poetryname like ?2)",nativeQuery = true)
    Page<TComment> findByEmail(String email, String kw, Pageable pageable);
    @Query(value = "select * from t_comment u where email=?1 and (comments like ?2 or poetryname like ?2)",nativeQuery = true)
    List<TComment> findByEmailAndKw(String email, String kw);


    //管理员通过关键字查询评论信息
    @Query("select u from TComment u where comments like ?1 or email like ?1 or poetryname like ?1")
    public List<TComment> findByKeyword(String kw);

    //诗词下显示本诗词评论
    //通过poetryid查询评论信息
    @Query("select u from TComment u where poetryname like ?1")
    public List<TComment> findByPoetryname(String poetryname);
}
