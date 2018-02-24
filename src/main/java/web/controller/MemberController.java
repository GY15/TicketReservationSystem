package web.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.Coupon;
import web.model.Member;
import web.model.OrderGeneral;
import web.model.PlanGeneral;
import web.service.DiscountService;
import web.service.OrderService;
import web.service.PlanService;
import web.service.UserService;
import web.utilities.enums.OrderState;
import web.utilities.enums.UserType;

import javax.servlet.ServletContext;
import javax.servlet.http.*;
import java.io.IOException;
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
        session.setAttribute("type","init");
        return "member_login";
    }

    @PostMapping(value = "/login")
    protected ModelAndView login(@RequestParam("login_email") String email, @RequestParam("login_password") String password,
                                 HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(true);
//        String remember = request.getParameter("remember");
//        if (remember != null) {
//            Cookie myCookie = new Cookie("login_email", email);
//            response.addCookie(myCookie);
//            myCookie.setMaxAge(60 * 60 * 24);
//        }

        if (userService.login(email, password, UserType.MEMBER)) {
            session.setAttribute("email", email);
            List<PlanGeneral> planGenerals = planService.getPlanGeneral();
//            response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/member/plan_page"));
            ModelAndView mv = new ModelAndView("member_plan");
            mv.addObject("plans", planGenerals);
            return mv;
        } else {
            ModelAndView mv = new ModelAndView("member_login");
            session.setAttribute("type", "init");
            session.setAttribute("errorMessage", "1");
            return mv;
        }
    }

    @GetMapping(value = "/logout")
    protected String logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);//防止创建Session
        if(session!=null) {
            if (session.getAttribute("userID") != null) {
                int counter = Integer.parseInt((String) Context.getAttribute("loginCounter"));
                counter--;
                Context.setAttribute("loginCounter", Integer.toString(counter));
            }
        }
        session.removeAttribute("userID");
        response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/home"));
        return null;
    }

    @PostMapping(value = "/valid", produces = "text/html;charset=UTF-8;")
    protected @ResponseBody
    String valid(@RequestParam("email") String email){
        return userService.validEmail(email).getRepre();
    }

    @PostMapping(value = "/registerMember")
    protected String registerMember(@RequestParam("reg_email") String reg_email,@RequestParam("valid_word") String valid_word,
                                    @RequestParam("reg_nickname") String reg_nickname,@RequestParam("reg_password") String reg_password,
                                    @RequestParam("reg_password2") String reg_password2,
                                    HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        if(!reg_password.equals(reg_password2)||reg_password.length()<6||valid_word.length()!=6||reg_nickname.length()==0){
            session.setAttribute("type","fail");
            return "member_home";
        }
        Member member = new Member(reg_email,reg_nickname,reg_password);
        if (userService.registerMember(member,valid_word)){
            session.setAttribute("type","success");
            session.setAttribute("registerMember",member.getEmail());
        }else{
            session.setAttribute("type","fail");
        }
        return "member_login";
    }

    @GetMapping(value = "/plan_page")
    protected ModelAndView searchPlan(HttpServletRequest request, HttpServletResponse response) {
        List<PlanGeneral> planGenerals = planService.getPlanGeneral();
        ModelAndView mv = new ModelAndView("member_plan");
        mv.addObject("plans", planGenerals);
        return mv;
    }



    @PostMapping(value = "/open_plan_detail")
    protected ModelAndView openDetail(@RequestParam("planid") int planid,@RequestParam("selectpicker") String block,HttpServletRequest request) {
        String email = request.getSession().getAttribute("email").toString();
        PlanGeneral planGeneral = planService.getPlan(planid);
        List<Coupon> coupons = discountService.getCoupons(email);
        double discount = discountService.getDiscount(email);
        ModelAndView mv = new ModelAndView("member_buy");
        mv.addObject("planJson", JSON.toJSONString(planGeneral));
        mv.addObject("plan", planGeneral);
        mv.addObject("block", block);
        mv.addObject("discount",discount);
        mv.addObject("couponJson",JSON.toJSONString(coupons));
        return mv;
    }

    @PostMapping(value = "/createMemberOrder")
    protected @ResponseBody
    String createOrder(@RequestParam("planid") int planid, @RequestParam("venueid") int venueid, @RequestParam("block") String block,
                       @RequestParam("seats") String seats, @RequestParam("value") double value,HttpServletRequest request){
        String email = request.getSession().getAttribute("email").toString();
        List<String> booked_seats = JSON.parseArray(seats,String.class);
        String result = orderService.createOrder(email,venueid, planid, block,  booked_seats, value, true,OrderState.NOT_PAY);
        return result;
    }


    @GetMapping(value = "/my_order")
    protected ModelAndView myOrder(@RequestParam("state") String state,HttpServletRequest request, HttpServletResponse response) {
        String email = request.getSession().getAttribute("email").toString();
        List<OrderGeneral> orders = orderService.getOrders(email, OrderState.ALL);
        ModelAndView mv = new ModelAndView("member_order");
        mv.addObject("orders",orders);
        return mv;
    }

    @GetMapping(value = "/my_info")
    protected ModelAndView myInfo(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getSession().getAttribute("email").toString();
        Member member = userService.getMember(email);
        List<Coupon> coupons = discountService.getCoupons(email);
        ModelAndView mv = new ModelAndView("member_info");
        mv.addObject("member",member);
        mv.addObject("coupons",coupons);
        return mv;
    }
    @GetMapping(value = "/recharge")
    protected String recharge(@RequestParam("money")int money,HttpServletRequest request, HttpServletResponse response) {
        String email = request.getSession().getAttribute("email").toString();
        userService.recharge(email,money);
        return "success";
    }
}
