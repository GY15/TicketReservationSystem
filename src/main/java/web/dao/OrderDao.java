package web.dao;

import web.entity.Order;
import web.entity.Reservation;
import web.utilities.enums.OrderState;

import java.util.List;

/**
 * Created by 61990 on 2017/12/19.
 */
public interface OrderDao extends BaseDao {
    /**
     * 获得新的orderid
     *
     * @author 61990
     * @updateTime 2018/2/18
     * @return planid
     */
    int createOrder(Order order);
    /**
     * 获得一个订单信息
     *
     * @author 61990
     * @updateTime 2018/2/21
     * @return order 的信息
     */
    Order getOrder(int orderid);
    /**
     * 更新一个订单信息
     *
     * @author 61990
     * @updateTime 2018/2/21
     * @return 是否成功
     */
    void updateOrder(Order order);

    /**
     * 获得一个用户的订单信息
     *
     * @author 61990
     * @updateTime 2018/2/21
     * @return order 的信息
     */
    List<Order> getOrders(String email, OrderState orderState);
    /**
     * 检查超过十五分钟的订单失效
     *
     * @author 61990
     * @updateTime 2018/3/5
     * @return null
     */
    void checkOvertime();

    /**
     * 获得可以处理的快速预定的票
     *
     * @author 61990
     * @updateTime 2018/3/5
     * @return null
     */
    List<Reservation> getReservationOrder();

    /**
     * 生成一条预购订单
     *
     * @author 61990
     * @updateTime 2018/3/5
     * @return 是否生成预购成功
     */
    boolean createReservation(Reservation reservation);
    /**
     * 得到指定用户的预购单
     *
     * @author 61990
     * @updateTime 2018/3/5
     * @param email 用户邮箱
     * @return 订单的座位信息
     */
    List<Reservation> getReservation(String email);

    /**
     * 将座位不满的预购失效
     *
     * @author 61990
     * @updateTime 2018/3/5
     * @return 是否操作成功
     */
     boolean updateReservation(Reservation reservation);
}
