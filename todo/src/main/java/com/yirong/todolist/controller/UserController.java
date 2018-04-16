package com.yirong.todolist.controller;

import com.yirong.todolist.entity.User;
import com.yirong.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserController {

    /**
     * 标准service注入
     */
    @Autowired
    private UserService userService;

    /**
     * 根据用户名密码查找是否有这个用户(登录)
     * @param u
     */
    @RequestMapping(value="/findUser",method= RequestMethod.POST)
    public boolean loginUser(@RequestBody User u, HttpSession session){
        User user =  userService.findUserByNameAndPassword(u.getName(),u.getPassword());
        //判断,如果有这个用户,则返回真,否则为假
        //用户存在
        if(user != null){
            //把用户存入到session中
            session.setAttribute("user",user);
            return true;
        }else{
            return false;
        }
    }

    /**
     * 添加用户
     * @param u
     * @return
     */
    @RequestMapping(value="/addUser",method= RequestMethod.POST)
    public boolean addUser(@RequestBody User u){
        //是否存在这个用户名,如果存在,返回false,否则,去注册
        Boolean isExistUser = userService.findUserByName(u.getName());
        //如果存在,返回true,则存在该用户,返回false
        if(isExistUser){
            return false;
        }else{
            userService.addUser(u);
            return true;
        }
    }

    /*@RequestMapping(value="/loginUser",method=RequestMethod.POST)
    public String loginUser(@RequestBody String paramAll){
        *//*User user =  userService.findUserByNameAndPassword(u.getName(),u.getPassword());
        //判断,如果有这个用户,则返回真,否则为假
        //用户存在
        if(user != null){
            return true;
        }else{
            return false;
        }*//*
        return paramAll;
    }*/
}
