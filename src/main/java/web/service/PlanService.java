package web.service;

import web.entity.Plan;
import web.model.PlanGeneral;
import web.model.SeatMapObj;
import web.model.SeatType;

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
    /**
     * 获得指定id 的计划
     *
     * @author 61990
     * @updateTime 2018/2/18
     * @param planid 计划的id
     * @return
     */
    PlanGeneral getPlan(int planid);

    /**
     * 暂时不分页，显示所有的计划
     *
     * @author 61990
     * @updateTime 2018/2/16
     * @return list
     */
    List<PlanGeneral> getPlanGeneral();

    /**
     * 计算获得某一计划某一区域的票价预估
     * 用于快速购票
     *
     * @author 61990
     * @updateTime 2018/3/5
     * @return list
     */
    double getBlockValue(int planid,String block);

}
