package com.yirong.todolist.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 事项表Entity类
 */
@Entity
@Table(name="schedule")
public class Schedule implements Serializable{

    /**
     * 事项id
     */
    @Id
    @GeneratedValue(generator = "system-uuid",strategy = GenerationType.AUTO)
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name="sid",nullable=false,length=32)
    private String sid;

    /**
     * 事项名称
     */
    @Column(name="scheduleName",nullable = false, length = 255)
    private String scheduleName;

    /**
     * 结束时间
     */
    @Column(name = "endTime",nullable = false)
    private Date endTime;

    /**
     * 完成时间
     */
    @Column(name="finishTime",nullable = true)
    private Date finishTime;

    /**
     * 优先级(1代表重要且紧急,2代表重要但不紧急,3代表不重要但紧急,4代表不重要也不紧急)
     */
    @Column(name="priority",nullable = false)
    private Integer priority;

    /**
     * 是否完成(0表示未完成,1表示完成)
     */
    @Column(name="isFinished",nullable = false)
    private Integer isFinished;

    /**
     *所属用户(optional为空表示user不能为空)
     */
//    @ManyToOne
//    //@JoinColumn(name="uid")//设置在schedule表中的关联字段(外键)
//    private User user;

    /*@ManyToOne
    @JoinColumn(name="uid")//设置在schedule表中的关联字段(外键)*/
    @Column(name="uid",nullable = false)
    private String uid;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(Integer isFinished) {
        this.isFinished = isFinished;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    /* public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }*/

    @Override
    public String toString() {
        return "Schedule{" +
                "sid='" + sid + '\'' +
                ", scheduleName='" + scheduleName + '\'' +
                ", endTime=" + endTime +
                ", finishTime=" + finishTime +
                ", priority=" + priority +
                ", isFinished=" + isFinished +
                ", uid='" + uid + '\'' +
                '}';
    }
}
