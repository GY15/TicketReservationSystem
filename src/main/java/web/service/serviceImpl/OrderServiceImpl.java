package web.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.OrderDao;
import web.dao.PlanDao;
import web.dao.TicketDao;
import web.dao.UserDao;
import web.model.*;
import web.service.OrderService;
import web.service.SeatService;
import web.service.TicketService;
import web.utilities.enums.OrderState;
import web.utilities.format.SeatMapConvert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    TicketDao ticketDao;
    @Autowired
    OrderDao orderDao;
    @Autowired
    UserDao userDao;
    @Autowired
    PlanDao planDao;
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
    public String createOrder(String email, int venueid, int planid, String block,List<String> seat,double value,boolean isMember,OrderState orderState){
        List<String> tickets =  ticketDao.bookTicket(planid,block,seat);
        if(tickets == null){
            return "fail";
        }
        if(isMember == true){
            Order order = new Order(email, planid, JSON.toJSONString(tickets), venueid, orderState.getRepre(), value, JSON.toJSONString(seat),block);
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
    /**
     * 得到用户的订单
     *
     * @author 61990
     * @updateTime 2018/2/21
     * @param email 用户邮箱
     * @param orderState 订单状况
     * @return 订单的座位信息
     */
    public List<OrderGeneral> getOrders(String email, OrderState orderState){
        return parseOrder(orderDao.getOrders(email,orderState));
    }
    /**
     * 将订单list转化为可用的形式
     *
     * @author 61990
     * @updateTime 2018/2/23
     * @return list
     */
    public List<OrderGeneral> parseOrder(List<Order> orders){
        List<OrderGeneral> orderGenerals = new ArrayList<>();
        for (Order order: orders){
            Venue venue = userDao.getVenue(order.getVenueid());
            Plan plan = planDao.getOnePlan(order.getPlanid());
            OrderGeneral orderGeneral = new OrderGeneral(order.getOrderid(), order.getEmail(), order.getPlanid(), order.getBlock(), order.getSeats(),
                    order.getVenueid(), order.getState(), plan.getStartTime(), plan.getEndTime(), venue.getName(), order.getValue(), order.getCreateTime(),
                    venue.getProvince(),venue.getCity(),venue.getLocation(),plan.getDescription(),plan.getType());
            orderGenerals.add(orderGeneral);
        }
        return orderGenerals;
    }
}
