package web.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.PlanDao;
import web.dao.SeatDao;
import web.dao.TicketDao;
import web.dao.UserDao;
import web.model.*;
import web.service.PlanService;
import web.service.SeatService;
import web.service.TicketService;
import web.utilities.format.SeatMapConvert;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {
    @Autowired
    SeatDao seatDao;
    @Autowired
    TicketService ticketService;
    @Autowired
    PlanDao planDao;
    @Autowired
    UserDao userDao;
    /**
     * 发布计划
     *
     * @author 61990
     * @updateTime 2018/2/14
     * @param plan 计划
     * @return 是否成功
     */
    public String publishPlan(Plan plan){
        int planid = planDao.createPlan(plan);
        List<SeatMapObj> seatMapObjs = SeatMapConvert.StringToObj( JSON.parseArray(plan.getSeatMaps(),SeatMap.class));
        return  ticketService.createTickets(planid,seatMapObjs);
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
        return parsePlan( planDao.getPlan(venueid));
    }
    /**
     * 获得指定id 的计划
     *
     * @author 61990
     * @updateTime 2018/2/18
     * @param planid 计划的id
     * @return 计划详情
     */
    public PlanGeneral getPlan(int planid){
        Plan plan = planDao.getOnePlan(planid);
        Venue venue = userDao.getVenue(plan.getVenueid());
        List<SeatMap> seatMap = JSON.parseArray(plan.getSeatMaps(),SeatMap.class);
        List<SeatMapObj> seatMapObjs =SeatMapConvert.StringToObj(seatMap);
        return new PlanGeneral(plan.getPlanid(),plan.getStartTime() , plan.getEndTime(), plan.getType(), plan.getDescription(),  seatMapObjs,venue.getProvince(),venue.getCity(),venue.getLocation());
    }
    /**
     * 暂时不分页，显示所有的计划
     *
     * @author 61990
     * @updateTime 2018/2/22
     * @return list
     */
    public List<PlanGeneral> getPlanGeneral(){
        return parsePlan( planDao.getAllPlan());
    }

    /**
     * 将计划list转化为可用的形式
     *
     * @author 61990
     * @updateTime 2018/2/22
     * @return list
     */
    public List<PlanGeneral> parsePlan(List<Plan> plans){
        List<PlanGeneral> planGenerals = new ArrayList<>();
        for (Plan plan: plans){
            Venue venue = userDao.getVenue(plan.getVenueid());
            List<SeatMap> seatMap = JSON.parseArray(plan.getSeatMaps(),SeatMap.class);
            List<SeatMapObj> seatMapObjs =SeatMapConvert.StringToObj(seatMap);
            planGenerals.add(new PlanGeneral(plan.getPlanid(),plan.getStartTime() , plan.getEndTime(), plan.getType(), plan.getDescription(),  seatMapObjs,venue.getProvince(),venue.getCity(),venue.getLocation()));
        }
        return planGenerals;
    }
}
