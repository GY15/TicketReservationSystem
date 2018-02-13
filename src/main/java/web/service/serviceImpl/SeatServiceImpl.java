package web.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.SeatDao;
import web.dao.UserDao;
import web.dao.daoImpl.UserDaoImpl;
import web.model.Member;
import web.model.SeatMap;
import web.service.SeatService;

@Service
public class SeatServiceImpl implements SeatService {
    @Autowired
    SeatDao seatDao;
    /**
     * 提交座位信息
     *
     * @author 61990
     * @updateTime 2018/2/13
     * @param seatMaps 座位信息
     * @return 是否成功
     */
    public String submitSeatMap(SeatMap[] seatMaps) {
        return seatDao.addSeatMaps(seatMaps)?"成功":"失败";
    }
}
