package web.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 座位的概况
 */
@Entity
@Table(name="orders")
public class Order implements Serializable{

    private int orderid;
    private String email;
    private int planid;
    private String tickets;
    private int venueid;
    private String state;
    private double value;
    private Date createTime;
    private String seats;
    private String block;
    public Order(){

    }

    public Order(String email, int planid, String tickets, int venueid, String state, double value,String seats,String block) {
        this.email = email;
        this.planid = planid;
        this.tickets = tickets;
        this.venueid = venueid;
        this.state = state;
        this.value = value;
        this.createTime = new Date();
        seats = seats.replaceAll("_","排");
        seats = seats.replaceAll("\",","座,");
        seats = seats.replaceAll("\"]","座]");
        seats = seats.replaceAll("\"","");
        this.seats = seats;
        this.block = block;
    }

    @Id
    @GenericGenerator(name = "myGenerator", strategy = "assigned")
    @GeneratedValue(generator = "myGenerator")
    @Column(name="orderid")
    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }
    @Column(name="email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name="planid")
    public int getPlanid() {
        return planid;
    }

    public void setPlanid(int planid) {
        this.planid = planid;
    }

    @Column(name="tickets")
    public String getTickets() {
        return tickets;
    }

    public void setTickets(String tickets) {
        this.tickets = tickets;
    }
    @Column(name="venueid")
    public int getVenueid() {
        return venueid;
    }

    public void setVenueid(int venueid) {
        this.venueid = venueid;
    }
    @Column(name="state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Column(name="value")
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Column(name="create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name="seats")
    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }
    @Column(name="block")
    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }
}
