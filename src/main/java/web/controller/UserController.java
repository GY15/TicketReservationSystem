package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import web.service.UserService;
import web.utilities.enums.MemberState;

import javax.servlet.ServletContext;
import javax.servlet.http.*;
import java.io.IOException;

@Controller
public class UserController extends HttpServlet {
    @Autowired
    ServletContext Context;
    @Autowired
    private UserService userService;

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

        if (userService.login(loginID, request.getParameter("password"))) {
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

}
