package com.yirong.todolist.serviceImpl;

import com.yirong.todolist.dao.ScheduleDao;
import com.yirong.todolist.entity.Schedule;
import com.yirong.todolist.service.ScheduleService;
import com.yirong.todolist.userEntity.ScheduleUserEntity;
import com.yirong.todolist.util.SpecificationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description :
 * @Create Date: 2018/2/1
 */
@RestController
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleDao scheduleDao;

    //String uid, Integer priority, Integer isFinished, Pageable pageInfo



    @Override
    public List<Schedule> findByPriorityEquals(Integer priority) {
        return scheduleDao.findByPriorityEquals(priority);
    }


    /**
     * 总条数
     * @param uid
     * @param priority
     * @param isFinished
     * @return
     */
    public int getTotal(String uid, Integer priority, Integer isFinished){
        List<Schedule> list=scheduleDao.findAll(SpecificationUtil.dynamic(uid, priority,isFinished));
        return list.size();
    }

    @Override
    public List<Schedule> getConditionPaging(String uid, Integer priority, Integer isFinished,Integer currentPage,Integer pageSize) {
        Page<Schedule> page=scheduleDao.findAll(SpecificationUtil.dynamic(uid, priority,isFinished),new PageRequest(currentPage-1,pageSize));
        List<Schedule> list = page.getContent();
        //找到页数
        return list;
    }

    /**
     * 改变任务完成的状态
     * @param sid
     * @param isFinished
     */
    @Override
    public boolean changeFinishStatus(String sid,Integer isFinished) {
        Schedule schedule = scheduleDao.findBySid(sid);
        schedule.setIsFinished(isFinished);
        scheduleDao.save(schedule);
        return true;
    }

    /**
     * 删除任务
     * @param sid
     * @return
     */
    @Override
    public boolean deleteSchedule(String sid) {
        scheduleDao.deleteById(sid);
        return true;
    }

    /**
     * 添加事项
     * @param scheduleName
     * @param priorityStatus
     * @param endTime
     * @return
     */
    @Override
    public boolean addSchedule(String uid,String sid,String scheduleName, Integer priorityStatus, Date endTime,Integer isFinished) {
        //对时间进行处理
        Schedule schedule = new Schedule();
        schedule.setUid(uid);
        //sid是不是空,如果是空,则为添加,否则为修改
        if(sid!=null&&sid!=""){
            schedule.setSid(sid);
        }
        schedule.setScheduleName(scheduleName);
        schedule.setPriority(priorityStatus);
        schedule.setIsFinished(isFinished);
        Date string = new Date();
        /*Timestamp timeStamp = new Timestamp(string.getTime());*/
        schedule.setEndTime(string);
        scheduleDao.save(schedule);
        return true;
    }

    /**
     * 通过sid找到事务
     * @param sid
     * @return
     */
    @Override
    public Schedule findBySid(String sid) {
        return scheduleDao.findBySid(sid);
    }


}
