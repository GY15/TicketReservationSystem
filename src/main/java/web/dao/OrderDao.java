package web.dao;

import web.model.Order;

import java.util.List;

/**
 * Created by 61990 on 2017/12/19.
 */
public interface OrderDao extends BaseDao {
    public void save(Object bean);
    public void delete(Object bean) ;
    public Object load(Class c, String id) ;

    /**
     * 获取指定ID的所有订单数目
     *
     * @author 61990
     * @updateTime 2017/12/19
     * @param userID 用户id
     * @return 指定ID的所有订单数目
     */
    int getNumOfOrders(String userID);
    /**
     * 获取指定ID的某一页订单
     *
     * @author 61990
     * @updateTime 2017/12/19
     * @param userID 用户id
     * @param start 开始条数
     * @param num  需要条数
     * @return 指定ID的某一页订单
     */
    List<Order> getOrdersOfUser(String userID, int start, int num);
}
