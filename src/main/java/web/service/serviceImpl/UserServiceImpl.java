package web.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.dao.daoImpl.UserDaoImpl;
import web.model.Member;
import web.service.UserService;
import web.utilities.MailUtil;
import web.utilities.enums.MemberState;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao = new UserDaoImpl();
    public boolean login(String userID, String password) {
        Member user = userDao.getMember(userID);
        if (user == null||!user.getPassword().equals(password)) {
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
}
