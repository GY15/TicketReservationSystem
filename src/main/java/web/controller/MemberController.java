package web.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.entity.Coupon;
import web.entity.Member;
import web.entity.Reservation;
import web.model.OrderGeneral;
import web.model.PlanGeneral;
import web.service.*;
import web.utilities.enums.OrderState;
import web.utilities.enums.ReservationState;
import web.utilities.enums.UserType;
import web.utilities.exceptions.MemberInvalidExistException;
import web.utilities.exceptions.PasswordWrongException;
import web.utilities.exceptions.UserNotExistException;

import javax.servlet.ServletContext;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController extends HttpServlet {
    @Autowired
    ServletContext Context;
    @Autowired
    private UserService userService;
    @Autowired
    private PlanService planService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private DiscountService discountService;
    @Autowired
    private RecordService recordService;


    @GetMapping(value = "/")
    protected String getHome(HttpServletRequest request) {

//        String login="";
        HttpSession session = request.getSession(true);
//        Cookie cookie = null;
//
//        Cookie[] cookies = request.getCookies();
//
//        if (null != cookies) {
//            for (int i = 0; i < cookies.length; i++) {
//                cookie = cookies[i];
//                if (cookie.getName().equals("loginID")) {
//                    login=cookie.getValue();
//                    break;
//                }
//            }
//        }
        session.setAttribute("type", "init");
        return "member/member_login";
    }

    @PostMapping(value = "/login")
    protected void login(@RequestParam("login_email") String email, @RequestParam("login_password") String password,
                                 HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(true);
//        String remember = request.getParameter("remember");
//        if (remember != null) {
//            Cookie myCookie = new Cookie("login_email", email);
//            response.addCookie(myCookie);
//            myCookie.setMaxAge(60 * 60 * 24);
//        }

        try {
            userService.login(email, password, UserType.MEMBER);
            session.setAttribute("email", email);
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/member/plan_page"));
        } catch (UserNotExistException | PasswordWrongException|MemberInvalidExistException e) {
            session.setAttribute("type", "init");
            session.setAttribute("errorMessage", e.getMessage());
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/member/"));
        }
    }

    @GetMapping(value = "/logout")
    protected String logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);//防止创建Session
        session.removeAttribute("email");
        response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/member/"));
        return null;
    }

    @PostMapping(value = "/valid", produces = "text/html;charset=UTF-8;")
    protected @ResponseBody
    String valid(@RequestParam("email") String email) {
        return userService.validEmail(email).getRepre();
    }

    @PostMapping(value = "/registerMember")
    protected String registerMember(@RequestParam("reg_email") String reg_email, @RequestParam("valid_word") String valid_word,
                                    @RequestParam("reg_nickname") String reg_nickname, @RequestParam("reg_password") String reg_password,
                                    @RequestParam("reg_password2") String reg_password2,
                                    HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if (!reg_password.equals(reg_password2) || reg_password.length() < 6 || valid_word.length() != 6 || reg_nickname.length() == 0) {
            session.setAttribute("type", "fail");
            if(!reg_password.equals(reg_password2) ){
                session.setAttribute("errorMessage", "两次密码输入不一致");
            }else {
                session.setAttribute("errorMessage", "密码格式不正确");
            }
        }else {
            Member member = new Member(reg_email, reg_nickname, reg_password);
            if (userService.registerMember(member, valid_word)) {
                session.setAttribute("type", "success");
                session.setAttribute("registerMember", member.getEmail());
            } else {
                session.setAttribute("type", "fail");
                session.setAttribute("errorMessage", "验证码错误，注册失败！");
            }
        }
        return "member/member_login";
    }

    @GetMapping(value = "/plan_page")
    protected ModelAndView searchPlan(HttpServletRequest request, HttpServletResponse response) {
        List<PlanGeneral> planGenerals = planService.getPlanGeneral();
        ModelAndView mv = new ModelAndView("member/member_plan");
        mv.addObject("plans", planGenerals);
        return mv;
    }


    @PostMapping(value = "/open_plan_detail")
    protected ModelAndView openDetail(@RequestParam("planid") int planid, @RequestParam("selectpicker") String block, HttpServletRequest request) {
        String email = request.getSession().getAttribute("email").toString();
        PlanGeneral planGeneral = planService.getPlan(planid);
        List<Coupon> coupons = discountService.getCoupons(email);
        double discount = discountService.getDiscount(email);
        ModelAndView mv = new ModelAndView("member/member_buy");
        mv.addObject("planJson", JSON.toJSONString(planGeneral));
        mv.addObject("plan", planGeneral);
        mv.addObject("block", block);
        mv.addObject("discount", discount);
        mv.addObject("couponJson", JSON.toJSONString(coupons));
        return mv;
    }

    @PostMapping(value = "/createMemberOrder")
    protected @ResponseBody
    String createOrder(@RequestParam("planid") int planid, @RequestParam("venueid") int venueid, @RequestParam("block") String block,
                       @RequestParam("seats") String seats, @RequestParam("value") double value, @RequestParam("couponid") int couponid, HttpServletRequest request) {
        String email = request.getSession().getAttribute("email").toString();
        List<String> booked_seats = JSON.parseArray(seats, String.class);
        String result = orderService.createOrder(email, venueid, planid, block, booked_seats, value, true, OrderState.NOT_PAY, couponid);
        return result;
    }

    @GetMapping(value = "/goto_pay")
    protected @ResponseBody
    ModelAndView gotoPay(@RequestParam("orderid") int orderid, HttpServletRequest request) throws ParseException {
        String email = request.getSession().getAttribute("email").toString();
        OrderGeneral orderGeneral = orderService.getOrder(email, orderid);
        ModelAndView mv = new ModelAndView("member/member_pay");
        mv.addObject("order", orderGeneral);
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date createTime = sdf.parse(orderGeneral.getCreateTime());
        int second = (int) ((createTime.getTime() - now.getTime()) / 1000 + 900);
        mv.addObject("second", second);
        return mv;
    }


    @GetMapping(value = "/my_order")
    protected @ResponseBody
    ModelAndView myOrder(@RequestParam("state") String state, HttpServletRequest request, HttpServletResponse response) {
        String email = request.getSession().getAttribute("email").toString();
        List<OrderGeneral> orders = orderService.getOrders(email, OrderState.getName(state));
        ModelAndView mv = new ModelAndView("member/member_order");
        mv.addObject("orders", orders);
        return mv;
    }

    @GetMapping(value = "/my_quick_order")
    protected @ResponseBody
    ModelAndView myReservation(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getSession().getAttribute("email").toString();
        List<Reservation> orders = orderService.getReservation(email);
        ModelAndView mv = new ModelAndView("member/member_reservation");
        mv.addObject("orders", orders);
        return mv;
    }


    @GetMapping(value = "/my_info")
    protected @ResponseBody
    ModelAndView myInfo(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getSession().getAttribute("email").toString();
        Member member = userService.getMember(email);
        List<Coupon> coupons = discountService.getCoupons(email);
        ModelAndView mv = new ModelAndView("member/member_info");
        mv.addObject("member", member);
        mv.addObject("coupons", coupons);
        mv.addObject("records", JSON.toJSONString(recordService.getMemberConsumeRecords(email)));
        return mv;
    }

    @PostMapping(value = "/recharge")
    protected @ResponseBody
    String recharge(@RequestParam("money") int money, HttpServletRequest request, HttpServletResponse response) {
        String email = request.getSession().getAttribute("email").toString();
        userService.recharge(email, money);
        return "success";
    }

    @PostMapping(value = "/cancel_member")
    protected @ResponseBody
    String cancelMember(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getSession().getAttribute("email").toString();
        userService.cancelMember(email);
        HttpSession session = request.getSession(false);
        session.removeAttribute("email");
        return "success";
    }

    @PostMapping(value = "/modify_name")
    protected @ResponseBody
    String modifyName(@RequestParam("nickname") String nickname,
                      HttpServletRequest request, HttpServletResponse response) {
        String email = request.getSession().getAttribute("email").toString();
        Member member = userService.getMember(email);
        member.setNickname(nickname);
        userService.modifyMemberMessage(member);
        return "success";
    }

    @PostMapping(value = "/modify_password")
    protected @ResponseBody
    String modifyPassword(@RequestParam("old_password") String old, @RequestParam("new_password") String password,
                          @RequestParam("new_password2") String password2,
                          HttpServletRequest request, HttpServletResponse response) {
        String email = request.getSession().getAttribute("email").toString();
        Member member = userService.getMember(email);
        if (member.getPassword().equals(old) && password.equals(password2)) {
            member.setPassword(password);
            userService.modifyMemberMessage(member);
            return "success";
        }
        return "fail";
    }

    @PostMapping(value = "/pay_order")
    protected @ResponseBody
    String payOrder(@RequestParam("orderid") int orderid, HttpServletRequest request) {
        String email = request.getSession().getAttribute("email").toString();
        return orderService.payOrder(orderid, email);
    }

    @PostMapping(value = "/quick_order")
    protected @ResponseBody
    boolean quickOrder(@RequestParam("planid") int planid, @RequestParam("venueid") int venueid, @RequestParam("number") int number, @RequestParam("block") String block,
                       HttpServletRequest request) {
        String email = request.getSession().getAttribute("email").toString();
        Reservation reservation = new Reservation(email, planid, venueid, number, ReservationState.RESERVATION.getRepre(), block);
        return orderService.createReservation(reservation);
    }

    @PostMapping(value = "/refund")
    protected @ResponseBody
    double refund(@RequestParam("orderid") int orderid, HttpServletRequest request) {
        return Double.parseDouble(String.format("%.2f", orderService.refundOrder(orderid)));
    }

    @PostMapping(value = "/get_refund_rate")
    protected @ResponseBody
    double getRefundRate(@RequestParam("orderid") int orderid, HttpServletRequest request) {
        String email = request.getSession().getAttribute("email").toString();
        return Double.parseDouble(String.format("%.2f", orderService.getPoundageRate(orderid)));
    }

    @PostMapping(value = "/switch_coupon")
    protected @ResponseBody
    String switchCoupon(@RequestParam("type") int type, @RequestParam("minimum") int minimum, @RequestParam("grade") int grade, @RequestParam("discount") double discount, HttpServletRequest request) {
        String email = request.getSession().getAttribute("email").toString();
        Coupon coupon = new Coupon(email, type, minimum, discount);
        return userService.switchCoupon(coupon, grade) ? "success" : "fail";
    }
}
