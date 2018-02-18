package web.dao.daoImpl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import web.dao.PlanDao;
import web.dao.SeatDao;
import web.model.Plan;
import web.model.SeatMap;
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
        super.save(plan);
        transaction.commit();
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
}
