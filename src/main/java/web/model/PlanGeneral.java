package web.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 座位的概况
 */
public class PlanGeneral implements Serializable{

    private int planid;
    private Date startTime;
    private Date endTime;
    private String type;
    private String description;
    private SeatMapObj[] seatMaps;

    public PlanGeneral(){

    }

    public PlanGeneral(Date startTime, Date endTime, String type, String description, String seatMaps) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
        this.description = description;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
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
}
