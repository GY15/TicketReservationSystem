package web.model;

import com.alibaba.fastjson.JSON;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 座位的概况
 */
public class PlanGeneral implements Serializable{

    private int planid;
    private String startTime;
    private String endTime;
    private String type;
    private String description;
    private SeatMapObj[] seatMaps;

    private String province;
    private String city;
    private String location;

    public PlanGeneral(int planid, Date startTime, Date endTime, String type, String description, List<SeatMapObj> seatMapObjs,String province, String city, String location){
        this.planid = planid;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        this.startTime = sdf.format(startTime);
        this.endTime = sdf.format(endTime);
        this.type = type;
        this.description = description;
        this.seatMaps = seatMapObjs.stream().toArray(SeatMapObj[]::new);
        this.province = province;
        this.city = city;
        this.location = location;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getPlanid() {
        return planid;
    }

    public void setPlanid(int planid) {
        this.planid = planid;
    }

    public SeatMapObj[] getSeatMaps() {
        return seatMaps;
    }

    public void setSeatMaps(SeatMapObj[] seatMaps) {
        this.seatMaps = seatMaps;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
