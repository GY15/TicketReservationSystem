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

}
