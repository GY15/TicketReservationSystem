package web.dao.daoImpl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import web.dao.RecordDao;
import web.dao.TicketDao;
import web.entity.ConsumeRecord;
import web.entity.SettleRecord;
import web.entity.Ticket;
import web.service.RecordService;
import web.utilities.HibernateUtil;

import java.util.ArrayList;
import java.util.List;


@Repository
public class RecordDaoImpl extends BaseDaoImpl implements RecordDao {
    /**
     * 生成一条结算数据
     *
     * @author 61990
     * @updateTime 2017/3/8
     * @param settleRecord 生成的记录信息
     * @return 是否充值成功
     */
    public boolean createSettleRecord(SettleRecord settleRecord){
        super.save(settleRecord);
        return true;
    }
    /**
     * 获得所有的结算数据
     *
     * @author 61990
     * @updateTime 2017/3/8
     * @return 所有
     */
    public List<SettleRecord> getAllSettleRecords(){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        String sql = "SELECT * from settle_record";
        Query query = session.createSQLQuery(sql).addEntity(SettleRecord.class);
        List<SettleRecord> settleRecords= query.list();
        transaction.commit();
        session.close();
        return settleRecords;
    }

    /**
     * 生成一条消费数据
     *
     * @author 61990
     * @updateTime 2017/3/8
     * @param consumeRecord 生成的记录信息
     * @return 是否成功
     */
    public boolean createConsumeRecord(ConsumeRecord consumeRecord){
        super.save(consumeRecord);
        return true;
    }
    /**
     * 获得会员所有的消费数据
     *
     * @author 61990
     * @updateTime 2017/3/8
     * @return 用户的所有记录
     */
    public List<ConsumeRecord> getMemberConsumeRecords(String email){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        String sql = "SELECT * from consume_record where email = '"+email+"'";
        Query query = session.createSQLQuery(sql).addEntity(ConsumeRecord.class);
        List<ConsumeRecord> consumeRecords= query.list();
        transaction.commit();
        session.close();
        return consumeRecords;
    }

    /**
     * 获得所有的消费数据
     *
     * @author 61990
     * @updateTime 2017/3/8
     * @return 场馆所有的记录
     */
    public List<ConsumeRecord> getVenueConsumeRecords(int venueid){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        String sql = "SELECT * from consume_record where venueid = "+venueid;
        Query query = session.createSQLQuery(sql).addEntity(ConsumeRecord.class);
        List<ConsumeRecord> consumeRecords= query.list();
        transaction.commit();
        session.close();
        return consumeRecords;
    }
}
