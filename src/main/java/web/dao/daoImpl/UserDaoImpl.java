package web.dao.daoImpl;

import web.dao.UserDao;
import web.model.User;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {


    /**
     * 获取用户信息
     *
     * @author 61990
     * @updateTime 2017/12/19
     * @param userID 基本数据的ID
     * @return 用户的基本数据
     */
    public User getUser(String userID) {
        User user = (User)super.load(User.class,userID);
        return user;
    }
}
