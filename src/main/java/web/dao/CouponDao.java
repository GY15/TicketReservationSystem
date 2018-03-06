package web.dao;

import web.entity.Coupon;

import java.util.List;

/**
 * Created by 61990 on 2017/12/19.
 */
public interface CouponDao extends BaseDao {

    /**
     * 获得一个用户的所有优惠劵
     *
     * @author 61990
     * @updateTime 2018/2/21
     * @param email 会员邮箱
     * @return coupon 的信息
     */
    List<Coupon> getCoupons(String email);
    /**
     * 生成一个优惠券
     *
     * @author 61990
     * @updateTime 2018/3/6
     * @param coupon 优惠券信息
     * @return 是否生成成功
     */
    boolean create(Coupon coupon);
    /**
     * 更新优惠券状态
     *
     * @author 61990
     * @updateTime 2018/3/6
     * @param couponid 优惠券id
     * @return coupon 的信息
     */
    boolean updateCoupon(int couponid,boolean state);
}
