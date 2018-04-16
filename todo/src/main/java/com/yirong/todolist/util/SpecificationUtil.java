package com.yirong.todolist.util;

import com.yirong.todolist.entity.Schedule;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description :
 * @Create Date: 2018/2/2
 */
public class SpecificationUtil {
    public static Specification<Schedule> dynamic(
            String uid, Integer priority, Integer isFinished){
        return new Specification<Schedule>() {
            @Override
            public Predicate toPredicate(Root<Schedule> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                //uid
                if(uid!=null&&!uid.equals("")) {
                    Predicate p=cb.equal(root.get("uid"),uid);
                    predicates.add(p);
                }
                //紧急程度
                if(priority!=null&&!priority.equals("")) {
                    predicates.add(cb.equal(root.get("priority"),priority));
                }
                //是否完成
                if(isFinished!=null&&!isFinished.equals("")) {
                    predicates.add(cb.equal(root.get("isFinished"),isFinished));
                }
                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        };
    }
}
