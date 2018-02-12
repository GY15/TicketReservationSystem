package web.dao;

import web.model.Member;
import web.model.Venue;

/**
 * Created by 61990 on 2017/12/19.
 */
public interface UserDao extends BaseDao {
    /**
     * 获取用户信息
     *
     * @author 61990
     * @updateTime 2017/12/19
     * @param email 用户email
     * @return 指定email的数据
     */
    Member getMember(String email);
    /**
     * 保存验证信息
     *
     * @author 61990
     * @updateTime 2018/2/7
     * @param email 用户email
     * @return success
     */
    String getValidNum(String email);
    /**
     * 验证是否符合验证码，并通过注册
     *
     * @author 61990
     * @updateTime 2018/2/7
     * @param member 用户信息
     * @param valid 用户验证码
     * @return success
     */
    boolean registerMember(Member member, String valid);
    /**
     * 创建一个未经过注册的账号,并返回id
     *
     * @author 61990
     * @updateTime 2018/2/12
     * @return success
     */
    int createVenueId();

    /**
     * 更新场馆信息
     *
     * @author 61990
     * @updateTime 2018/2/12
     * @return 是否成功
     */
    boolean updateVenue(Venue venue);
}
