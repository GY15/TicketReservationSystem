package web.controller;


import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.*;
import web.service.SeatService;
import web.service.UserService;
import web.utilities.enums.UserType;
import web.utilities.format.SeatMapConvert;

import javax.servlet.ServletContext;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @GetMapping(value = "/")
    protected String getHome(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute("type", "init");
        return "venue_login";
    }

    @PostMapping(value = "/login")
    protected String login(@RequestParam("login_id") String venueid, @RequestParam("login_password") String password,
                           HttpServletRequest request, HttpServletResponse response) {
        String remember = request.getParameter("remember");
        HttpSession session = request.getSession(true);

        if (remember != null) {
            Cookie myCookie = new Cookie("login_id", venueid);
            response.addCookie(myCookie);
            myCookie.setMaxAge(60 * 60 * 24);
        }
        if (userService.login(venueid, password, UserType.VENUE)) {
            session.setAttribute("venueid", venueid);
            return "venue_plan";
        } else {
            session.setAttribute("errorMessage", "1");
            return "venue_login";
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
//        System.out.println(seat_info)   ;
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
    protected String myPlan(HttpServletRequest request, HttpServletResponse response) {
        return "venue_plan";
    }

    @GetMapping(value = "/my_info")
    protected String myInfo(HttpServletRequest request, HttpServletResponse response) {
        return "venue_info";
    }

    @RequestMapping(value = "/publish")
    protected @ResponseBody
    String publish(@RequestParam("startTime") String startTime,@RequestParam("endTime") String endTime,
                   @RequestParam("type") String type, @RequestParam("description") String description,
                   @RequestParam("seat_info") String seat_info, HttpServletRequest request, HttpServletResponse response) throws ParseException {
        HttpSession session = request.getSession();
        int venueid = Integer.parseInt(request.getSession().getAttribute("venueid").toString());
        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm");
        Plan plan = new Plan(venueid,formatter.parse(startTime),formatter.parse(endTime),type,description,seat_info);
        seatService.publishPlan(plan);
        return "publish_plan";
    }
}
