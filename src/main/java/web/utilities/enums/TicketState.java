package web.utilities.enums;

public enum TicketState {
    NOT_SALE("未出售"),
    SALE("已出售"),
    ATTEND("已检票"),
    BOOKIND("已下订单");

    private String repre;

    TicketState(String repre) {
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
    public static TicketState getEnum(String a) {
        for (TicketState thisEnum : TicketState.values()){
            if (thisEnum.repre.equals(a)){
                return thisEnum;
            }
        }
        return null;
    }

}
