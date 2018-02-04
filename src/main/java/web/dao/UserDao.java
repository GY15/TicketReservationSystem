package web.dao;

import web.model.User;

/**
 * Created by 61990 on 2017/12/19.
 */
public interface UserDao extends BaseDao {
    /**
     * 获取用户信息
     *
     * @author 61990
     * @updateTime 2017/12/19
     * @param userID 用户ID
     * @return 指定ID的基本数据
     */
    User getUser(String userID);
}
