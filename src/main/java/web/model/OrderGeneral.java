package web.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 订单概况的展示信息
 */
public class OrderGeneral implements Serializable{

    private int orderid;
    private String email;
    private int planid;
    private String block;
    private String tickets;
    private int venueid;
    private String state;
    private String startTime;
    private String endTime;
    private String venueName;
    private double value;
    private String createTime;
    private String province;
    private String city;
    private String location;
    private String planType;
    private String planName;

    public OrderGeneral(int orderid, String email, int planid, String block, String tickets, int venueid, String state, Date startTime, Date endTime, String venueName, double value, Date createTime, String province, String city, String location,String planName,String planType) {
        this.orderid = orderid;
        this.email = email;
        this.planid = planid;
        this.block = block;
        this.tickets = tickets;
        this.venueid = venueid;
        this.state = state;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        this.startTime = sdf.format(startTime);
        this.endTime = sdf.format(endTime);
        this.venueName = venueName;
        this.value = value;
        this.createTime = sdf.format(createTime);
        this.province = province;
        this.city = city;
        this.location = location;
        this.planName = planName;
        this.planType = planType;
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

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPlanid() {
        return planid;
    }

    public void setPlanid(int planid) {
        this.planid = planid;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getTickets() {
        return tickets;
    }

    public void setTickets(String tickets) {
        this.tickets = tickets;
    }

    public int getVenueid() {
        return venueid;
    }

    public void setVenueid(int venueid) {
        this.venueid = venueid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }
}
