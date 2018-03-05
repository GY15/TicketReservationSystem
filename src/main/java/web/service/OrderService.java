package web.service;

import web.entity.Reservation;
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
     * 得到用户的订单
     *
     * @author 61990
     * @updateTime 2018/3/5
     * @param email 用户邮箱
     * @return 订单的座位信息
     */
    List<Reservation> getReservation(String email);

    /**
     * 支付‘未支付’的订单
     *
     * @author 61990
     * @updateTime 2018/2/25
     * @param email 用户邮箱
     * @param planid 计划id
     * @param orderid 订单id
     * @return 是否成功的信息
     */
    String payOrder(int orderid, String email);

    /**
     * 检查超过十五分钟的订单失效
     *
     * @author 61990
     * @updateTime 2018/3/5
     * @return null
     */
    void checkOvertime();

    /**
     * 定期的快速购票
     *
     * @author 61990
     * @updateTime 2018/3/5
     * @return null
     */
    void autoDistributeTicket();
    /**
     * 生成一条预购订单
     *
     * @author 61990
     * @updateTime 2018/3/5
     * @return 是否生成预购成功
     */
     boolean createReservation(Reservation reservation);
    /**
     * 申请退款
     *
     * @author 61990
     * @updateTime 2018/3/5
     * @return 退款金额
     */
    boolean refundOrder(int orderid);
    /**
     * 获得退款的利率
     *
     * @author 61990
     * @updateTime 2018/3/5
     * @return 订单退款利率
     */
    double getPoundageRate(int orderid);
}
