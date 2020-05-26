package com.springboot.imp;

import com.springboot.dao.UserDao;
import com.springboot.entity.TUser;
import com.springboot.entity.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import com.springboot.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public  class UserImp implements UserService {
    @Autowired
    private UserDao userDao;
    //保存用户
    @Override
    public void addUser(TUser user) {
        userDao.save(user);
    }

    @Override
    public void modifyUser(TUser user) {
        userDao.save(user);
    }

    //
    @Override
    public List<TUser> findEmail(String email) {
         List<TUser> userList=UserImp.toList(userDao.findByEmail(email));
         return userList;
    }
    //找到除当前id外有没有对应邮箱行
    @Override
    public List<TUser> findByEmailNotId(String email, long id) {
        return userDao.findByEmailAndIdNot(email,id);
    }

    //Optional转换为List
		public static <T> List <T> toList(Optional<T> optional){
			return optional.map(Collections::singletonList).orElse(Collections.emptyList());
		}
    /**
     * 检测登录用户是否合法
     * @param user 登录用户的账号密码
     * @return 如果用户合法返回true。否则返回false
     */
    @Override
    public TUser checkUser(UserLogin user) {
        TUser u=null;
        //去数据库中通过账号查找用户信息
        Optional<TUser> ou=userDao.findByEmail(user.getEmail());
        if (ou.isPresent()){//isPresent()方法是判断Optional中是否包含目标对象
            u=ou.get();
            //把库中的密码与登录时的密码对比是否一致
            if (u.getPassword().equals(user.getPassword())){
                return u;//数据一致为合法用户返回用户对象
            }
        }
        return null;//其他一切返回null对象
    }

    @Override
    public void saveUser(TUser u) throws Exception{
        try{
            userDao.save(u);
        }catch (Exception ex){
            throw ex;
        }

    }

    @Override
    public List<TUser> findAll() {
        return userDao.findAll();
    }

    @Override
    public Page<TUser> findAll(String kw, Pageable pageable) {
        return userDao.findByKeyword(kw, pageable);
    }



    @Override
    public TUser findById(long id) {
        return userDao.findById(id).get();
    }

    @Override
    public void delete(TUser u) {
        userDao.delete(u);
    }

    @Override
    public boolean deleteById(long id) {
        userDao.deleteById(id);
        return false;
    }

    @Override
    @Transactional//保证数据删除的完整性
    public boolean deletes(List<TUser> users) {
        for (TUser u:users){
            userDao.delete(u);
        }
        return false;
    }

    @Override
    public TUser findByEmail(String email) {
        return userDao.findByEmail(email).get();
    }

    //分页查询
    @Override
    public Page<TUser> findBookNoCriteria(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "id");
        return userDao.findAll(pageable);
    }
    //关键字分页查询
    @Override
    public Page<TUser> findBookCriteria(Integer page, Integer size, String kw) {
        Pageable pageable =  PageRequest.of(page, size, Sort.Direction.ASC, "id");
//        Page<TUser> userPage = userDao.findAll((Specification<TUser>) userDao.findByKeyword(kw, pageable),pageable);
        Page<TUser> userPage = userDao.findByKeyword(kw, pageable);
        return userPage;
    }

    @Override
    public void save(TUser user) {
        userDao.save(user);
    }

    @Override
    public void updataUser(TUser user) {
        TUser entity=userDao.getOne(user.getId());
        if (entity!=null){//用户存在，编辑用户
            entity.setUsername(user.getUsername());
            entity.setPassword(user.getPassword());
            entity.setGrander(user.getGrander());
            entity.setIsVIP(user.getIsVIP());
            entity.setGrade(user.getGrade());
        }
        else {//用户不存在，添加用户
            entity=user;
        }
        userDao.save(entity);
    }

    @Override
    public void deleteusers(List<Long> userList) {
        for (int i=0;i<userList.size();i++) {
            deleteById(userList.get(i));
        }
    }


//    @Override
//    public void saveUser(TUser user) {
//        userDao.saveOrUpdate(user);
//    }


//    @Override
//    public Page findUserPage(String queryString) {
//        if(StringUtil.isNotEmpty(queryString)) {
//            return userDao.findUserPage(queryString);
//        }else {
//            return userDao.pageAll();
//        }
//    }


}
