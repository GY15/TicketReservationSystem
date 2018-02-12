package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import web.model.Member;
import web.service.UserService;

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

    @GetMapping(value = "/")
    protected String getHome(HttpServletRequest request) {


        HttpSession session = request.getSession(true);
        Cookie cookie = null;

        Cookie[] cookies = request.getCookies();


        session.setAttribute("type","init");
        return "venue_home";
    }

    @PostMapping(value = "/login")
    protected String login(HttpServletRequest request, HttpServletResponse response) {

        return null;
    }

    @GetMapping(value = "/logout")
    protected String logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);//防止创建Session
        return "";
    }

    @PostMapping(value = "/valid", produces = "text/html;charset=UTF-8;")
    protected @ResponseBody
    String valid(){
        return userService.createVenueId()+"";
    }

    @PostMapping(value = "/registerMember")
    protected String registerMember(@RequestParam("reg_email") String reg_email,@RequestParam("valid_word") String valid_word,
                                    @RequestParam("reg_nickname") String reg_nickname,@RequestParam("reg_password") String reg_password,
                                    @RequestParam("reg_password2") String reg_password2,
                                    HttpServletRequest request, HttpServletResponse response) {

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
        return "member_home";
    }

}
