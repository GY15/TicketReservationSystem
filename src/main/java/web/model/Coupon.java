package web.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 座位的sql
 */
@Entity
@Table(name="coupon")
public class Coupon implements Serializable{

    private int couponid;
    private String email;
    private boolean valid;
    private int type;
    private int minimum;
    private double discount;

    public Coupon(){

    }



    @Id
    @GenericGenerator(name = "myGenerator", strategy = "assigned")
    @GeneratedValue(generator = "myGenerator")
    @Column(name="couponid")
    public int getCouponid() {
        return couponid;
    }

    public void setCouponid(int couponid) {
        this.couponid = couponid;
    }
    @Column(name="email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Column(name="valid")
    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
    @Column(name="type")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    @Column(name="minimum")
    public int getMinimum() {
        return minimum;
    }
    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }
    @Column(name="discount")
    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
