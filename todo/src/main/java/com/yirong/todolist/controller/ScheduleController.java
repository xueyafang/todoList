package com.yirong.todolist.controller;

import com.yirong.todolist.entity.Schedule;
import com.yirong.todolist.entity.User;
import com.yirong.todolist.service.ScheduleService;
import com.yirong.todolist.userEntity.ScheduleUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @Description :
 * @Create Date: 2018/2/1
 */
@RestController
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @RequestMapping(value="getTotal",method=RequestMethod.POST)
    public int getTotal(@RequestBody ScheduleUserEntity scheduleUserEntity,HttpSession session){
        User u = (User)session.getAttribute("user");
        return scheduleService.getTotal(u.getUid(),scheduleUserEntity.getPriorityStatus(),scheduleUserEntity.getIsFinished());
    }


    /**
     * 1.根据用户名查找到相应的事项
     * 2.重要性
     * 3.完成状态
     * 4.页数
     * 5.时间在
     * @return
     */
    @RequestMapping(value="/paging",method= RequestMethod.POST)
    public List<Schedule> paging(@RequestBody ScheduleUserEntity scheduleUserEntity,HttpSession session){
        User u = (User)session.getAttribute("user");
        List<Schedule> list=scheduleService.getConditionPaging(u.getUid(),scheduleUserEntity.getPriorityStatus(),scheduleUserEntity.getIsFinished(),scheduleUserEntity.getCurrentPage(),scheduleUserEntity.getPageSize());
        return list;


    }

    /**
     * 改变完成状态
     * @param scheduleUserEntity
     * @return
     */
    @RequestMapping(value="/changeFinishStatus",method= RequestMethod.POST)
    public boolean changeFinishStatus(@RequestBody ScheduleUserEntity scheduleUserEntity){
        return scheduleService.changeFinishStatus(scheduleUserEntity.getSid(),scheduleUserEntity.getIsFinished());
    }

    /**
     * 删除事项
     * @param scheduleUserEntity
     * @return
     */
    @RequestMapping(value="/deleteSchedule",method= RequestMethod.POST)
    public boolean deleteSchedule(@RequestBody ScheduleUserEntity scheduleUserEntity){
        return scheduleService.deleteSchedule(scheduleUserEntity.getSid());
    }

    /**
     * 添加或修改事项
     * @param scheduleUserEntity
     * @return
     */
    @RequestMapping(value="/addSchedule",method= RequestMethod.POST)
    public boolean addSchedule(@RequestBody ScheduleUserEntity scheduleUserEntity,HttpSession session) throws ParseException {
        User u = (User)session.getAttribute("user");
        return scheduleService.addSchedule(u.getUid(),scheduleUserEntity.getSid(),scheduleUserEntity.getScheduleName(),scheduleUserEntity.getPriorityStatus(),scheduleUserEntity.getEndTime(),scheduleUserEntity.getIsFinished());
    }

    /**
     * 找到事务
     * @param scheduleUserEntity
     * @return
     */
    @RequestMapping(value="/findBySid",method= RequestMethod.POST)
    public Schedule findBySid(@RequestBody ScheduleUserEntity scheduleUserEntity){
        return scheduleService.findBySid(scheduleUserEntity.getSid());
    }
}