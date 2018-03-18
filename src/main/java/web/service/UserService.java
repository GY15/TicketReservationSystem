package web.service;

import web.entity.Coupon;
import web.entity.Manager;
import web.entity.Member;
import web.entity.Venue;
import web.utilities.enums.MemberState;
import web.utilities.enums.UserType;
import web.utilities.exceptions.MemberInvalidExistException;
import web.utilities.exceptions.PasswordWrongException;
import web.utilities.exceptions.UserNotExistException;

import java.util.List;

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
    boolean login(String userID, String password, UserType userType) throws UserNotExistException, MemberInvalidExistException, PasswordWrongException;
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
     * 注册
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
     * 获取为过审核venue信息
     *
     * @author 61990
     * @updateTime 2018/3/7
     * @return 场馆基本信息
     */
    List<Venue> getInvalidVenues();
    /**
     * 获取过审核venue信息
     *
     * @author 61990
     * @updateTime 2018/3/7
     * @return 场馆基本信息
     */
     List<Venue> getValidVenues();
    /**
     * 获取member信息
     *
     * @author 61990
     * @updateTime 2018/2/23
     * @return  会员基本信息
     */
    Member getMember(String email);

    /**
     * 获取合法的会员信息
     *
     * @author 61990
     * @updateTime 2018/3/7
     * @return 会员基本信息
     */
    List<Member> getValidMembers();

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
     * @param venueid 场馆id
     * @param money 消费金额
     * @return 是否订单成功
     */
    boolean consume(String email,int venueid, double money);

    /**
     * 余额退款
     *
     * @author 61990
     * @updateTime 2018/3/1
     * @param email 邮箱
     * @param venueid 场馆id
     * @param money 消费金额
     * @return null
     */
    void refund(String email,int venueid, double money);

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

    /**
     * 获取场馆信息
     *
     * @author 61990
     * @updateTime 2017/2/13
     * @param manager 经理的账号
     * @return 经理的信息
     */
    Manager getManager(String manager);

    /**
     * 处理结算
     *
     * @author 61990
     * @updateTime 2017/2/13
     * @param manager 经理的账号
     * @param money 充值金额
     * @return 经理获得的收益
     */
    double settleBalance(String manager,int venueid,double rate);
    /**
     * 通过场馆审核
     *
     * @author 61990
     * @updateTime 2017/2/13
     * @param venueid 场馆的账户
     * @return 是否充值成功
     */
    void verifyVenue(int venueid);
}
