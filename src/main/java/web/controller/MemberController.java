package web.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.Member;
import web.model.PlanGeneral;
import web.service.PlanService;
import web.service.UserService;
import web.utilities.enums.MemberState;
import web.utilities.enums.UserType;

import javax.servlet.ServletContext;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.ParseException;
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
    protected ModelAndView myPlan(HttpServletRequest request, HttpServletResponse response) {
        List<PlanGeneral> planGenerals = planService.getPlanGeneral();
        ModelAndView mv = new ModelAndView("member_plan");
        mv.addObject("plans", planGenerals);
        return mv;
    }

    @PostMapping(value = "/open_plan_detail")
    protected ModelAndView openDetail(@RequestParam("planid") int planid,@RequestParam("selectpicker") String block) {
        PlanGeneral planGeneral = planService.getPlan(planid);
        ModelAndView mv = new ModelAndView("member_buy");
        mv.addObject("planJson", JSON.toJSONString(planGeneral));
        mv.addObject("plan", planGeneral);
        mv.addObject("block", block);
        return mv;
    }


}
