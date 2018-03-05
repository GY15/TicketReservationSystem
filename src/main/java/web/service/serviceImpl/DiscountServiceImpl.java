package web.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.*;
import web.entity.Coupon;
import web.entity.Member;
import web.service.DiscountService;
import web.utilities.RankUtil;

import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {
    @Autowired
    CouponDao couponDao;
    @Autowired
    UserDao userDao;
    /**
     * 获得一个用户的所有用户劵
     *
     * @author 61990
     * @updateTime 2018/2/21
     * @param email 会员邮箱
     * @return coupon 的信息
     */
    public List<Coupon> getCoupons(String email){
       return couponDao.getCoupons(email);
    }
    /**
     * 在场馆购票时，获取指定客户的折扣
     *
     * @author 61990
     * @updateTime 2018/2/18
     * @return 会员折扣
     */
    public double getDiscount(String email){
        Member member = userDao.getMember(email);
        return RankUtil.discount[member.getRank()];
    }
}
