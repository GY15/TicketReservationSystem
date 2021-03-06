package web.model;

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

    private int venueid;
    private String venueName;
    private String province;
    private String city;
    private String location;
    private int valid;

    private int isChecked;

    public PlanGeneral(int planid, Date startTime, Date endTime, String type, String description, List<SeatMapObj> seatMapObjs,String province, String city, String location,String name,int venueid){
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
        this.venueName = name ;
        this.venueid = venueid;
        Date date  =  new Date();
        if(startTime.getTime() < date.getTime()){
            valid = 0;
        }else{
            valid = 1;
        }
        if(startTime.getTime() > date.getTime()+10*1000*3600||endTime.getTime()< date.getTime()){
            isChecked = 0;
        }else{
            isChecked = 1;
        }
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

    public int getVenueid() {
        return venueid;
    }

    public void setVenueid(int venueid) {
        this.venueid = venueid;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    public int getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(int isChecked) {
        this.isChecked = isChecked;
    }
}
