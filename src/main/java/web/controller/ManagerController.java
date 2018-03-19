package web.controller;


import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.entity.*;
import web.model.PlanGeneral;
import web.model.SeatMapObj;
import web.service.*;
import web.utilities.enums.OrderState;
import web.utilities.enums.UserType;
import web.utilities.exceptions.MemberInvalidExistException;
import web.utilities.exceptions.PasswordWrongException;
import web.utilities.exceptions.UserNotExistException;
import web.utilities.format.SeatMapConvert;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/manage")
public class ManagerController extends HttpServlet {
    @Autowired
    ServletContext Context;
    @Autowired
    private UserService userService;
    @Autowired
    private RecordService recordService;

    @GetMapping(value = "/")
    protected String getHome(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        return "manager/manager_login";
    }

    @PostMapping(value = "/login")
    protected void login(@RequestParam("login_id") String manager, @RequestParam("login_password") String password,
                           HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(true);
        try {
            userService.login(manager, password, UserType.MANAGER);
            session.setAttribute("manager",  manager);
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/manage/verify"));
        } catch (UserNotExistException | PasswordWrongException e) {
            session.setAttribute("errorMessage",e.getMessage());
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/manage/"));
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }


    @GetMapping(value = "/logout")
    protected String logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);//防止创建Session
        session.removeAttribute("manager");
        response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/manage/"));
        return null;
    }
    @GetMapping(value = "/verify")
    protected ModelAndView openVerify(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("manager/manager_verify");
        List<Venue> venues = userService.getInvalidVenues();
        mv.addObject("venues",JSON.toJSONString(venues));
        return mv;
    }
    @PostMapping(value = "/verify_venue")
    protected @ResponseBody
    String verify( @RequestParam("venueid") int venueid,HttpServletRequest request, HttpServletResponse response) {
        userService.verifyVenue(venueid,true);
        return "success";
    }

    @PostMapping(value = "/not_verify_venue")
    protected @ResponseBody
    String notVerify( @RequestParam("venueid") int venueid,HttpServletRequest request, HttpServletResponse response) {
        userService.verifyVenue(venueid,false);
        return "success";
    }

    @GetMapping(value = "/balance")
    protected ModelAndView openBalance(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("manager/manager_balance");
        List<Venue> venues = userService.getValidVenues();
        mv.addObject("manager",userService.getManager(request.getSession().getAttribute("manager").toString()));
        mv.addObject("venues",JSON.toJSONString(venues));
        return mv;
    }
    @PostMapping(value = "/settle_balance")
    protected @ResponseBody
    String balance( @RequestParam("venueid") int venueid,@RequestParam("rate") double rate, HttpServletRequest request, HttpServletResponse response) {
        String manager = request.getSession().getAttribute("manager").toString();
        return userService.settleBalance(manager,venueid,rate)+"";
    }
    @GetMapping(value = "/venue_statistics")
    protected ModelAndView venueStatistics(HttpServletRequest request, HttpServletResponse response) {
        List<Venue> venues = userService.getValidVenues();
        ModelAndView mv = new ModelAndView("manager/venue_statistics");
        mv.addObject("venues",JSON.toJSONString(venues));
        return mv;
    }
    @GetMapping(value = "/member_statistics")
    protected ModelAndView memberStatistics(HttpServletRequest request, HttpServletResponse response) {
        List<Member> memebers = userService.getValidMembers();
        ModelAndView mv = new ModelAndView("manager/member_statistics");
        mv.addObject("members",JSON.toJSONString(memebers));
        return mv;
    }
    @GetMapping(value = "/tickets_statistics")
    protected ModelAndView ticketsStatistics(HttpServletRequest request, HttpServletResponse response) {
        List<SettleRecord> settleRecords = recordService.getAllSettleRecords();
        ModelAndView mv = new ModelAndView("manager/tickets_statistics");
        mv.addObject("records",JSON.toJSONString(settleRecords));
        return mv;
    }
}
