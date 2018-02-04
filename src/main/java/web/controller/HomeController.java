package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Home
 */
@Controller
public class HomeController extends HttpServlet {
    @Autowired
    ServletContext Context;

	private static final long serialVersionUID = 1L;

    @GetMapping(value = "/home")
	protected String getHome(HttpServletRequest request) {

		String login="";
		HttpSession session = request.getSession(false);
		Cookie cookie = null;

//        ServletContext Context= getServletContext();
        int allCounter = Integer.parseInt((String) Context.getAttribute("allCounter"));
        int loginCounter = Integer.parseInt((String) Context.getAttribute("loginCounter"));

        if(session==null) {
            request.getSession(true);
            System.out.println("create session");
            allCounter++;
            Context.setAttribute("allCounter", Integer.toString(allCounter));
        }else{
            if(session.getAttribute("userID")!=null){
                loginCounter--;
                session.removeAttribute("userID");
                Context.setAttribute("loginCounter", Integer.toString(loginCounter));
            }
        }



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
        request.setAttribute("login",login);
        request.setAttribute("allCounter",allCounter);
        request.setAttribute("loginCounter",loginCounter);
        return "login";

	}
}
