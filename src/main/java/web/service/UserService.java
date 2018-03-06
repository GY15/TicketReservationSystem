package web.service;

import web.entity.Coupon;
import web.entity.Member;
import web.entity.Venue;
import web.utilities.enums.MemberState;
import web.utilities.enums.UserType;

/**
 * 管理用户登录
 */
public interface UserService {
    /**
     * 用户登录
     *
     * @author 61990
     * @updateTime 2018/2/7
     * @param userID id
     * @param password 密码
     * @param userType 登录类型
     * @return 是否成功
     */
    boolean login(String userID, String password, UserType userType);
    /**
     * 查看是否已经注册，否则生成并发送验证码
     *
     * @author 61990
     * @updateTime 2018/2/7
     * @param email 注册的电子邮件
     * @return 是否成功
     */
    MemberState validEmail(String email);
    /**
     * 查看是否已经注册，否则生成并发送验证码
     *
     * @author 61990
     * @updateTime 2018/2/8
     * @param member 注册的会员信息
     * @param valid 验证码
     * @return 是否成功
     */
    boolean registerMember(Member member, String valid);
    /**
     * 自动生成场馆的识别码
     *
     * @author 61990
     * @updateTime 2018/2/10
     * @return 是否成功
     */
    int createVenueId();

    /**
     * 修改场馆信息
     *
     * @author 61990
     * @updateTime 2018/2/12
     * @return 是否成功
     */
    boolean modifyVenueMessage(Venue venue);

    /**
     * 修改会员信息
     *
     * @author 61990
     * @updateTime 2018/2/12
     * @return 是否成功
     */
    boolean modifyMemberMessage(Member member);

    /**
     * 获取venue 信息
     *
     * @author 61990
     * @updateTime 2018/2/18
     * @return 场馆基本信息
     */
    Venue getVenueInfo(int venueid);

    /**
     * 获取member信息
     *
     * @author 61990
     * @updateTime 2018/2/23
     * @return  会员基本信息
     */
    Member getMember(String email);

    /**
     * 会员充值
     *
     * @author 61990
     * @updateTime 2018/2/23
     * @param email 邮箱
     * @param money 充值金额
     * @return
     */
    void recharge(String email, int money);
    /**
     * 取消会员资格
     *
     * @author 61990
     * @updateTime 2018/3/1
     * @param email 邮箱
     * @return null
     */
    void cancelMember(String email);

    /**
     * 会员消费
     *
     * @author 61990
     * @updateTime 2018/3/1
     * @param email 邮箱
     * @param money 消费金额
     * @return null
     */
    void consume(String email, double money);

    /**
     * 余额退款
     *
     * @author 61990
     * @updateTime 2018/3/1
     * @param email 邮箱
     * @param money 消费金额
     * @return null
     */
    void refund(String email, double money);

    /**
     * 获得账户余额
     *
     * @author 61990
     * @updateTime 2018/3/1
     * @param email 邮箱
     * @param money 消费金额
     * @return null
     */
    double getBalance(String email);

    /**
     * 积分兑换优惠券
     *
     * @author 61990
     * @updateTime 2018/3/6
     * @param coupon 优惠券信息
     * @param grade 所需要的积分
     * @return 是否兑换成功
     */
    boolean switchCoupon(Coupon coupon, int grade);
}
