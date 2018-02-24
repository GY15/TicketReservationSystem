package web.controller;


import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.*;
import web.service.*;
import web.utilities.enums.OrderState;
import web.utilities.enums.UserType;
import web.utilities.format.SeatMapConvert;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletContext;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/venue")
public class VenueController extends HttpServlet {
    @Autowired
    ServletContext Context;
    @Autowired
    private UserService userService;
    @Autowired
    private SeatService seatService;
    @Autowired
    private PlanService planService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private DiscountService discountService;

    @GetMapping(value = "/")
    protected String getHome(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        //        String login="";
//        Cookie cookie = null;
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

        return "venue_login";
    }

    @PostMapping(value = "/login")
    protected ModelAndView login(@RequestParam("login_id") String venueid, @RequestParam("login_password") String password,
                           HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession(true);

//        String remember = request.getParameter("remember");
//        if (remember != null) {
//            Cookie myCookie = new Cookie("login_id", venueid);
//            response.addCookie(myCookie);
//            myCookie.setMaxAge(60 * 60 * 24);
//        }


        if (userService.login(venueid, password, UserType.VENUE)) {
            session.setAttribute("venueid", venueid);
            List<PlanGeneral> planGenerals = planService.getPlanGeneral(Integer.parseInt(venueid));
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/venue/my_plan"));
            ModelAndView mv = new ModelAndView("venue_plan");
            mv.addObject("plans", planGenerals);
            return mv;
        } else {
            ModelAndView mv = new ModelAndView("venue_login");
            session.setAttribute("errorMessage","1");
            return mv;
        }
    }


    @GetMapping(value = "/logout")
    protected String logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);//防止创建Session
        return "";
    }

    @PostMapping(value = "/valid", produces = "text/html;charset=UTF-8;")
    protected @ResponseBody
    String valid() {
        return userService.createVenueId() + "";
    }

    @PostMapping(value = "/register_venue")
    protected @ResponseBody
    String register(@RequestParam("venueid") int venueid, @RequestParam("name") String name, @RequestParam("password") String password,
                    @RequestParam("province") String province, @RequestParam("city") String city, @RequestParam("location") String location,
                    @RequestParam("seat_info") String seat_info,
                    HttpServletRequest request, HttpServletResponse response) {
        Venue venue = new Venue(venueid, password, name, province, city, location);
        userService.modifyVenueMessage(venue);
        List<SeatMap> seats = JSON.parseArray(seat_info,SeatMap.class);
        seatService.submitSeatMap(seats);
        return "success";
    }

    @GetMapping(value = "/open_publish")
    protected ModelAndView openPublish(HttpServletRequest request, HttpServletResponse response) {
        int venueid = Integer.parseInt(request.getSession().getAttribute("venueid").toString());
        List<SeatMap> seatMaps = seatService.getSeatMap(venueid);
        List<SeatMapObj> seatMapObjs = SeatMapConvert.StringToObj(seatMaps);
        ModelAndView mv = new ModelAndView("publish_plan");
        mv.addObject("seatMaps", seatMapObjs);
        mv.addObject("seatMapsJson", JSON.toJSONString(seatMapObjs));
        return mv;
    }

    @GetMapping(value = "/my_plan")
    protected ModelAndView myPlan(HttpServletRequest request, HttpServletResponse response) {
        int venueid = Integer.parseInt(request.getSession().getAttribute("venueid").toString());
        List<PlanGeneral> planGenerals = planService.getPlanGeneral(venueid);
        ModelAndView mv = new ModelAndView("venue_plan");
        mv.addObject("plans", planGenerals);
        return mv;
    }

    @PostMapping(value = "/open_plan_detail")
    protected ModelAndView openDetail(@RequestParam("planid") int planid,@RequestParam("selectpicker") String block,
            HttpServletRequest request, HttpServletResponse response) {
        PlanGeneral planGeneral = planService.getPlan(planid);
        ModelAndView mv = new ModelAndView("venue_buy");
        mv.addObject("planJson", JSON.toJSONString(planGeneral));
        mv.addObject("plan", planGeneral);
        mv.addObject("block", block);
        return mv;
    }


    @GetMapping(value = "/my_info")
    protected ModelAndView myInfo(HttpServletRequest request, HttpServletResponse response) {
        int venueid = Integer.parseInt(request.getSession().getAttribute("venueid").toString());
        Venue venue = userService.getVenueInfo(venueid);
        ModelAndView mv = new ModelAndView("venue_info");
        mv.addObject("venue", venue);
        return mv;
    }


    @RequestMapping(value = "/publish")
    protected @ResponseBody
    String publish(@RequestParam("startTime") String startTime,@RequestParam("endTime") String endTime,
                   @RequestParam("type") String type, @RequestParam("description") String description,
                   @RequestParam("seat_info") String seat_info,  @RequestParam("seat_num") int seat_num,HttpServletRequest request, HttpServletResponse response) throws ParseException {
        int venueid = Integer.parseInt(request.getSession().getAttribute("venueid").toString());
        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm");
        Plan plan = new Plan(venueid,seat_num,formatter.parse(startTime),formatter.parse(endTime),type,description,seat_info);
        planService.publishPlan(plan);
        return "publish_plan";
    }

    @PostMapping(value = "/createVenueOrder")
    protected @ResponseBody
    String createOrder(@RequestParam("member") boolean member,@RequestParam("planid") int planid,@RequestParam("email") String email,
                   @RequestParam("block") String block, @RequestParam("seats") String seats,
                   @RequestParam("value") double value,HttpServletRequest request){
        List<String> booked_seats = JSON.parseArray(seats,String.class);
        int venueid = Integer.parseInt(request.getSession().getAttribute("venueid").toString());
        String result = orderService.createOrder(email,venueid, planid, block,  booked_seats, value, member, OrderState.ARRIVE);
        return result;
    }

    @PostMapping(value = "/getBookedArray")
    protected @ResponseBody
    String[] getBookedArray(@RequestParam("planid") int planid,@RequestParam("block") String block) {
        return ticketService.getBookedArray(planid,block);
    }

    //用于现场购票
    @PostMapping(value = "/getDiscount")
    protected @ResponseBody
    double getDiscount(@RequestParam("email") String email) {
        return discountService.getDiscount(email);
    }

    //用于现场检票
    @PostMapping(value = "/check_ticket")
    protected @ResponseBody
    String checkTicket(@RequestParam("planid") int planid,@RequestParam("orderid") int orderid,HttpServletRequest request) {
        int venueid = Integer.parseInt(request.getSession().getAttribute("venueid").toString());
        return orderService.checkTicket(planid,orderid,venueid);
    }
}
