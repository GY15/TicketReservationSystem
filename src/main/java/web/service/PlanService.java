package web.service;

import web.model.Plan;
import web.model.PlanGeneral;
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
     * 获得venueid的plan
     *
     * @author 61990
     * @updateTime 2018/2/16
     * @param venueid 场馆id
     * @return list
     */
    List<PlanGeneral> getPlanGeneral(int venueid);
}
