package web.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.SeatDao;
import web.dao.TicketDao;
import web.dao.UserDao;
import web.dao.daoImpl.UserDaoImpl;
import web.model.*;
import web.service.SeatService;
import web.utilities.format.SeatMapConvert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SeatServiceImpl implements SeatService {
    @Autowired
    SeatDao seatDao;
    @Autowired
    TicketDao ticketDao;
    /**
     * 提交座位信息
     *
     * @author 61990
     * @updateTime 2018/2/13
     * @param seatMaps 座位信息
     * @return 是否成功
     */
    public String submitSeatMap(List<SeatMap> seatMaps) {
        return seatDao.addSeatMaps(seatMaps)?"成功":"失败";
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
        return seatDao.getSeatMap(venueid);
    }


}
