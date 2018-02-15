package web.model;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.List;

public class SeatMapObj implements Serializable {

    private String blockid;
    private int venueid;
    private String block;
    private String description;
    private String[] map;
    private SeatType[] type;

    public SeatMapObj(SeatMap seatMap) {
        this.blockid = seatMap.getBlockid();
        this.venueid = seatMap.getVenueid();
        this.block = seatMap.getBlock();
        this.description = seatMap.getDescription();
        List<String> list = JSON.parseArray(seatMap.getMap(),String.class);
        this.map = list.stream().toArray(String[]::new);
        this.type = JSON.parseObject(seatMap.getType(), SeatType[].class);

    }

    public String getBlockid() {
        return blockid;
    }

    public void setBlockid(String blockid) {
        this.blockid = blockid;
    }

    public int getVenueid() {
        return venueid;
    }

    public void setVenueid(int venueid) {
        this.venueid = venueid;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getMap() {
        return map;
    }

    public void setMap(String[] map) {
        this.map = map;
    }

    public SeatType[] getType() {
        return type;
    }

    public void setType(SeatType[] type) {
        this.type = type;
    }
}
