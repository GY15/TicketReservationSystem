package web.tag;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class CheckSessionHandler extends TagSupport {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Override
    public int doStartTag() throws JspException {
        int result=0;
        System.out.println("我执行了tag");
        HttpSession session=pageContext.getSession();
        String userID=(String)session.getAttribute("userID");
        if(userID==null){
            result=SKIP_BODY;
            try {
                System.out.println("我跳转了");
                HttpServletResponse response=(HttpServletResponse)pageContext.getResponse();
                response.sendRedirect("/home");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            result=EVAL_BODY_INCLUDE;
        }
        return result;
    }
}
