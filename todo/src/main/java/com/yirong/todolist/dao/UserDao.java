package com.yirong.todolist.dao;

import com.yirong.todolist.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,String> {
    User findUserByNameAndPassword(String name, String password);

    User findUserByName(String name);
}
