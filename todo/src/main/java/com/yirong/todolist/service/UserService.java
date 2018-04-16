package com.yirong.todolist.service;

import com.yirong.todolist.entity.User;

import java.util.List;


public interface UserService {
    /**
     * 用户列表
     * @return
     */
    public List<User> userList();

    /**
     * 通过用户名查找用户是否存在
     * @param name
     * @return
     */
    public boolean findUserByName(String name);

    /**
     * 添加用户
     * @param u
     * @return
     */
    public User addUser(User u);

    /**
     * 通过用户名密码查找用户
     * @param name
     * @param password
     * @return
     */
    public User findUserByNameAndPassword(String name, String password);
}
