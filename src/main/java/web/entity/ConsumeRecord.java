package web.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 座位的sql
 */
@Entity
@Table(name="consume_record")
public class ConsumeRecord implements Serializable{

    private Date handleTime;
    private String email;
    private int venueid;
    private double value;
    private boolean isFund;

    public ConsumeRecord(String email, int venueid, double value, boolean isFund) {
        this.email = email;
        this.venueid = venueid;
        this.value = value;
        this.isFund = isFund;
        this.handleTime = new Date();
    }

    public ConsumeRecord(){

    }



    @Id
    @GenericGenerator(name = "myGenerator", strategy = "assigned")
    @GeneratedValue(generator = "myGenerator")
    @Column(name="handle_time")
    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    @Column(name="email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Column(name="venueid")
    public int getVenueid() {
        return venueid;
    }

    public void setVenueid(int venueid) {
        this.venueid = venueid;
    }
    @Column(name="value")
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
    @Column(name="is_fund")
    public boolean isFund() {
        return isFund;
    }

    public void setFund(boolean fund) {
        isFund = fund;
    }
}
