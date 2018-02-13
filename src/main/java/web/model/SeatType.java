package web.model;

public class SeatType {
    private String type;
    private String name;
    private double value;
    public SeatType(String type, String name, double value) {
        this.type = type;
        this.name = name;
        this.value = value;
    }

    public SeatType() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
