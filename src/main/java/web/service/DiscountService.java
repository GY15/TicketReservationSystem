package web.service;

import web.model.Coupon;
import web.model.OrderGeneral;
import web.utilities.enums.OrderState;

import java.util.List;

/**
 * 管理用户登录
 */
public interface DiscountService {
    /**
     * 获得一个用户的所有用户劵
     *
     * @author 61990
     * @updateTime 2018/2/21
     * @param email 会员邮箱
     * @return coupon 的信息
     */
    List<Coupon> getCoupons(String email);
    /**
     * 在场馆购票时，获取指定客户的折扣
     *
     * @author 61990
     * @updateTime 2018/2/18
     * @param email 会员邮箱
     * @return 折扣
     */
    double getDiscount(String email);
}
