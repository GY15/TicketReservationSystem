package web.service;

import web.model.Member;
import web.model.Venue;
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
     * 在场馆购票时，获取指定客户的折扣
     *
     * @author 61990
     * @updateTime 2018/2/18
     * @return 是否成功
     */
    double getDiscount(String email);
    /**
     * 获取venue 信息
     *
     * @author 61990
     * @updateTime 2018/2/18
     * @return 场馆基本信息
     */
    Venue getVenueInfo(int venueid);
}
