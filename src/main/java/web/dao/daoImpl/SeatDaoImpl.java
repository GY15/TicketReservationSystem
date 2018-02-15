package web.dao.daoImpl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import web.dao.SeatDao;
import web.model.Plan;
import web.model.SeatMap;
import web.model.Venue;
import web.utilities.HibernateUtil;

import java.util.List;


@Repository
public class SeatDaoImpl extends BaseDaoImpl implements SeatDao {
    /**
     * 添加一个场馆的全部座位信息
     *
     * @author 61990
     * @updateTime 2018/2/13
     * @param seatMaps 座位信息
     * @return 是否成功
     */
    public boolean addSeatMaps(List<SeatMap> seatMaps){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        int venueid = seatMaps.get(0).getVenueid();
        String sql = "DELETE from seat_map WHERE venueid = "+venueid;
        Query query = session.createNativeQuery(sql);

        transaction.commit();
        session.close();
        for(SeatMap seatMap  : seatMaps){
            super.save(seatMap);
        }
        return true;
    }

    /**
     * 获取一个场馆的全部座位信息
     *
     * @author 61990
     * @updateTime 2018/2/13
     * @param venueid 场馆id
     * @return 是否成功
     */
    public List<SeatMap> getSeatMap(int venueid){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        String sql = "SELECT * from seat_map WHERE venueid = "+venueid;
        Query query = session.createSQLQuery(sql).addEntity(SeatMap.class);
        List<SeatMap> seatMaps= query.list();
        transaction.commit();
        return seatMaps;
    }
    /**
     * 发布计划
     *
     * @author 61990
     * @updateTime 2018/2/14
     * @param plan 计划
     * @return 是否成功
     */
    public String createPlan(Plan plan){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        String sql = "select max(p.planid) from plan p";
        int id = (Integer)session.createNativeQuery(sql).uniqueResult()+1;
        plan.setPlanid(id);
        super.save(plan);
        transaction.commit();
        return "success";
    }
}
