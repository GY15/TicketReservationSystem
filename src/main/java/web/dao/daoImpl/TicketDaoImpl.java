package web.dao.daoImpl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import web.dao.TicketDao;
import web.entity.Reservation;
import web.entity.Ticket;
import web.utilities.HibernateUtil;
import web.utilities.enums.ReservationState;

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
        for (Ticket ticket : tickets) {
            super.save(ticket);
        }
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
        transaction.commit();
        session.close();

        List<String> list = new ArrayList<>();
        for (Ticket ticket : tickets){
            list.add(ticket.getTicketid());
            if(!ticket.getState().equals("未出售")){
                return null;
            }
        }

        for (Ticket ticket : tickets){
            ticket.setState("已预订");
            super.update(ticket);
        }
        return list;
    }

    /**
     * 随机选择指定数的票的id
     *
     * @author 61990
     * @updateTime 2018/2/18
     * @param number 选择的票数
     * @return list<ticket>
     */
    public List<Ticket> autoChooseTickets(int planid,int number, String block){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        String sql = "SELECT * from ticket WHERE planid = "+ planid +" AND state = '未出售' and block = '"+block+"' LIMIT " + number;
        Query query = session.createSQLQuery(sql).addEntity(Ticket.class);
        List<Ticket> tickets= query.list();
        transaction.commit();
        session.close();
        if (tickets.size() < number ){
            return null;
        }
        return tickets;
    }

    /**
     * 退票退座位
     *
     * @author 61990
     * @updateTime 2018/3/5
     * @param tickets 退票的id
     * @return 是否成功
     */
    public boolean refundTickets(List<String> tickets){
        for (String ticketid : tickets){
            Ticket ticket = (Ticket) super.load(Ticket.class, ticketid);
            ticket.setState(ReservationState.ALLOCATE_FAIL.getRepre());
            super.update(ticket);
        }
        return true;
    }

}
