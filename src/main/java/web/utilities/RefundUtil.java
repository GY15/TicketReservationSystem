package web.utilities;

import java.util.Date;

public class RefundUtil {
    //根据不同的时间收取不同的手续费率
    public static double getPoundageRate(Date date){
        Date today = new Date();
        long diff = (date.getTime()-today.getTime())/1000/3600/24;
        if(diff > 10){
            return 1.0;
        }else if(diff > 5){
            return 0.5;
        }else{
            return 0.3;
        }
    }
}
