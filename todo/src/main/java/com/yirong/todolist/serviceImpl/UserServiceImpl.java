package com.yirong.todolist.serviceImpl;

import com.yirong.todolist.dao.UserDao;
import com.yirong.todolist.entity.User;
import com.yirong.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户表service实现类
 */
@RestController
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 用户列表
     * @return
     */
    public List<User> userList(){
        return userDao.findAll();
    }


    /**
     * 通过用户名查找用户
     * @param name
     * @return
     */
    @Override
    public boolean findUserByName(String name) {
        User u =  userDao.findUserByName(name);
        //u为null，说明没找到,返回false
        if(u == null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public User addUser(User u) {
        return userDao.save(u);
    }



    /**
     * 通过用户名密码增加用户
     */
    public User findUserByNameAndPassword(String name, String password){
        return userDao.findUserByNameAndPassword(name,password);
    }


}
