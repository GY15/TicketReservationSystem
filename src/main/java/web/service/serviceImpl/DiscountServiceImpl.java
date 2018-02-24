package web.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.*;
import web.model.*;
import web.service.DiscountService;
import web.service.OrderService;
import web.utilities.enums.OrderState;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {
    @Autowired
    CouponDao couponDao;
    @Autowired
    UserDao userDao;
//    0-7级的会员所享受的优惠
    Double[] discount = new Double[]{1.0,0.95,0.9,0.85,0.8,0.8,0.75,0.7};
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
        return discount[member.getRank()];
    }
}
