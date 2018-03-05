package web.utilities.enums;

public enum ReservationState {
    ALLOCATE_FAIL("配票失败"),
    ALLOCATE_SUCCESS("配票成功"),
    RESERVATION("等待配票");

    private String repre;

    ReservationState(String repre) {
        this.repre = repre;
    }

    /**
     *
     * @return 该枚举相对应的文件中形式
     *
     * enum TO String
     */
    public String getRepre() {
        return repre;
    }

    /**
     *
     * @return 该类型对应的枚举代码
     *
     * String TO enum
     * 便于从数据库读入
     */
    public static ReservationState getName(String a) {
        for (ReservationState thisEnum : ReservationState.values()){
            if (thisEnum.name().equals(a)){
                return thisEnum;
            }
        }
        return null;
    }
    public static ReservationState getEnum(String a) {
        for (ReservationState thisEnum : ReservationState.values()){
            if (thisEnum.getRepre().equals(a)){
                return thisEnum;
            }
        }
        return null;
    }

}
