package com.springboot.service;

import com.springboot.entity.TUser;
import com.springboot.entity.UserLogin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService extends java.io.Serializable{
    void addUser(TUser user);
    void modifyUser(TUser user);
    List<TUser> findEmail(String email);
    List<TUser> findByEmailNotId(String email,long id);
    TUser checkUser(UserLogin user);
    //管理员对用户的操作
    public void saveUser(TUser u)throws Exception;
    public List<TUser> findAll();
    public Page<TUser> findAll(String kw, Pageable pageable);
    public TUser findById(long id);
    public void delete(TUser u);//删除一条信息
    public boolean deleteById(long id);
    public boolean deletes(List<TUser> users);//批量删除

    TUser findByEmail(String email);


    //分页查询
    Page<TUser> findBookNoCriteria(Integer page, Integer size);
    //关键字分页查询
    Page<TUser> findBookCriteria(Integer page, Integer size,String kw);

    void save(TUser user);

//    Page findUserPage(String queryString);

//    @Transactional
//    public void saveUser(TUser user);

    //批量删除
//     Result deleteUserByCheck(String ids);
    public void updataUser(TUser u);
    public void deleteusers(List<Long> userList);
}


