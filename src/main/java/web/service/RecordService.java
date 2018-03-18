package web.service;

import web.entity.*;
import web.model.RecordGeneral;
import web.utilities.enums.MemberState;
import web.utilities.enums.UserType;

import java.util.List;

/**
 * 管理用户登录
 */
public interface RecordService {

    /**
     * 生成一条结算数据
     *
     * @author 61990
     * @updateTime 2017/3/8
     * @param settleRecord 生成的记录信息
     * @return 是否创建成功
     */
    boolean createSettleRecord(SettleRecord settleRecord);
    /**
     * 获得所有的结算数据
     *
     * @author 61990
     * @updateTime 2017/3/8
     * @return 所有
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
    List<RecordGeneral> getMemberConsumeRecords(String email);

    /**
     * 获得所有的消费数据
     *
     * @author 61990
     * @updateTime 2017/3/8
     * @return 场馆所有的记录
     */
    List<RecordGeneral> getVenueConsumeRecords(int venueid);
}
