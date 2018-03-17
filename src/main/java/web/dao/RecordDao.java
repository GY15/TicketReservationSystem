package web.dao;

import web.entity.ConsumeRecord;
import web.entity.SettleRecord;
import web.entity.Ticket;

import java.util.List;

/**
 * Created by 61990 on 2017/12/19.
 */
public interface RecordDao extends BaseDao {
    /**
     * 生成一条结算数据
     *
     * @author 61990
     * @updateTime 2017/3/8
     * @param settleRecord 生成的记录信息
     * @return 是否充值成功
     */
    boolean createSettleRecord(SettleRecord settleRecord);
    /**
     * 获得所有的结算数据
     *
     * @author 61990
     * @updateTime 2017/3/8
     * @return 所有数据
     */
    List<SettleRecord> getAllSettleRecords();

    /**
     * 生成一条消费数据
     *
     * @author 61990
     * @updateTime 2017/3/8
     * @param consumeRecord 生成的记录信息
     * @return 是否成功
     */
    boolean createConsumeRecord(ConsumeRecord consumeRecord);
    /**
     * 获得会员所有的消费数据
     *
     * @author 61990
     * @updateTime 2017/3/8
     * @return 用户的所有记录
     */
    List<ConsumeRecord> getMemberConsumeRecords(String email);

    /**
     * 获得所有的消费数据
     *
     * @author 61990
     * @updateTime 2017/3/8
     * @return 场馆所有的记录
     */
    List<ConsumeRecord> getVenueConsumeRecords(int venueid);
}
