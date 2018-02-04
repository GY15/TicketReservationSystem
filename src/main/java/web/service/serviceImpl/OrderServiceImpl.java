package web.service.serviceImpl;

import org.springframework.stereotype.Service;
import web.dao.OrderDao;
import web.dao.daoImpl.OrderDaoImpl;
import web.model.Order;
import web.service.OrderService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    OrderDao orderDao = new OrderDaoImpl();
    public List<Order> getOrdersOfThePage(String userID, int start, int num) {
        return orderDao.getOrdersOfUser(userID,start,num);
    }

    public int getNumOfOrders(String userID) {
        return orderDao.getNumOfOrders(userID);
    }
}
