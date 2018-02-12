package web.service;

import web.model.Member;
import web.utilities.enums.MemberState;

/**
 * 管理用户登录
 */
public interface UserService {

    boolean login(String userID, String password);
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
}
