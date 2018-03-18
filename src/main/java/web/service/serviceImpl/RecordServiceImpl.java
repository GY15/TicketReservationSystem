package web.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.CouponDao;
import web.dao.RecordDao;
import web.dao.UserDao;
import web.entity.*;
import web.model.RecordGeneral;
import web.service.RecordService;
import web.service.UserService;
import web.utilities.MailUtil;
import web.utilities.enums.MemberState;
import web.utilities.enums.UserType;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    RecordDao recordDao;
    @Autowired
    UserDao userDao;
    /**
     * 生成一条结算数据
     *
     * @author 61990
     * @updateTime 2017/3/8
     * @param settleRecord 生成的记录信息
     * @return 是否充值成功
     */
    public boolean createSettleRecord(SettleRecord settleRecord){
        return recordDao.createSettleRecord(settleRecord);
    }

    /**
     * 获得所有的结算数据
     *
     * @author 61990
     * @updateTime 2017/3/8
     * @return 所有
     */
    public List<SettleRecord> getAllSettleRecords(){
        return recordDao.getAllSettleRecords();
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
        return recordDao.createConsumeRecord(consumeRecord);
    }
    /**
     * 获得会员所有的消费数据
     *
     * @author 61990
     * @updateTime 2017/3/8
     * @return 用户的所有记录
     */
    public List<RecordGeneral> getMemberConsumeRecords(String email){
        List<ConsumeRecord> records = recordDao.getMemberConsumeRecords(email);
        return parseRecord(records);
    }

    /**
     * 获得所有的消费数据
     *
     * @author 61990
     * @updateTime 2017/3/8
     * @return 场馆所有的记录
     */
    public List<RecordGeneral> getVenueConsumeRecords(int venueid){
        List<ConsumeRecord> records = recordDao.getVenueConsumeRecords(venueid);
        return parseRecord(records);
    }

    public List<RecordGeneral> parseRecord(List<ConsumeRecord> records){
        List<RecordGeneral> recordGenerals = new ArrayList<>();
        for (ConsumeRecord record: records){
            Venue venue = userDao.getVenue(record.getVenueid());
            recordGenerals.add(new RecordGeneral(record.getHandleTime(),record.getEmail(),record.getVenueid(),venue.getName(),record.getValue(),record.isFund()));
        }
        return recordGenerals;
    }
}

