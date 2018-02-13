package web.dao.daoImpl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import web.dao.SeatDao;
import web.model.SeatMap;
import web.utilities.HibernateUtil;


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
    public boolean addSeatMaps(SeatMap[] seatMaps){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        int venueid = seatMaps[0].getVenueid();
        String sql = "DELETE from seat_map WHERE venueid = "+venueid;
        Query query = session.createNativeQuery(sql);
        transaction.commit();
        for(int i = 0; i < seatMaps.length; i++){
            super.save(seatMaps[i]);
        }
        return true;
    }

}
