package web.dao;

import web.model.Member;
import web.model.Venue;

/**
 * Created by 61990 on 2017/12/19.
 */
public interface UserDao extends BaseDao {
    /**
     * 获取会员信息
     *
     * @param email 用户email
     * @return 指定email的数据
     * @author 61990
     * @updateTime 2017/2/13
     */
    Member getMember(String email);

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
