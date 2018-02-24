package web.dao.daoImpl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import web.dao.OrderDao;
import web.dao.TicketDao;
import web.model.Order;
import web.model.Ticket;
import web.utilities.HibernateUtil;
import web.utilities.enums.OrderState;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Repository
public class OrderDaoImpl extends BaseDaoImpl implements OrderDao {
    /**
     * 获得新的orderid
     *
     * @return planid
     * @author 61990
     * @updateTime 2018/2/18
     */
    public int createOrder(Order order) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        String sql = "select max(o.orderid) from orders o";
        int id = (Integer) session.createNativeQuery(sql).uniqueResult() + 1;
        order.setOrderid(id);
        super.save(order);
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * 获得一个订单信息
     *
     * @return planid
     * @author 61990
     * @updateTime 2018/2/21
     */
    public Order getOrder(int orderid) {
        return (Order) super.load(Order.class, orderid);
    }
    /**
     * 更新一个订单信息
     *
     * @author 61990
     * @updateTime 2018/2/21
     * @return 是否成功
     */
    public void updateOrder(Order order){
        super.update(order);
    }
    /**
     * 获得一个用户的订单信息
     *
     * @author 61990
     * @updateTime 2018/2/21
     * @return order 的信息
     */
    public List<Order> getOrders(String email, OrderState orderState){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        String sql;
        if(orderState.equals(OrderState.ALL)){
            sql = "SELECT * from orders where email = '"+email+"'";
        }else{
            sql = "SELECT * from orders where email = '"+email+"' and state = '"+orderState.getRepre()+"'";
        }
        Query query = session.createSQLQuery(sql).addEntity(Order.class);
        List<Order> orders= query.list();
        transaction.commit();
        session.close();
        return orders;
    }
}