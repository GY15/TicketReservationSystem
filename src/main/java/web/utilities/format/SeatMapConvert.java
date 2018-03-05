package web.utilities.format;

import web.entity.SeatMap;
import web.model.SeatMapObj;

import java.util.ArrayList;
import java.util.List;

public class SeatMapConvert {
    public static List<SeatMapObj> StringToObj(List<SeatMap> seatMaps){
        List<SeatMapObj> seatMapObjs = new ArrayList<>();
        for (SeatMap seatMap: seatMaps){
            seatMapObjs.add(new SeatMapObj(seatMap));
        }
        return seatMapObjs;
    }
}
