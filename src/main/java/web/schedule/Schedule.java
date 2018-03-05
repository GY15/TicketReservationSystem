package web.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import web.service.OrderService;

/**
 * spring定时器
 *
 * @author 61990
 *
 */
@Component
public class Schedule {
    @Autowired
    private OrderService orderService;
    @Scheduled(cron = "0/20 * * * * ?")
    public void handleOvertimeOrder() {
//        orderService.checkOvertime();
    }
    @Scheduled(cron =  "0 0/5 * * * ?")
    public void autoDistributeTicket() {
     //   orderService.autoDistributeTicket();
    }
}
