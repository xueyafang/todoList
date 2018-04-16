package com.yirong.todolist.dao;

import com.yirong.todolist.entity.Schedule;
import com.yirong.todolist.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description :
 * @Create Date: 2018/2/1
 */
@Repository
public interface ScheduleDao extends JpaRepository<Schedule,String>,JpaSpecificationExecutor<Schedule> {
    public List<Schedule> findByPriorityEquals(Integer priority);
    public List<Schedule> findByUidEquals(String uid);
    public Schedule findBySid(String sid);


    /*public List<Schedule> getConditionPaging(String uid, Integer priority, Integer isFinished, Pageable pageInfo);*/
}
