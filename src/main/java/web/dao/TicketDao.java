package web.dao;

import web.dao.BaseDao;
import web.model.Plan;
import web.model.SeatMap;

import java.util.List;

/**
 * Created by 61990 on 2017/12/19.
 */
public interface TicketDao extends BaseDao {
    /**
     * 添加一个plan的所有座位信息
     *
     * @author 61990
     * @updateTime 2018/2/15
     * @param
     * @return 是否成功
     */
    boolean addTicket();

}
