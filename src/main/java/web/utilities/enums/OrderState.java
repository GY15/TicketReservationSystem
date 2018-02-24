package web.utilities.enums;

public enum OrderState {
    NOT_PAY("未支付"),
    PAY("已支付"),
    REFUND("已退票"),
    ARRIVE("已出票"),
    INVALID("已失效"),
    ALL("全部");

    private String repre;

    OrderState(String repre) {
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
    public static OrderState getEnum(String a) {
        for (OrderState thisEnum : OrderState.values()){
            if (thisEnum.repre.equals(a)){
                return thisEnum;
            }
        }
        return null;
    }

}
