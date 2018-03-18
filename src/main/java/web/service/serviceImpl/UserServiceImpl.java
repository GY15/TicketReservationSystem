package web.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.CouponDao;
import web.dao.UserDao;
import web.entity.*;
import web.service.RecordService;
import web.service.UserService;
import web.utilities.MailUtil;
import web.utilities.enums.MemberState;
import web.utilities.enums.UserType;
import web.utilities.exceptions.MemberInvalidExistException;
import web.utilities.exceptions.PasswordWrongException;
import web.utilities.exceptions.UserNotExistException;


import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    CouponDao couponDao;
    @Autowired
    RecordService recordService;
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
    public boolean login(String userID, String password, UserType userType) throws UserNotExistException, MemberInvalidExistException, PasswordWrongException {
        String realPassword;
        if(userType.equals(UserType.MEMBER)){
            Member member = userDao.getMember(userID);
            if (member==null){
                throw new UserNotExistException();
            }
            if (!member.isValid()){
                throw new MemberInvalidExistException();
            }
            realPassword = member.getPassword();
        }else if(userType.equals(UserType.VENUE)){
            Venue venue = userDao.getVenue(Integer.parseInt(userID));
            if (venue==null){
                throw new UserNotExistException();
            }
            realPassword = venue.getPassword();
        }else{
            Manager manager = userDao.getManager(userID);
            if (manager==null){
                throw new UserNotExistException();
            }
            realPassword = manager.getPassword();
        }
        if (realPassword == ""||!realPassword.equals(password)) {
            throw new PasswordWrongException();
        } else {
           return true;
        }
    }
    /**
     * 查看是否已经注册，否则生成并发送验证码
     *
     * @author 61990
     * @updateTime 2018/2/7
     * @param email 注册的电子邮件
     * @return 是否成功
     */
    public MemberState validEmail(String email) {
        Member member = userDao.getMember(email);
        if(member==null) {
            String valid = userDao.getValidNum(email);
            try {
                MailUtil.sendMail(email, valid);
            } catch (Exception e) {
                //发送失败
                return MemberState.ERROR_MAIL;
            }
            //发送成功
            return MemberState.ALLOWED;
        }else {
            //已经被注册
            if (!member.isValid()) {
                return MemberState.LOCKED;
            } else {
                return MemberState.REGISTERED;
            }
        }
    }

    /**
     * 注册
     *
     * @author 61990
     * @updateTime 2018/2/8
     * @param member 注册的会员信息
     * @param valid 验证码
     * @return 是否成功
     */
    public boolean registerMember(Member member, String valid){
        return userDao.registerMember(member,valid);
    }
    /**
     * 自动生成场馆的识别码
     *
     * @author 61990
     * @updateTime 2018/2/10
     * @return 是否成功
     */
    public int createVenueId(){
        return userDao.createVenueId();
    }
    /**
     * 修改场馆信息
     *
     * @author 61990
     * @updateTime 2018/2/12
     * @return 是否成功
     */
    public boolean modifyVenueMessage(Venue venue){
        return userDao.updateVenue(venue);
    }

    /**
     * 修改会员信息
     *
     * @author 61990
     * @updateTime 2018/2/12
     * @return 是否成功
     */
    public boolean modifyMemberMessage(Member member){
        userDao.updateMember(member);
        return true;
    }

    /**
     * 获取venue 信息
     *
     * @author 61990
     * @updateTime 2018/2/18
     * @return 场馆基本信息
     */
    public Venue getVenueInfo(int venueid){
        return userDao.getVenue(venueid);
    }

    /**
     * 获取为过审核venue信息
     *
     * @author 61990
     * @updateTime 2018/3/7
     * @return 场馆基本信息
     */
    public List<Venue> getInvalidVenues(){
        return userDao.getInvalidVenues();
    }

    /**
     * 获取过审核venue信息
     *
     * @author 61990
     * @updateTime 2018/3/7
     * @return 场馆基本信息
     */
    public List<Venue> getValidVenues(){
        return userDao.getValidVenues();
    }

    /**
     * 获取member信息
     *
     * @author 61990
     * @updateTime 2018/2/23
     * @return  会员基本信息
     */
    public Member getMember(String email){
        return userDao.getMember(email);
    }
    /**
     * 获取合法的会员信息
     *
     * @author 61990
     * @updateTime 2018/3/7
     * @return 会员基本信息
     */
    public List<Member> getValidMembers(){
       return userDao.getValidMembers();
    }

    /**
     * 会员充值
     *
     * @author 61990
     * @updateTime 2018/2/23
     * @param email 邮箱
     * @param money 充值金额
     * @return
     */
    public void recharge(String email, int money){
        userDao.recharge(email,money);
    }
    /**
     * 取消会员资格
     *
     * @author 61990
     * @updateTime 2018/3/1
     * @param email 邮箱
     * @return null
     */
    public void cancelMember(String email){
        Member member = userDao.getMember(email);
        member.setValid(false);
        userDao.updateMember(member);
    }
    /**
     * 会员消费
     *
     * @author 61990
     * @updateTime 2018/3/1
     * @param email 邮箱
     * @param money 消费金额
     * @return 是否订单成功
     */
    public boolean consume(String email,int venueid, double money){
        return handleBalance(email,venueid,money,false);
    }
    /**
     * 余额退款
     *
     * @author 61990
     * @updateTime 2018/3/1
     * @param email 邮箱
     * @param money 消费金额
     * @return null
     */
    public void refund(String email,int venueid ,double money){
        handleBalance(email,venueid,money,true);
    }
    /**
     * 处理退款和订单支付
     *
     * @author 61990
     * @updateTime 2018/3/1
     * @param email 邮箱
     * @param money 消费金额
     * @return null
     */
    private boolean handleBalance(String email, int venueid, double money, boolean isFund){
        money = isFund ? -money : money;
        Member member = userDao.getMember(email);
        Venue venue = userDao.getVenue(venueid);
        if( (!isFund) && member.getBalance() < money ){
            return false;
        }
        else {
            member.setBalance(member.getBalance()-money);
            member.setCredit(member.getCredit()+ new Double(money).intValue());
            member.setGrade(member.getGrade()+ new Double(money).intValue());
            userDao.updateMember(member);
            venue.setUnliquidated(venue.getUnliquidated()+money);
            venue.setEarning(venue.getEarning()+money);
            userDao.updateVenue(venue);
            recordService.createConsumeRecord(new ConsumeRecord(email,venueid,Math.abs(money),isFund));
            return true;
        }
    }

    /**
     * 获得账户余额
     *
     * @author 61990
     * @updateTime 2018/3/1
     * @param email 邮箱
     * @return null
     */
    public double getBalance(String email){
        return userDao.getMember(email).getBalance();
    }
    /**
     * 积分兑换优惠券
     *
     * @author 61990
     * @updateTime 2018/3/6
     * @param coupon 优惠券信息
     * @param grade 所需要的积分
     * @return 是否兑换成功
     */
    public boolean switchCoupon(Coupon coupon, int grade){
        Member member = userDao.getMember(coupon.getEmail());
        if (member.getGrade()<grade){
            return false;
        }else {
            member.setGrade(member.getGrade()-grade);
            userDao.updateMember(member);
            couponDao.create(coupon);
            return true;
        }
    }

    /**
     * 获取场馆信息
     *
     * @author 61990
     * @updateTime 2017/2/13
     * @param manager 经理的账号
     * @return 经理的信息
     */
    public Manager getManager(String manager){
        return userDao.getManager(manager);
    }

    /**
     * 处理结算
     *
     * @author 61990
     * @updateTime 2017/2/13
     * @param manager 经理的账号
     * @param venueid 场馆id
     * @param rate 结算比例
     * @return 是否充值成功
     */
    public double settleBalance(String manager,int venueid,double rate){
        Manager manager1 =  userDao.getManager(manager);
        Venue venue = getVenueInfo(venueid);
        double profit =venue.getUnliquidated()*(1-rate);
        venue.setBalance(venue.getBalance()+venue.getUnliquidated()-profit);
        venue.setUnliquidated(0);
        manager1.setBalance(manager1.getBalance()+profit);
        recordService.createSettleRecord(new SettleRecord(manager,venueid,rate,profit));
        if(userDao.updateVenue(venue)&& userDao.updateManager(manager1)){
            return rate;
        }else {
            return 0;
        }
    }
    /**
     * 通过场馆审核
     *
     * @author 61990
     * @updateTime 2017/2/13
     * @param venueid 场馆的账户
     * @return 是否充值成功
     */
    public void verifyVenue(int venueid){
        Venue venue = userDao.getVenue(venueid);
        venue.setValid(true);
        userDao.updateVenue(venue);
    }
}

