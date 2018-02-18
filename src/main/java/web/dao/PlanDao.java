package web.dao;

import web.model.Plan;
import web.model.SeatMap;

import java.util.List;

/**
 * Created by 61990 on 2017/12/19.
 */
public interface PlanDao extends BaseDao {
    /**
     * 发布计划
     *
     * @author 61990
     * @updateTime 2018/2/14
     * @param plan 计划
     * @return planid
     */
    int createPlan(Plan plan);
    /**
     * 获得指定id所有plan
     *
     * @author 61990
     * @updateTime 2018/2/16
     * @param venueid 场馆id
     * @return list<plan>
     */
    List<Plan> getPlan(int venueid);
}
