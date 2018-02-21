package web.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import web.model.Order;
import web.model.Plan;
import web.model.Ticket;
import web.utilities.HibernateUtil;

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
}
