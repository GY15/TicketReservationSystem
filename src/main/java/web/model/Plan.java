package web.model;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 座位的sql
 */
@Entity
@Table(name="plan")
public class Plan implements Serializable{

    private int planid;
    private int venueid;
    private int seatNum;
    private int saleSeatNum;
    private int unsubscribeNum;
    private Date startTime;
    private Date endTime;
    private String type;
    private String description;
    private String seatMaps;

    public Plan(){

    }

    public Plan(int venueid, int seatNum,  Date startTime, Date endTime, String type, String description, String seatMaps) {
        this.venueid = venueid;
        this.seatNum = seatNum;
        this.saleSeatNum = 0;
        this.unsubscribeNum = 0;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
        this.description = description;
        this.seatMaps = seatMaps;
    }

    @Id
    @GenericGenerator(name = "myGenerator", strategy = "assigned")
    @GeneratedValue(generator = "myGenerator")
    @Column(name="planid")
    public int getPlanid() {
        return planid;
    }

    public void setPlanid(int planid) {
        this.planid = planid;
    }

    @Column(name="venueid")
    public int getVenueid() {
        return venueid;
    }

    public void setVenueid(int venueid) {
        this.venueid = venueid;
    }

    @Column(name="seat_num")
    public int getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }

    @Column(name="sale")
    public int getSaleSeatNum() {
        return saleSeatNum;
    }

    public void setSaleSeatNum(int saleSeatNum) {
        this.saleSeatNum = saleSeatNum;
    }

    @Column(name="unsubscribe")
    public int getUnsubscribeNum() {
        return unsubscribeNum;
    }

    public void setUnsubscribeNum(int unsubscribeNum) {
        this.unsubscribeNum = unsubscribeNum;
    }

    @Column(name="type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name="description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name="start_time")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    @Column(name="end_time")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    @Column(name="seat_maps")
    public String getSeatMaps() {
        return seatMaps;
    }

    public void setSeatMaps(String seatMaps) {
        this.seatMaps = seatMaps;
    }


}
