package web.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RecordGeneral {
    private String handleTime;
    private String email;
    private int venueid;
    private String venueName;
    private double value;
    private boolean isFund;
    private String type;

    public RecordGeneral(Date handleTime, String email, int venueid, String venueName, double value, boolean isFund) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.handleTime = sdf.format(handleTime);
        this.email = email;
        this.venueid = venueid;
        this.venueName = venueName;
        this.value = value;
        this.isFund = isFund;
        this.type = isFund?"会员退款":"会员支付";
    }

    public String getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(String handleTime) {
        this.handleTime = handleTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isFund() {
        return isFund;
    }

    public void setFund(boolean fund) {
        isFund = fund;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
