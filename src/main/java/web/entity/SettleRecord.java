package web.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 座位的sql
 */
@Entity
@Table(name="settle_record")
public class SettleRecord implements Serializable{

    private Date settleTime;
    private String manager;
    private int venueid;
    private double rate;
    private double profit;

    public SettleRecord(){

    }

    public SettleRecord(String manager, int venueid, double rate, double profit) {
        this.settleTime = new Date();
        this.manager = manager;
        this.venueid = venueid;
        this.rate = rate;
        this.profit = profit;
    }

    @Id
    @GenericGenerator(name = "myGenerator", strategy = "assigned")
    @GeneratedValue(generator = "myGenerator")
    @Column(name="settle_time")
    public Date getSettleTime() {
        return settleTime;
    }

    public void setSettleTime(Date settleTime) {
        this.settleTime = settleTime;
    }
    @Column(name="manager")
    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
    @Column(name="venueid")
    public int getVenueid() {
        return venueid;
    }

    public void setVenueid(int venueid) {
        this.venueid = venueid;
    }

    @Column(name="rate")
    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
    @Column(name="profit")
    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

}
