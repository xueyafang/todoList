package com.yirong.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String logina(){
        return "todoList/login/login.html";
    }


    @RequestMapping("/login")
    public String login(){
        return "todoList/login/login.html";
    }

    /**
     * 注册页面
     * @return
     */
    @RequestMapping("/regist")
    public String regist(){
        return "todoList/regist/regist.html";
    }

    /**
     * 首页
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "todoList/index/index.html";
    }


}
