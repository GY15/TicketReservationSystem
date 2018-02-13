package web.service;

import web.model.Member;
import web.model.SeatMap;
import web.model.Venue;
import web.utilities.enums.MemberState;

/**
 * 管理用户登录
 */
public interface SeatService {

    /**
     * 提交座位信息
     *
     * @author 61990
     * @updateTime 2018/2/13
     * @param seatMaps 座位信息
     * @return 是否成功
     */
    String submitSeatMap(SeatMap[] seatMaps);

}
