package web.service;

import web.model.Plan;
import web.model.PlanGeneral;
import web.model.SeatMapObj;
import web.model.Ticket;

import java.util.List;

/**
 * 管理用户登录
 */
public interface TicketService {

    /**
     * 获得已经预定的的座位
     *
     * @author 61990
     * @updateTime 2018/2/18
     * @param planid 预定的list
     * @param block 区域
     * @return 是否成功
     */
    String[] getBookedArray(int planid,String block);
    /**
     * 由seatMapObjs 生成tickets
     *
     * @author 61990
     * @updateTime 2018/2/15
     * @param seatMapObjs 多个区域的座位信息
     * @param planid 计划的id信息
     * @return ticket list
     */
    String createTickets(int planid , List<SeatMapObj> seatMapObjs);
}