package web.dao.daoImpl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import web.dao.PlanDao;
import web.entity.Plan;
import web.utilities.HibernateUtil;

import java.util.List;


@Repository
public class PlanDaoImpl extends BaseDaoImpl implements PlanDao {
    /**
     * 发布计划
     *
     * @author 61990
     * @updateTime 2018/2/14
     * @param plan 计划
     * @return planid
     */
    public int createPlan(Plan plan){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        String sql = "select max(p.planid) from plan p";
        int id = (Integer)session.createNativeQuery(sql).uniqueResult()+1;
        plan.setPlanid(id);
        transaction.commit();
        session.close();
        super.save(plan);
        return id;
    }
    /**
     * 获得指定id所有plan
     *
     * @author 61990
     * @updateTime 2018/2/16
     * @param venueid 场馆id
     * @return list<plan>
     */
    public List<Plan> getPlan(int venueid){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        String sql = "SELECT * from plan WHERE venueid = "+venueid;
        Query query = session.createSQLQuery(sql).addEntity(Plan.class);
        List<Plan> plans= query.list();
        transaction.commit();
        session.close();
        return plans;
    }
    /**
     * 获得指定id 的计划
     *
     * @author 61990
     * @updateTime 2018/2/18
     * @param planid 计划的id
     * @return 计划详情
     */
    public Plan getOnePlan(int planid){
        return (Plan) super.load(Plan.class, planid);
    }
    /**
     * 获得指定所有plan
     *
     * @author 61990
     * @updateTime 2018/2/22
     * @return list<plan>
     */
    public List<Plan> getAllPlan(){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        String sql = "SELECT * from plan";
        Query query = session.createSQLQuery(sql).addEntity(Plan.class);
        List<Plan> plans= query.list();
        transaction.commit();
        session.close();
        return plans;
    }
}
