package com.yirong.todolist.service;

import com.yirong.todolist.entity.Schedule;
import com.yirong.todolist.userEntity.ScheduleUserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

/**
 * @Description :
 * @Create Date: 2018/2/1
 */
public interface ScheduleService {
    public List<Schedule> findByPriorityEquals(Integer priority);

    /**
     * 获取总条数
     * @param uid
     * @param priority
     * @param isFinished
     * @return
     */
    public int getTotal(String uid, Integer priority, Integer isFinished);

    /**
     * 1.根据用户名查找到相应的事项
     * 2.重要性
     * 3.完成状态
     * 4.页数
     * 5.时间在
     * @return
     */
    public List<Schedule> getConditionPaging(String uid, Integer priority, Integer isFinished,Integer currentPage,Integer pageSize);

    /**
     * 改变任务完成的状态
     * @param sid
     * @param isFinished
     */
    public boolean changeFinishStatus(String sid,Integer isFinished);


    /**
     * 删除任务
     * @param sid
     * @return
     */
    public boolean deleteSchedule(String sid);

    /**
     * 添加事项
     * @param scheduleName
     * @param priorityStatus
     * @param endTime
     * @return
     */
    public boolean addSchedule(String uid,String sid,String scheduleName, Integer priorityStatus, Date endTime,Integer isFinished);


    /**
     * 通过sid找到事务
     * @param sid
     * @return
     */
    public Schedule findBySid(String sid);
}
