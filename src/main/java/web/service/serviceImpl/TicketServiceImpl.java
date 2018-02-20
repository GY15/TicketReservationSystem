package web.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.PlanDao;
import web.dao.SeatDao;
import web.dao.TicketDao;
import web.model.*;
import web.service.PlanService;
import web.service.TicketService;
import web.utilities.format.SeatMapConvert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    TicketDao ticketDao;
    /**
     * 获得已经预定的的座位编号
     * 格式 1_4
     * @author 61990
     * @updateTime 2018/2/18
     * @param planid 预定的list
     * @param block 区域
     * @return 是否成功
     */
    public String[] getBookedArray(int planid,String block){
        List<Ticket> tickets = ticketDao.getBookedTicket(planid,block);
        String[] seatNums = new String[tickets.size()];
        for (int i = 0 ; i< tickets.size();i++){
            seatNums[i]= tickets.get(i).getRow()+"_"+tickets.get(i).getCol();
        }
        return seatNums;
    }
    /**
     * 由seatMapObjs 生成tickets，返回是否保存成功
     *
     * @author 61990
     * @updateTime 2018/2/15
     * @param seatMapObjs 多个区域的座位信息
     * @param planid 计划的id信息
     * @return 生成ticket是否成功
     */
    public String createTickets(int planid ,List<SeatMapObj> seatMapObjs) {
        List<Ticket> tickets = new ArrayList<>();
        for (SeatMapObj seatMapObj : seatMapObjs){
            SeatType[] seatTypes =  seatMapObj.getType();
            HashMap<String,String> seatName = new HashMap();
            HashMap<String,Double> seatValue = new HashMap();
            for (SeatType seatType : seatTypes){
                seatValue.put(seatType.getType(),seatType.getValue());
                seatName.put(seatType.getType(),seatType.getName());
            }
            String[] maps = seatMapObj.getMap();
            for (int i = 0; i < maps.length;i++){
                for (int j = 0; j < maps[i].length();j++){
                    String type = maps[i].substring(j,j+1);
                    if(!type.equals("_")) {
                        Ticket ticket = new Ticket(planid + "_" + seatMapObj.getBlock() + "_" + (i + 1) + "_" + (j + 1), planid, seatName.get(type),
                                seatMapObj.getBlock(), i + 1, j + 1, seatValue.get(type));
                        tickets.add(ticket);
                    }
                }
            }
        }
        return ticketDao.addTicket(tickets)?"success":"fail";
    }


}
