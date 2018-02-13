package web.controller;


import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import web.model.SeatMap;
import web.model.Venue;
import web.service.SeatService;
import web.service.UserService;
import web.utilities.enums.UserType;

import javax.servlet.ServletContext;
import javax.servlet.http.*;
import java.io.IOException;

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
        Venue venue = new Venue(venueid, password, name, province, city, location);
        userService.modifyVenueMessage(venue);
//        System.out.println(seat_info);
//        JSONObject jsonObj = ;
        SeatMap seatMap = JSON.parseObject(seat_info,SeatMap.class);
        seatMap.setVenueid(venueid);
        SeatMap[] seatMaps = new SeatMap[]{seatMap};
        seatService.submitSeatMap(seatMaps);
        return "success";
    }

    @GetMapping(value = "/venue_plan_publish")
    protected String pub(HttpServletRequest request, HttpServletResponse response) {

//        HttpSession session = request.getSession();
//        if(!reg_password.equals(reg_password2)||reg_password.length()<6||valid_word.length()!=6||reg_nickname.length()==0){
//            session.setAttribute("type","fail");
//            return "member_home";
//        }
//        Member member = new Member(reg_email,reg_nickname,reg_password);
//        if (userService.registerMember(member,valid_word)){
//            session.setAttribute("type","success");
//            session.setAttribute("registerMember",member.getEmail());
//        }else{
//            session.setAttribute("type","fail");
//        }
        return "publish_plan";
    }

    @PostMapping(value = "/publish")
    protected String publish(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute("venueid", "123456");

//        if(!reg_password.equals(reg_password2)||reg_password.length()<6||valid_word.length()!=6||reg_nickname.length()==0){
//            session.setAttribute("type","fail");
//            return "member_home";
//        }
//        Member member = new Member(reg_email,reg_nickname,reg_password);
//        if (userService.registerMember(member,valid_word)){
//            session.setAttribute("type","success");
//            session.setAttribute("registerMember",member.getEmail());
//        }else{
//            session.setAttribute("type","fail");
//        }
        return "publish_plan";
    }
}
