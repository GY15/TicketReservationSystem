package web.dao.daoImpl;

import org.springframework.stereotype.Repository;
import web.dao.UserDao;
import web.model.Member;
import web.model.ValidUser;
import web.utilities.FormatValid;

@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    /**
     * 获取用户信息
     *
     * @param userID 基本数据的ID
     * @return 用户的基本数据
     * @author 61990
     * @updateTime 2017/12/19
     */
    public Member getMember(String email) {
        Member member = (Member) super.load(Member.class, email);
        return member;
    }

    /**
     * 保存验证信息
     *
     * @param email 用户email
     * @return valid number
     * @author 61990
     * @updateTime 2018/2/7
     */
    public String getValidNum(String email) {
        ValidUser validUser = (ValidUser) super.load(ValidUser.class, email);
        if (validUser == null) {
            validUser = new ValidUser();
            validUser.setEmail(email);
            validUser.setValid(FormatValid.createValid());
            super.save(validUser);
        }
        return validUser.getValid();
    }
}
