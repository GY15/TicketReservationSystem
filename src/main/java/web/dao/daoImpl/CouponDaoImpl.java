package web.dao.daoImpl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import web.dao.CouponDao;
import web.entity.Coupon;
import web.utilities.HibernateUtil;

import java.util.List;


@Repository
public class CouponDaoImpl extends BaseDaoImpl implements CouponDao {
    /**
     * 获得一个用户的所有用户劵
     *
     * @author 61990
     * @updateTime 2018/2/21
     * @param email 会员邮箱
     * @return coupon 的信息
     */
    public List<Coupon> getCoupons(String email){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        String sql = "SELECT * from coupon where email = '"+email+"' and valid = 1";
        Query query = session.createSQLQuery(sql).addEntity(Coupon.class);
        List<Coupon> coupons= query.list();
        transaction.commit();
        session.close();
        return coupons;
    }
}
