package web.model;

import org.hibernate.annotations.GenericGenerator;
import web.utilities.enums.TicketState;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 座位的概况
 */
@Entity
@Table(name="ticket")
public class Ticket implements Serializable{
    private String ticketid;
    private int planid;
    private String seatName;
    private String block;
    private int row;
    private int col;
    private String state;
    private double value;

    public Ticket(){

    }

    public Ticket(String ticketid, int planid, String seatName, String block, int row, int col, double value) {
        this.ticketid = ticketid;
        this.planid = planid;
        this.state = TicketState.NOT_SALE.getRepre();
        this.seatName = seatName;
        this.block = block;
        this.row = row;
        this.col = col;
        this.value = value;
    }

    @Id
    @GenericGenerator(name = "myGenerator", strategy = "assigned")
    @GeneratedValue(generator = "myGenerator")
    @Column(name="ticketid")
    public String getTicketid() {
        return ticketid;
    }

    public void setTicketid(String ticketid) {
        this.ticketid = ticketid;
    }
    @Column(name="planid")
    public int getPlanid() {
        return planid;
    }

    public void setPlanid(int planid) {
        this.planid = planid;
    }


    @Column(name="seat_name")
    public String getSeatName() {
        return seatName;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }

    @Column(name="block")
    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    @Column(name="row")
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    @Column(name="col")
    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
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
}
