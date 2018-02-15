package web.service;

import web.model.Member;
import web.model.Plan;
import web.model.SeatMap;
import web.model.Venue;
import web.utilities.enums.MemberState;

import java.util.List;

/**
 * 管理用户登录
 */
public interface SeatService {

    /**
     * 提交座位信息
     *
     * @author 61990
     * @updateTime 2018/2/13
     * @param seatMaps 座位信息
     * @return 是否成功
     */
    String submitSeatMap(List<SeatMap> seatMaps);
    /**
     * 获取一个场馆的全部座位信息
     *
     * @author 61990
     * @updateTime 2018/2/13
     * @param venueid 场馆id
     * @return 是否成功
     */
    List<SeatMap> getSeatMap(int venueid);
    /**
     * 发布计划
     *
     * @author 61990
     * @updateTime 2018/2/14
     * @param plan 计划
     * @return 是否成功
     */
    String publishPlan(Plan plan);
}
