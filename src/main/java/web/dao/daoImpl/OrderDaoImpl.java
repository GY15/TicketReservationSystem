package web.dao.daoImpl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import web.dao.OrderDao;
import web.dao.TicketDao;
import web.model.Order;
import web.model.Ticket;
import web.utilities.HibernateUtil;

import java.util.List;


@Repository
public class OrderDaoImpl extends BaseDaoImpl implements OrderDao {
    /**
     * 获得新的orderid
     *
     * @author 61990
     * @updateTime 2018/2/18
     * @return planid
     */
    public int createOrder(Order order){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        String sql = "select max(o.orderid) from orders o";
        int id = (Integer)session.createNativeQuery(sql).uniqueResult()+1;
        order.setOrderid(id);
        super.save(order);
        transaction.commit();
        return id;
    }
}
