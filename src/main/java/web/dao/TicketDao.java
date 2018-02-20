package web.dao;

import web.dao.BaseDao;
import web.model.Plan;
import web.model.SeatMap;
import web.model.Ticket;

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
}
