package web.service;

import web.model.SeatMapObj;

import java.util.List;

/**
 * 管理用户登录
 */
public interface OrderService {
    /**
     * 下订单，生成订单
     *
     * @author 61990
     * @updateTime 2018/2/18
     * @param planid 计划id
     * @param block plan的区域
     * @param seat 选中的seat
     * @return 是否成功
     */
    String createOrder(String email, int venueid, int planid, String block,List<String> seat,double value,boolean isMember);

    /**
     * 检查订单是否符合计划id和 场馆id
     *
     * @author 61990
     * @updateTime 2018/2/21
     * @return 订单的座位信息
     */
    String checkTicket(int planid, int orderid, int venueid);
}
