package web.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="venue")
public class Venue implements Serializable{

    private int venueid;
    private String password;
    private String name;
    private String province;
    private String city;
    private String location;
    private boolean valid;
    private double unliquidated;
    private double balance;
    private double earning;
    public Venue(){

    }

    public Venue(int venueid, String password, String name, String province, String city, String location) {
        this.venueid = venueid;
        this.password = password;
        this.name = name;
        this.province = province;
        this.city = city;
        this.location = location;
        this.unliquidated = 0;
        this.balance = 0;
        this.earning = 0;
    }
    public Venue(Venue venue,int venueid, String password, String name, String province, String city, String location) {
        this.venueid = venueid;
        this.password = password;
        this.name = name;
        this.province = province;
        this.city = city;
        this.location = location;
        this.unliquidated = venue.getUnliquidated();
        this.balance = venue.getBalance();
        this.earning = venue.getEarning();
    }

    @Id
    @GenericGenerator(name = "myGenerator", strategy = "assigned")
    @GeneratedValue(generator = "myGenerator")
    @Column(name="venueid")
    public int getVenueid() {
        return venueid;
    }

    public void setVenueid(int venueid) {
        this.venueid = venueid;
    }


    @Column(name="password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name="province")
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Column(name="city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name="location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Column(name="valid")
    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Column(name="unliquidated")
    public double getUnliquidated() {
        return unliquidated;
    }

    public void setUnliquidated(double unliquidated) {
        this.unliquidated = unliquidated;
    }

    @Column(name="balance")
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Column(name="earning")
    public double getEarning() {
        return earning;
    }

    public void setEarning(double earning) {
        this.earning = earning;
    }
}
