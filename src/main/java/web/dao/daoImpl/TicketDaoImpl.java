package web.dao.daoImpl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import web.dao.SeatDao;
import web.dao.TicketDao;
import web.model.Plan;
import web.model.SeatMap;
import web.utilities.HibernateUtil;

import java.util.List;


@Repository
public class TicketDaoImpl extends BaseDaoImpl implements TicketDao {
    /**
     * 添加一个plan的所有座位信息
     *
     * @author 61990
     * @updateTime 2018/2/15
     * @param
     * @return 是否成功
     */
    public boolean addTicket(){

        return true;
    }
}
