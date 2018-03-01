package web.service;

import web.model.OrderGeneral;
import web.model.SeatMapObj;
import web.utilities.enums.OrderState;

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
    String createOrder(String email, int venueid, int planid, String block,List<String> seat,double value,boolean isMember,OrderState orderState,int couponid);

    /**
     * 检查订单是否符合计划id和 场馆id
     *
     * @author 61990
     * @updateTime 2018/2/21
     * @return 订单的座位信息
     */
    String checkTicket(int planid, int orderid, int venueid);

    /**
     * 得到用户的订单
     *
     * @author 61990
     * @updateTime 2018/2/21
     * @param email 用户邮箱
     * @param orderState 订单状况
     * @return 订单的座位信息
     */
    List<OrderGeneral> getOrders(String email,OrderState orderState);

    /**
     * 得到用户的订单
     *
     * @author 61990
     * @updateTime 2018/2/24
     * @param email 用户邮箱
     * @param orderid 订单id
     * @return 订单的座位信息
     */
    OrderGeneral getOrder(String email,int orderid);

    /**
     * 支付‘未支付’的订单
     *
     * @author 61990
     * @updateTime 2018/2/25
     * @param email 用户邮箱
     * @param orderid 订单id
     * @return 订单的座位信息
     */
    String pay(int orderid, String email);
}
