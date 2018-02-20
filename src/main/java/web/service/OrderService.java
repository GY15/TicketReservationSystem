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
}
