package web.tag;

import web.model.Order;
import web.model.OrderList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class OrderHandler extends SimpleTagSupport {
    public void doTag() throws JspException, IOException {
        try {
            OrderList orderList =  (OrderList) getJspContext().findAttribute("orderList");
            JspWriter out = getJspContext().getOut();
            for (int i = 0; i < orderList.getOrderList().size(); i++) {
                Order order = orderList.getOrderList(i);

                out.println("<tr>\n" +
                        "   <td>"+ order.getOrderID() +"</td>\n" +
                        "   <td>"+ order.getName()+"</td>\n" +
                        "   <td>"+ order.getNum() +"</td>\n" +
                        "   <td>"+ order.getPrice() +"</td>\n" +
                        "   <td>"+ order.getTime() +"</td>\n" +
                        "   <td>"+ (order.getOutStock()==0?"缺货":"") +"</td>\n" +
                        "</tr>\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new JspException(e.getMessage());
        }
    }

}
