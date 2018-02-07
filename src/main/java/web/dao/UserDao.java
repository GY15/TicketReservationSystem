package web.dao;

import web.model.Member;

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
}
