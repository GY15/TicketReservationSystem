package web.dao.daoImpl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import web.dao.OrderDao;
import web.entity.Order;
import web.entity.Reservation;
import web.utilities.HibernateUtil;
import web.utilities.enums.OrderState;
import web.utilities.enums.ReservationState;

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
        transaction.commit();
        session.close();
        super.save(order);
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
    /**
     * 检查超过十五分钟的订单失效
     *
     * @author 61990
     * @updateTime 2018/3/5
     * @return null
     */
    public void checkOvertime(){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        String sql = "SELECT * from orders\n" +
                "where orderid in \n" +
                "(SELECT orderid from\n" +
                "(SELECT orderid,TIMEDIFF(NOW(),create_time) as time_diff \n" +
                "from orders \n" +
                "WHERE state = '未支付' ) as t\n" +
                "WHERE time_diff > 1500)";
        Query query = session.createSQLQuery(sql).addEntity(Order.class);
        List<Order> orders= query.list();
        transaction.commit();
        session.close();
        for (Order order : orders){
            order.setState(OrderState.INVALID.getRepre());
            super.update(order);
        }
    }

    /**
     * 获得可以处理的快速预定的票
     *
     * @author 61990
     * @updateTime 2018/3/5
     * @return null
     */
    public List<Reservation> getReservationOrder(){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        String sql = "SELECT * from reservation WHERE state = '" + ReservationState.RESERVATION.getRepre()+"'";
        Query query = session.createSQLQuery(sql).addEntity(Reservation.class);
        List<Reservation> reservations = query.list();
        transaction.commit();
        session.close();
        return reservations;
    }

    /**
     * 生成一条预购订单
     *
     * @author 61990
     * @updateTime 2018/3/5
     * @return 是否创建成功
     */
    public boolean createReservation(Reservation reservation){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        String sql = "select max(r.orderid) from reservation r";
        int id = (Integer) session.createNativeQuery(sql).uniqueResult() + 1;
        reservation.setOrderid(id);
        transaction.commit();
        session.close();
        super.save(reservation);
        return true;
    }

    /**
     * 得到用户的预购单
     *
     * @author 61990
     * @updateTime 2018/3/5
     * @param email 用户邮箱
     * @return 订单的座位信息
     */
    public List<Reservation> getReservation(String email){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        String sql = "SELECT * from reservation where email = '"+email+"'";
        Query query = session.createSQLQuery(sql).addEntity(Reservation.class);
        List<Reservation> orders= query.list();
        transaction.commit();
        session.close();
        return orders;
    }

    /**
     * 将座位不满的预购失效
     *
     * @author 61990
     * @updateTime 2018/3/5
     * @return 是否操作成功
     */
    public boolean updateReservation(Reservation reservation){
        super.save(reservation);
        return true;
    }

}
