package com.yirong.todolist.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户表Entity类
 */
@Entity
@Table(name="user")
public class User implements Serializable {

    /**
     * 用户id
     */
    @Id
    @GeneratedValue(generator = "system-uuid",strategy = GenerationType.AUTO)
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name="uid",nullable=false,length=32)
    private String uid;

    /**
     * 用户名
     */
    @Column(name="name",unique = true,nullable = false, length = 30)
    private String name;

    /**
     * 密码
     */
    @Column(name = "password",nullable = false,length = 64)
    private String password;

    /**
     * 事项列表
     */
    /*@OneToMany(mappedBy = "user")
    private List<Schedule> schedules;*/
   /* @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Schedule> scheduleList = new HashSet<Schedule>();*/

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }*/
}
