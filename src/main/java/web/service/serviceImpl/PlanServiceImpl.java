package web.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.SeatDao;
import web.dao.TicketDao;
import web.model.*;
import web.service.PlanService;
import web.service.SeatService;
import web.utilities.format.SeatMapConvert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {
    @Autowired
    SeatDao seatDao;
    @Autowired
    TicketDao ticketDao;
    /**
     * 发布计划
     *
     * @author 61990
     * @updateTime 2018/2/14
     * @param plan 计划
     * @return 是否成功
     */
    public String publishPlan(Plan plan){
        int planid = seatDao.createPlan(plan);
        List<SeatMapObj> seatMapObjs = SeatMapConvert.StringToObj( JSON.parseArray(plan.getSeatMaps(),SeatMap.class));
        List<Ticket> tickets = createTickets(planid,seatMapObjs);
        return ticketDao.addTicket(tickets)?"success":"fail";
    }
    /**
     * 获得venueid的plan
     *
     * @author 61990
     * @updateTime 2018/2/16
     * @param venueid 场馆id
     * @return list
     */
    public List<PlanGeneral> getPlanGeneral(int venueid) {

        return null;
    }

    /**
     * 由seatMapObjs 生成tickets
     *
     * @author 61990
     * @updateTime 2018/2/15
     * @param seatMapObjs 多个区域的座位信息
     * @param planid 计划的id信息
     * @return ticket list
     */
    private List<Ticket> createTickets(int planid ,List<SeatMapObj> seatMapObjs) {
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
        return tickets;
    }
}
