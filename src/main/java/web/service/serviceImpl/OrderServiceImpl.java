package web.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.*;
import web.entity.*;
import web.model.*;
import web.service.DiscountService;
import web.service.OrderService;
import web.service.PlanService;
import web.service.UserService;
import web.utilities.RefundUtil;
import web.utilities.enums.OrderState;
import web.utilities.enums.ReservationState;

import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    CouponDao couponDao;
    @Autowired
    PlanService planService;
    @Autowired
    DiscountService discountService;
    @Autowired
    UserService userService;
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
    public String createOrder(String email, int venueid, int planid, String block,List<String> seat,double value,boolean isMember,OrderState orderState, int coupinid){
        List<String> tickets =  ticketDao.bookTicket(planid,block,seat);
        if(tickets == null){
            return "fail";
        }
        if(isMember == true){
            Order order = new Order(email, planid, JSON.toJSONString(tickets), venueid, orderState.getRepre(), value, JSON.toJSONString(seat),block);
            if (coupinid != 0){
                //todo 尚未实现
                couponDao.delete(Coupon.class,coupinid+"");
            }
            return ""+orderDao.createOrder(order);
        }
        return "fail";
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
     * 得到用户的订单
     *
     * @author 61990
     * @updateTime 2018/2/24
     * @param email 用户邮箱
     * @param orderid 订单id
     * @return 订单的座位信息
     */
    public OrderGeneral getOrder(String email,int orderid){
        return parseOrder(orderDao.getOrder(orderid));
    }

    /**
     * 得到用户的预购订单
     *
     * @author 61990
     * @updateTime 2018/3/5
     * @param email 用户邮箱
     * @return 订单的座位信息
     */
    public List<Reservation> getReservation(String email){
        return orderDao.getReservation(email);
    }

    /**
     * 支付‘未支付’的订单
     *
     * @author 61990
     * @updateTime 2018/2/25
     * @param email 用户邮箱
     * @param orderid 订单id
     * @return 是否支付成功
     */
    public String payOrder(int orderid, String email){
        Order order = orderDao.getOrder(orderid);
        //TODO 之后优化这里
        Member member = userDao.getMember(email);
        if (member.getBalance()<order.getValue()){
            return "账户余额不足";
        }
        if (!order.getState().equals(OrderState.NOT_PAY.getRepre())){
            return "支付失败，订单已经取消";
        }
        order.setState(OrderState.PAY.getRepre());
        orderDao.updateOrder(order);
        member.setBalance(member.getBalance()-order.getValue());
        userDao.updateMember(member);
        return "success";
    }
    /**
     * 检查超过十五分钟的订单失效
     *
     * @author 61990
     * @updateTime 2018/3/5
     * @return null
     */
    public void checkOvertime(){
        orderDao.checkOvertime();
    }
    /**
     * 定期的快速购票
     *
     * @author 61990
     * @updateTime 2018/3/5
     * @return null
     */
    public void autoDistributeTicket(){
        List<Reservation> reservations = orderDao.getReservationOrder();
        for (Reservation reservation : reservations){
            Plan plan = planDao.getOnePlan(reservation.getPlanid());
            if(plan.getStartTime().getTime()-new Date().getTime() <= 12*1000*3600*24) {
                List<Ticket> tickets = ticketDao.autoChooseTickets(reservation.getPlanid(), reservation.getNumber(),reservation.getBlock());
                if (tickets == null) {
                    userService.refund(reservation.getEmail(),reservation.getValue());
                    reservation.setState(ReservationState.ALLOCATE_FAIL.getRepre());
                    orderDao.updateReservation(reservation);
                } else {
                    List<String> seats = new ArrayList<>();
                    for (Ticket ticket : tickets) {
                        String[] temp =  ticket.getTicketid().split("_");
                        seats.add(temp[2]+"_"+temp[3]);
                    }
                    createOrder(reservation.getEmail(), reservation.getVenueid(), reservation.getPlanid(), reservation.getBlock(),seats,reservation.getValue(),true,OrderState.PAY,0);
                    reservation.setState(ReservationState.ALLOCATE_SUCCESS.getRepre());
                    orderDao.updateReservation(reservation);
                }
            }
        }
    }

    /**
     * 生成一条预购订单
     *
     * @author 61990
     * @updateTime 2018/3/5
     * @return 是否生成预购成功
     */
    public boolean createReservation(Reservation reservation){
        double ticketValue= planService.getBlockValue(reservation.getPlanid(),reservation.getBlock());
        double orderValue = ticketValue*reservation.getNumber()*discountService.getDiscount(reservation.getEmail());
        if (userService.getBalance(reservation.getEmail()) < orderValue){
            return false;
        }else {
            reservation.setValue(orderValue);
            userService.consume(reservation.getEmail(),orderValue);
            orderDao.createReservation(reservation);
        }
        return true;
    }


    /**
     * 申请退款
     *
     * @author 61990
     * @updateTime 2018/3/5
     * @return 退款金额
     */
    public boolean refundOrder(int orderid){
        Order order = orderDao.getOrder(orderid);
        order.setState(OrderState.REFUND.getRepre());
        orderDao.updateOrder(order);
        //todo 退票的其他事宜
        return true;
    }
    /**
     * 获得退款的利率
     *
     * @author 61990
     * @updateTime 2018/3/5
     * @return 订单退款利率
     */
    public double getPoundageRate(int orderid){
        Order order = orderDao.getOrder(orderid);
        Plan plan = planDao.getOnePlan(order.getPlanid());
        return RefundUtil.getPoundageRate(plan.getStartTime());
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
            orderGenerals.add(parseOrder(order));
        }
        return orderGenerals;
    }

    /**
     * 将订单转化为可用的形式
     *
     * @author 61990
     * @updateTime 2018/2/23
     * @return orderGeneral
     */
    public OrderGeneral parseOrder(Order order){
            Venue venue = userDao.getVenue(order.getVenueid());
            Plan plan = planDao.getOnePlan(order.getPlanid());
            OrderGeneral orderGeneral = new OrderGeneral(order.getOrderid(), order.getEmail(), order.getPlanid(), order.getBlock(), order.getSeats(),
                    order.getVenueid(), order.getState(), plan.getStartTime(), plan.getEndTime(), venue.getName(), order.getValue(), order.getCreateTime(),
                    venue.getProvince(),venue.getCity(),venue.getLocation(),plan.getDescription(),plan.getType());
        return orderGeneral;
    }
}
