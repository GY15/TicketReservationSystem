package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import web.model.Member;
import web.service.UserService;
import web.utilities.enums.MemberState;
import web.utilities.enums.UserType;

import javax.servlet.ServletContext;
import javax.servlet.http.*;
import java.io.IOException;

@Controller
@RequestMapping("/member")
public class MemberController extends HttpServlet {
    @Autowired
    ServletContext Context;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    protected String getHome(HttpServletRequest request) {

        String login="";
        HttpSession session = request.getSession(true);
        Cookie cookie = null;

        Cookie[] cookies = request.getCookies();

        if (null != cookies) {
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                if (cookie.getName().equals("loginID")) {
                    login=cookie.getValue();
                    break;
                }
            }
        }
        session.setAttribute("type","init");
        return "member_login";
    }

    @PostMapping(value = "/login")
    protected String login(HttpServletRequest request, HttpServletResponse response) {

        String loginID = request.getParameter("id");
        String remember = request.getParameter("remember");

        HttpSession session = request.getSession(false);

        if (remember != null) {
            Cookie myCookie = new Cookie("loginID", loginID);
            response.addCookie(myCookie);
            myCookie.setMaxAge(60 * 60 * 24);
        }

        if (userService.login(loginID, request.getParameter("password"), UserType.MEMBER)) {
            if (session == null) {
                session = request.getSession(true);
                session.setAttribute("userID", loginID);
            } else {
                session.setAttribute("userID", loginID);
            }

            int loginCounter = Integer.parseInt((String) Context.getAttribute("loginCounter"));
            loginCounter++;
            Context.setAttribute("loginCounter", Integer.toString(loginCounter));

            try {
                response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/order?curPage=1"));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            request.setAttribute("errorMessage", "账号或者密码错误");
            return "errorPage";
        }
        return null;
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
        return "member_home";
    }

}
