package web.dao;

import web.entity.Manager;
import web.entity.Member;
import web.entity.Venue;

import java.util.List;

/**
 * Created by 61990 on 2017/12/19.
 */
public interface UserDao extends BaseDao {
    /**
     * 获取会员信息
     *
     * @param email 用户email
     * @author 61990
     * @updateTime 2017/2/13
     * @return 指定email的数据
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
     * 更新会员信息
     *
     * @param email 用户email
     * @author 61990
     * @updateTime 2017/3/1
     * @return 是否更新用户成功
     */
    void updateMember(Member member);

    /**
     * 获取场馆信息
     *
     * @param venueid venueid
     * @return 指定venue的数据
     * @author 61990
     * @updateTime 2017/2/13
     */
    Venue getVenue(int venueid);

    /**
     * 获取场馆未过审核的信息
     *
     * @param
     * @return 指定venue的数据
     * @author 61990
     * @updateTime 2017/3/7
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
     * 获取场馆信息
     *
     * @author 61990
     * @updateTime 2017/2/13
     * @param manager 经理的账号
     * @return 经理的信息
     */
    Manager getManager(String manager);

    /**
     * 更新经理信息
     *
     * @author 61990
     * @updateTime 2017/2/13
     * @param manager 经理的信息
     * @return 是否充值成功
     */
    boolean updateManager(Manager manager);

    /**
     * 保存验证信息
     *
     * @param email 用户email
     * @return success
     * @author 61990
     * @updateTime 2018/2/7
     */
    String getValidNum(String email);

    /**
     * 验证是否符合验证码，并通过注册
     *
     * @param member 用户信息
     * @param valid  用户验证码
     * @return success
     * @author 61990
     * @updateTime 2018/2/7
     */
    boolean registerMember(Member member, String valid);

    /**
     * 创建一个未经过注册的账号,并返回id
     *
     * @return success
     * @author 61990
     * @updateTime 2018/2/12
     */
    int createVenueId();

    /**
     * 更新场馆信息
     *
     * @return 是否成功
     * @author 61990
     * @updateTime 2018/2/12
     */
    boolean updateVenue(Venue venue);

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
}
