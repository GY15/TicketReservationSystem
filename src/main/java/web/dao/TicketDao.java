package web.dao;

import web.entity.Ticket;

import java.util.List;

/**
 * Created by 61990 on 2017/12/19.
 */
public interface TicketDao extends BaseDao {
    /**
     * 添加一个plan的所有座位信息
     *
     * @author 61990
     * @updateTime 2018/2/15
     * @param
     * @return 是否成功
     */
    boolean addTicket(List<Ticket> tickets);
    /**
     * 获得指定planid所有已经预定的ticket
     *
     * @author 61990
     * @updateTime 2018/2/18
     * @param venueid 场馆id
     * @param block 区域
     * @return list<ticket>
     */
     List<Ticket> getBookedTicket(int planid,String block);
    /**
     * 预定占有所有ticket
     *
     * @author 61990
     * @updateTime 2018/2/18
     * @param planid 计划id
     * @param block plan的区域
     * @param seat 选中的seat
     * @return 是否成功
     */
    List<String> bookTicket(int planid,String block,List<String> seat);

    /**
     * 随机选择指定数的票的id
     *
     * @author 61990
     * @updateTime 2018/3/5
     * @param number 选择的票数
     * @return list<ticket>
     */
    List<Ticket> autoChooseTickets(int planid,int number,String block);

    /**
     * 退票退座位
     *
     * @author 61990
     * @updateTime 2018/3/5
     * @param tickets 退票的id
     * @return 是否成功
     */
    boolean refundTickets(List<String> tickets);
}
