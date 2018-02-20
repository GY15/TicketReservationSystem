package web.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.dao.daoImpl.UserDaoImpl;
import web.model.Member;
import web.model.Venue;
import web.service.UserService;
import web.utilities.MailUtil;
import web.utilities.enums.MemberState;
import web.utilities.enums.UserType;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
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
    public boolean login(String userID, String password, UserType userType){
        String realPassword = "";
        if(userType.equals(UserType.MEMBER)){
            Member member = userDao.getMember(userID);
            if (member==null){
                return false;
            }
            realPassword = member.getPassword();
        }else if(userType.equals(UserType.VENUE)){
            Venue venue = userDao.getVenue(Integer.parseInt(userID));
            if (venue==null){
                return false;
            }
            realPassword = venue.getPassword();
        }else{

        }
        if (realPassword == ""||!realPassword.equals(password)) {
            return false;
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
     * 查看是否已经注册，否则生成并发送验证码
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
     * 获取指定客户的折扣
     *
     * @author 61990
     * @updateTime 2018/2/18
     * @return 是否成功
     */
    public double getDiscount(String email){
        return 0.8;
    }
}
