package web.service;

import web.model.Plan;
import web.model.SeatMap;

import java.util.List;

/**
 * 管理用户登录
 */
public interface PlanService {

    /**
     * 发布计划
     *
     * @author 61990
     * @updateTime 2018/2/14
     * @param plan 计划
     * @return 是否成功
     */
    String publishPlan(Plan plan);
    /**
     * 发布计划
     *
     * @author 61990
     * @updateTime 2018/2/14
     * @param plan 计划
     * @return 是否成功
     */
    List<Plan> getPlanGeneral(int venueid);
}
