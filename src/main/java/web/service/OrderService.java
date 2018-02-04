package web.service;

import web.model.Order;

import java.util.List;

/**
 * 关于订单管理的service
 */
public interface OrderService {
    List<Order> getOrdersOfThePage(String userID, int start, int num);
    int getNumOfOrders(String userID);
}
