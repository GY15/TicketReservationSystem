package web.dao.daoImpl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import web.dao.UserDao;
import web.entity.Manager;
import web.entity.Member;
import web.entity.ValidUser;
import web.entity.Venue;
import web.utilities.FormatValid;
import web.utilities.HibernateUtil;
import web.utilities.RankUtil;

import java.util.List;

@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    /**
     * 获取用户信息
     *
     * @param email 基本数据的ID
     * @return 用户的基本数据
     * @author 61990
     * @updateTime 2017/2/19
     */
    public Member getMember(String email) {
        Member member = (Member) super.load(Member.class, email);
        return member;
    }
    /**
     * 获取合法的会员信息
     *
     * @author 61990
     * @updateTime 2018/3/7
     * @return 会员基本信息
     */
    public List<Member> getValidMembers(){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        String sql = "SELECT * from member where valid = 1";
        Query query = session.createSQLQuery(sql).addEntity(Member.class);
        List<Member> members= query.list();
        transaction.commit();
        session.close();
        return members;
    }

    /**
     * 更新会员信息
     *
     * @param member 用户
     * @author 61990
     * @updateTime 2017/3/1
     * @return 是否更新用户成功
     */
    public void updateMember(Member member){
        member.setRank(RankUtil.updateRank(member.getCredit()));
        super.update(member);
    }

    /**
     * 获取会员信息
     *
     * @author 61990
     * @updateTime 2017/2/13
     * @param venueid 场馆id
     * @return 指定venue的数据
     */
    public Venue getVenue(int venueid){
        Venue venue = (Venue) super.load(Venue.class, venueid);
        return venue;
    }

    /**
     * 获取场馆未过审核的信息
     *
     * @author 61990
     * @updateTime 2017/3/7
     * @param
     * @return 指定venue的数据
     */
    public List<Venue> getInvalidVenues(){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        String sql = "SELECT * from venue where valid = 0";
        Query query = session.createSQLQuery(sql).addEntity(Venue.class);
        List<Venue> venues= query.list();
        transaction.commit();
        session.close();
        return venues;
    }

    /**
     * 获取过审核venue信息
     *
     * @author 61990
     * @updateTime 2018/3/7
     * @return 场馆基本信息
     */
    public List<Venue> getValidVenues(){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        String sql = "SELECT * from venue where valid = 1";
        Query query = session.createSQLQuery(sql).addEntity(Venue.class);
        List<Venue> venues= query.list();
        transaction.commit();
        session.close();
        return venues;
    }
    /**
     * 获取场馆信息
     *
     * @author 61990
     * @updateTime 2017/2/13
     * @param manager 经理的账号
     * @return 经理的信息
     */
    public Manager getManager(String manager){
        return (Manager) super.load(Manager.class, manager);
    }

    /**

     * 更新经理信息
     *
     * @author 61990
     * @updateTime 2017/2/13
     * @param manager 经理的信息
     * @return 是否充值成功
     */
    public boolean updateManager(Manager manager){
        super.update(manager);
        return true;
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
    /**
     * 验证是否符合验证码，并通过注册
     *
     * @author 61990
     * @updateTime 2018/2/7
     * @param member 用户信息
     * @param valid 用户验证码
     * @return success
     */
    public boolean registerMember(Member member, String valid){
        ValidUser validUser = (ValidUser) super.load(ValidUser.class, member.getEmail());
        if(validUser==null||!validUser.getValid().equals(valid)){
            return false;
        }else{
            super.save(member);
            super.delete(validUser);
            return true;
        }
    }
    /**
     * 创建一个未经过注册的账号,并返回id
     *
     * @author 61990
     * @updateTime 2018/2/12
     * @return success
     */
    public int createVenueId(){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        String sql = "select max(v.venueid) from venue v";
        int id = (Integer)session.createNativeQuery(sql).uniqueResult()+1;
        transaction.commit();
        session.close();
        Venue venue = new Venue();
        venue.setVenueid(id);
        venue.setValid(false);
        super.save(venue);
        return id;
    }
    /**
     * 更新场馆信息
     *
     * @author 61990
     * @updateTime 2018/2/12
     * @return 是否成功
     */
    public boolean updateVenue(Venue venue){
        super.update(venue);
        return true;
    }
    /**
     * 会员充值
     *
     * @author 61990
     * @updateTime 2018/2/23
     * @param email 邮箱
     * @param money 充值金额
     * @return
     */
    public void recharge(String email, int money){
        Member member = (Member) super.load(Member.class, email);
        member.setBalance(member.getBalance()+money);
        super.update(member);
    }
}
