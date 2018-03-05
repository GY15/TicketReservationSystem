package web.dao;

import web.entity.SeatMap;

import java.util.List;

/**
 * Created by 61990 on 2017/12/19.
 */
public interface SeatDao extends BaseDao {
    /**
     * 添加一个场馆的全部座位信息
     *
     * @author 61990
     * @updateTime 2018/2/13
     * @param seatMaps 座位信息
     * @return 是否成功
     */
    boolean addSeatMaps(List<SeatMap> seatMaps);
    /**
     * 获取一个场馆的全部座位信息
     *
     * @author 61990
     * @updateTime 2018/2/13
     * @param venueid 场馆id
     * @return 是否成功
     */
    List<SeatMap> getSeatMap(int venueid);

}
