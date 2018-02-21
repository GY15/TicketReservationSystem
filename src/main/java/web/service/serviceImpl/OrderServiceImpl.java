package web.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.OrderDao;
import web.dao.TicketDao;
import web.model.Order;
import web.model.SeatMapObj;
import web.model.SeatType;
import web.model.Ticket;
import web.service.OrderService;
import web.service.SeatService;
import web.service.TicketService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    TicketDao ticketDao;
    @Autowired
    OrderDao orderDao;
    /**
     * 下订单，生成订单
     *
     * @author 61990
     * @updateTime 2018/2/18
     * @param planid 计划id
     * @param block plan的区域
     * @param seat 选中的seat
     * @return 是否成功
     */
    public String createOrder(String email, int venueid, int planid, String block,List<String> seat,double value,boolean isMember){
        List<String> seats =  ticketDao.bookTicket(planid,block,seat);
        if(seats == null){
            return "fail";
        }
        if(isMember == true){
            Order order = new Order(email, planid, JSON.toJSONString(seats), venueid, "已出票", value);
            orderDao.createOrder(order);
        }
        return "success";
    }
    /**
     * 检查订单是否符合计划id和 场馆id
     *
     * @author 61990
     * @updateTime 2018/2/21
     * @return 订单的座位信息
     */
    public String checkTicket(int planid, int orderid, int venueid){
        Order order = orderDao.getOrder(orderid);
        if(order.getPlanid() != planid || order.getVenueid() != venueid || order.getState().equals("已出票")) {
            return "";
        }
        order.setState("已出票");
        orderDao.updateOrder(order);
        return order.getTickets();
    }
}
