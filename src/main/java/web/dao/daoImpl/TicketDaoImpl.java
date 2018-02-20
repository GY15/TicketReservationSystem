package web.dao.daoImpl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import web.dao.SeatDao;
import web.dao.TicketDao;
import web.model.Plan;
import web.model.SeatMap;
import web.model.Ticket;
import web.utilities.HibernateUtil;

import java.util.ArrayList;
import java.util.List;


@Repository
public class TicketDaoImpl extends BaseDaoImpl implements TicketDao {
    /**
     * 添加一个plan的所有座位信息
     *
     * @param
     * @return 是否成功
     * @author 61990
     * @updateTime 2018/2/15
     */
    public boolean addTicket(List<Ticket> tickets) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        for (Ticket ticket : tickets) {
            session.save(ticket);
        }
        transaction.commit();
        session.close();
        return true;
    }
    /**
     * 获得指定planid所有已经预定的ticket
     *
     * @author 61990
     * @updateTime 2018/2/18
     * @param planid 计划id
     * @return list<ticket>
     */
    public List<Ticket> getBookedTicket(int planid,String block){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        String sql = "SELECT * from ticket WHERE planid = "+ planid + " AND block = '"+ block + "' AND state != '未出售'";
        Query query = session.createSQLQuery(sql).addEntity(Ticket.class);
        List<Ticket> tickets= query.list();
        transaction.commit();
        session.close();
        return tickets;
    }
    /**
     * 预定占有所有ticket
     *
     * @author 61990
     * @updateTime 2018/2/18
     * @param planid 计划id
     * @param block plan的区域
     * @param seat 选中的seat
     * @return ticket id 的 list
     */
    public List<String> bookTicket(int planid,String block,List<String> seat){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        String rowAndCol = "(";
        rowAndCol += ("(row = "+seat.get(0).split("_")[0]+" and col = "+seat.get(0).split("_")[1]+")");
        for(int i = 1; i<seat.size();i++){
            rowAndCol += ("or (row = "+seat.get(i).split("_")[0]+" and col = "+seat.get(i).split("_")[1]+")");
        }
        rowAndCol += ")";
        String sql = "SELECT * from ticket WHERE planid = " + planid + " AND block = '" + block + "' AND " + rowAndCol;
        Query query = session.createSQLQuery(sql).addEntity(Ticket.class);
        List<Ticket> tickets= query.list();
        List<String> list = new ArrayList<>();
        for (Ticket ticket : tickets){
            list.add(ticket.getTicketid());
            if(!ticket.getState().equals("未出售")){
                transaction.commit();
                session.close();
                return null;
            }
        }
        for (Ticket ticket : tickets){
            ticket.setState("已预定");
            super.update(seat);
        }
        transaction.commit();
        session.close();
        return list;
    }
}
