package web.dao;

import web.dao.BaseDao;
import web.model.Coupon;
import web.model.Order;
import web.utilities.enums.OrderState;

import java.util.List;

/**
 * Created by 61990 on 2017/12/19.
 */
public interface CouponDao extends BaseDao {

    /**
     * 获得一个用户的所有用户劵
     *
     * @author 61990
     * @updateTime 2018/2/21
     * @param email 会员邮箱
     * @return coupon 的信息
     */
    List<Coupon> getCoupons(String email);
}
