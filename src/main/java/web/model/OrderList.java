package web.model;

import java.io.Serializable;
import java.util.List;

public class OrderList implements Serializable {
    private List<Order> orderList;


    public List getOrderList() {
        return orderList;
    }


    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public Order getOrderList(int index) {
        return orderList.get(index);
    }
}
